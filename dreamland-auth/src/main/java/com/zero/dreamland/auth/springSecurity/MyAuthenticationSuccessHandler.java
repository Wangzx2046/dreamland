package com.zero.dreamland.auth.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.dreamland.auth.jwt.JwtTokenUtil;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @Description：Spring security登录成功处理类,返回jwtToken
 * @Creation Date    ：2019/9/315:34
 * @Author           ：wzx
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        //TODO  本类未使用，以下代码也没改，不会起作用

        //从authentication中获取用户信息
        final AuthUser userDetail = (AuthUser) authentication.getPrincipal();
        //生成jwt
        String token = jwtTokenUtil.generateToken(userDetail, LocalDate.now().toString());
        httpServletResponse.addHeader("token", "Bearer " + token);

        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResultUtil.success()));
        log.info("登录成功:当前用户【" + userDetail.getUsername() + "】");
    }
}
