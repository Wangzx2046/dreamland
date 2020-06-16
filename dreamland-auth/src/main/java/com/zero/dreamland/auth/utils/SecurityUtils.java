package com.zero.dreamland.auth.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.utils.SpringContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 获取当前登录的用户
 * @author Zheng Jie
 * @date 2019-01-17
 */
public class SecurityUtils {



    /**
     * 获取当前登录的用户
     * @return UserDetails
     */
    public static UserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "当前登录状态过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
            return userDetailsService.loadUserByUsername(userDetails.getUsername());
        }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED, "找不到当前登录的信息");
    }


    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "当前登录状态过期");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


    /**
     * 获取系统用户ID
     * @return 系统用户ID
     */
    public static String getCurrentUserId() {
        UserDetails userDetails = getCurrentUser();
        return new JSONObject(new JSONObject(userDetails).get("user")).get("id", String.class);
    }

    /**
     * 获取当前用户的数据权限
     * @return /
     */
    public static List<String> getCurrentUserDataScope(){
        UserDetails userDetails = getCurrentUser();
        JSONArray array = JSONUtil.parseArray(new JSONObject(userDetails).get("dataScopes"));
        return JSONUtil.toList(array,String.class);
    }



/*
    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {

            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return userDetails;
    }

    public static AuthUser getAuthUser() {
        AuthUser authUser;
        try {
            authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {

            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return authUser;
    }

    *//**
     * 获取系统用户名称
     * @return 系统用户名称
     *//*
    public static String getUsername(){
        Object obj = getUserDetails();
        return new JSONObject(obj).get("username", String.class);
    }*/
}
