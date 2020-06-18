package com.zero.dreamland.auth.jwt;/*
package com.zero.dreamland.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zero.devil.plugin.StringUtils.Base64ConvertUtil;
import com.zero.origin.auth.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.Date;


*/
/**
 *   @Description    ：JwtUtil   auth0版本
 *   @Creation Date    ：2019/9/2 15:24       
 *   @Author           ：wzx
 *//*

@Configuration
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);


    @Value("${jwt.expire}")
    private long expire;// 过期时间(秒)

    @Value("${jwt.secret}")
    private String encryptJWTKey;//密钥


    */
/**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     *//*

    public boolean verify(String token) {
        if (token.indexOf(" ") != -1) {
            token = token.substring(token.indexOf(" ") + 1);//去掉前缀，避免解析异常
        }
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.ACCOUNT) + Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            logger.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new RuntimeException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    */
/**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return
     *//*

    public String getClaim(String token, String claim) {
        if (token.indexOf(" ") != -1) {
            token = token.substring(token.indexOf(" ") + 1);//去掉前缀，避免解析异常
        }

        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            logger.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new RuntimeException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }


    */
/**
     * 生成Token
     *
     * @param account 用户名
     * @return 加密的token
     *//*

    public String generateToken(String account, String nowDate) {

        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + expire * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim("account", account)
                    .withClaim("currentTimeMillis", nowDate)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            logger.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new RuntimeException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }


}
*/
