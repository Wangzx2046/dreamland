package com.zero.dreamland.auth.springSecurity;

import cn.hutool.core.util.StrUtil;
import com.zero.dreamland.auth.jwt.JwtTokenUtil;
import com.zero.dreamland.auth.service.IOnlineUserService;
import com.zero.dreamland.common.properties.Constant;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 过滤器，在这里解析token，拿到该用户角色，设置到springsecurity的上下文环境中，让springsecurity自动判断权限
 * 所有请求最先进入此过滤器，包括登录接口，而且在springsecurity的密码验证之前执行
 * * GYB
 * * 20190220
 */
@Slf4j
@Component
public class MyJwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;//这里直接取redis缓存没有查数据库，所以用不着这个
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private IOnlineUserService iOnlineUserService;

    /**
     * 在拦截器中获取token并解析，拿到用户信息，放置到SecurityContextHolder，这样便完成了springsecurity和jwt的整合。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        // System.out.println("进入token过滤器");

        String authToken = resolveToken(httpServletRequest);

        if (StrUtil.isNotBlank(authToken)) {
            try {
                //校验token合法性
                jwtUtil.parseToken(authToken);
                //获取token中的账户信息
                String account = jwtUtil.getUsernameFromToken(authToken);
                if (StringUtils.isEmpty(account)) {
                    throw new RuntimeException("Token中帐号为空(The account in Token is empty.)");
                }

                //校验token过期时间
                if (jwtUtil.isTokenExpired(authToken)) {
                    throw new RuntimeException("Token已经过期(The Token has expired.)");
                }

                //验证redis登录状态
                Object redisInfo = iOnlineUserService.getOne(Constant.PREFIX_SECURITY_REFRESH_TOKEN + authToken);
                if (StringUtils.isEmpty(redisInfo)) {
                    log.info("错误的token:" + Constant.PREFIX_SECURITY_REFRESH_TOKEN + authToken);
                    throw new RuntimeException("Token已经失效或被系统强制下线(The Token has expired.)");
                }
                //验证token,具体怎么验证看需求，可以只验证token不查库，把权限放在jwt中即可
                UserDetails UserDetails = myUserDetailsService.loadUserByUsername(account);
                AuthUser userDetails = iOnlineUserService.getUserDetails(authToken);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }


            } catch (JwtException jwte) {
                logger.info("Token解析失败(错误的token)：" + jwte.getMessage());
            } catch (Exception e) {
                logger.info("Token解析失败：" + e.getMessage());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(Constant.TOKEN_HEADER);

        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(Constant.TOKEN_TYPE_BEARER)) {
            return authHeader.substring(Constant.TOKEN_TYPE_BEARER.length());
        }

        return null;
    }


}
