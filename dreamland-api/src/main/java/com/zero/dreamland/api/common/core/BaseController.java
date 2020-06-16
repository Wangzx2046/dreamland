package com.zero.dreamland.api.common.core;

import com.zero.dreamland.auth.springSecurity.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: BaseController
 * @Description: 基础父类-获取公共信息*
 * @Author: wzx
 * @Date: 2019-09-06 13:08
 * @Version 1.0.0
 **/
public class BaseController {
    public AuthUser getCurrentUser(){
        Object ud = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUser userDetails = (AuthUser) ud;
        return  userDetails;
    }
}
