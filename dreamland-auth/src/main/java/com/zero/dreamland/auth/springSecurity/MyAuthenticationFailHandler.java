package com.zero.dreamland.auth.springSecurity;

import com.alibaba.fastjson.JSONObject;
import com.zero.dreamland.common.returnMsg.ResultEnum;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 *@Description：Spring security登录失败处理类
 *@Creation Date    ：2019/9/315:36
 *@Author           ：wzx
 */
@Slf4j
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 直接返回 json错误
        log.info("登录失败:AuthenticationFailureHandler检测到异常:" + e.getMessage());

        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode())));
        printWriter.flush();
    }
}
