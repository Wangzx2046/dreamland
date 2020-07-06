package com.zero.dreamland.auth.rest;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zero.dreamland.auth.jwt.JwtTokenUtil;
import com.zero.dreamland.auth.service.IOnlineUserService;
import com.zero.dreamland.auth.springSecurity.AuthUser;
import com.zero.dreamland.auth.utils.SecurityUtils;
import com.zero.dreamland.common.StringUtils.IpUtil;
import com.zero.dreamland.common.annotation.AnonymousAccess;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.login.LoginUser;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.rsa.RsaProperties;
import com.zero.dreamland.common.rsa.RsaUtils;
import com.zero.dreamland.redis.utils.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: TokenLogin
 * @Description: Token认证登录*
 * @Author: wzx
 * @Date: 2019-04-26 13:19
 * @Version 1.0.0
 **/
@Api(tags = "常规登录")
@Slf4j
@RestController
@RequestMapping("tokenAuth")
public class SecurityLoginController {


    @Value("${rsa.private_key}")
    private String privateKey;
    @Value("${login.single:false}")
    private Boolean singleLogin;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private IOnlineUserService onlineUserService;


    /**
     *   @Description    ：登录
     *   @Method_Name    ：login 
     *
     * @Param : [loginUser]
     *   @return           : com.zero.devil.common.returnMsg.Result    
     *   @Creation Date    ：2020/3/26 10:13       
     *   @Author           ：wzx
     */
    @AnonymousAccess
    @ApiOperation("获取token")
    @PostMapping("/getToken")
    private ResponseEntity<Object> login(@Validated @RequestBody LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 密码解密---使用hutool
//        RSA rsa = new RSA(privateKey, null);
//        String password = new String(rsa.decrypt(loginUser.getPassWord(), KeyType.PrivateKey));
        //自定义工具类解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, loginUser.getPassWord());

        // 查询验证码
        String code = (String) redisUtil.get(loginUser.getUuid());
        // 清除验证码
        redisUtil.delete(loginUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(loginUser.getCode()) || !loginUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }


        // 生成令牌
        String currentTimeMillis = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toString();//统一token和redis的当前时间的时间戳。

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(), password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final AuthUser authUser = (AuthUser) authentication.getPrincipal();
        final String token = jwtUtil.generateToken(authUser, currentTimeMillis);

        // 保存在线信息
        onlineUserService.save(authUser, token, request);

        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        //response.setHeader("Authorization", token);
        //response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", token);
            put("user", authUser);
        }};

        String ip = IpUtil.getIpAddr(request);
        log.info("用户（" + loginUser.getUserName() + ")从IP(" + ip + ")登录系统");

        if (singleLogin) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }

        return ResponseEntity.ok(authInfo);


    }


    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    /**
     *   @Description    ：退出登录
     *   @Method_Name    ：cancellationToken 
     *   @return           : com.wzx.genesis.util.returnMsg.Result    
     *   @Creation Date    ：2019/4/30 14:03       
     *   @Author           ：wzx
     */
    @DeleteMapping("cancellationToken")
    private ResponseEntity<Object> cancellationToken(HttpServletRequest request, HttpServletResponse response) {
        String token = jwtUtil.getRequestToken(request);
        try {
            onlineUserService.logout(token);
            log.info("用户（" + jwtUtil.getUsernameFromToken(token) + ")退出系统");
        } catch (ExpiredJwtException e) {
            log.info("退出登录时捕获无效token:" + token);
            return new ResponseEntity<>(HttpStatus.OK);//退出登录无所谓了，
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     *   @Description    ：测试登录
     *   @Method_Name    ：article 
     *
     * @Param : []
     *   @return           : com.nier.automata.plugin.returnMsg.Result    
     *   @Creation Date    ：2019/7/19 15:34       
     *   @Author           ：wzx
     */
    @GetMapping("/article")
    public Result article(Principal principal) {

        if (null != principal) {
            return new Result(HttpStatus.OK.value(), "(" + principal.getName() + ")" + ",您已经登录了(You are already logged in)", null);
        } else {
            return new Result(HttpStatus.OK.value(), "你是游客(You are guest)", null);
        }
    }
}
