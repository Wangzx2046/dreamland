package com.zero.dreamland.auth.jwt;

import com.zero.dreamland.auth.springSecurity.AuthUser;
import com.zero.dreamland.common.properties.Constant;
import com.zero.dreamland.redis.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt token 工具类
 * jwt的claim里一般包含以下几种数据
 * 1、iss	token的发行者
 * 2、sub	该JWT所面向的用户
 * 3、aud	接收该jwt的乙方
 * 4、exp	token的失效时间
 * 5、nbf	在此时间之前，不会被处理
 * 6、iat	jwt的发布时间
 * 7、jti	jwt的唯一标识，防止重复使用
 *
 * @author Guoqing
 * @Date 2018-01-12
 */
@Data
@Configuration
public class JwtTokenUtil {

    private String TOKEN_TYPE_BEARER = Constant.TOKEN_TYPE_BEARER;
    private String header = Constant.TOKEN_HEADER;
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private Long expiration;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    @Resource
    private RedisUtil redisUtil;

    /**
     *@Description：从请求中获取token
     *@Method_Name：
     *   @Param            :
     *@return           :
     *@Creation Date    ：2020/3/2617:05
     *@Author           ：wzx
     */
    public String getRequestToken(HttpServletRequest request){
        String token = request.getHeader(header);
        return  token.substring(TOKEN_TYPE_BEARER.length());
    }


    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 从token获取jwt的发布时间
     *
     * @param token
     * @return
     */
    public Date getIssueAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 从token获取失效时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt的接收者
     *
     * @param token
     * @return
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取使用的jwt claim
     *
     * @param token
     * @param key
     * @return
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key
     *
     * @param token
     * @return
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, md5Key);
    }

    /**
     * 获取jwt的payload部分
     *
     * @param token
     * @return
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 验证token是否过期，true为过期，false为没过期
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        boolean flag = true;
        final Date authExpriation = getExpirationDateFromToken(token);//认证token中的过期时间

//        String userName = getUsernameFromToken(token);
//        String redisToken = (String) redisUtil.get(Constant.PREFIX_SECURITY_REFRESH_TOKEN + userName);

//        if (StringUtils.isEmpty(redisToken) || !token.trim().equals(redisToken)) {
//            return flag;//redis缓存过期,或者认证token与redis不一致。
//        }

        // Date redisExpriation = getExpirationDateFromToken(token);//认证token中的过期时间
        if (authExpriation.before(new Date())) {
            return flag;
        }

        return !flag;
    }



    /**
     * @param token 需要检查的token
     */
/*    public void checkRenewal(String token){
        // 判断是否续期token,计算token的过期时间
        long time = redisUtils.getExpire(properties.getOnlineKey() + token) * 1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        // 如果在续期检查的范围内，则续期
        if(differ <= properties.getDetect()){
            long renew = time + properties.getRenew();
            redisUtils.expire(properties.getOnlineKey() + token, renew, TimeUnit.MILLISECONDS);
        }
    }*/


    /**
     * 解析token是否正确，不正确抛出异常
     *
     * @param token
     * @throws JwtException
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 生成token（通过用户名和签名时候用的随机数）
     *
     * @param userDetails
     * @param nowDate
     * @return
     */
    public String generateToken(AuthUser userDetails, String nowDate) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(md5Key, "987654321");
        claims.put("currentTimeMillis", nowDate);
        String token = doGenerateToken(claims, userDetails);
        //将生成的token存入redis做唯一性校验
        //    redisUtil.setEx(Constant.PREFIX_SECURITY_REFRESH_TOKEN + userDetails.getUsername(), token, expiration, TimeUnit.SECONDS);
        //    redisUtil.setEx(Constant.PREFIX_SECURITY_ACCESS_INFO + userDetails.getUsername(), JSON.toJSON(userDetails).toString(), expiration, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 生成token
     *
     * @param claims
     * @param userDetails
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, UserDetails userDetails) {
        final Date createDate = new Date();
        final Date expirationDate = new Date(createDate.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }









}
