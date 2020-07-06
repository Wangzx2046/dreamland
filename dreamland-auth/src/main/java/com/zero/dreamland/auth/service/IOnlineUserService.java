package com.zero.dreamland.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.auth.jwt.JwtTokenUtil;
import com.zero.dreamland.auth.springSecurity.AuthUser;
import com.zero.dreamland.biz.online.OnlineUser;
import com.zero.dreamland.common.StringUtils.IpUtil;
import com.zero.dreamland.common.properties.Constant;
import com.zero.dreamland.common.properties.JwtProperties;
import com.zero.dreamland.common.utils.EncryptUtils;
import com.zero.dreamland.common.utils.FileUtil;
import com.zero.dreamland.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Zheng Jie
 * @Date 2019年10月26日21:56:27
 */
@Service
@Slf4j
public class IOnlineUserService {

    private final JwtProperties properties;
    private RedisUtil redisUtils;
    private JwtTokenUtil jwtTokenUtil;

    public IOnlineUserService(JwtProperties properties, JwtTokenUtil jwtTokenUtil, RedisUtil redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 保存在线用户信息
     *
     * @param authUser /
     * @param token    /
     * @param request  /
     */
    public void save(AuthUser authUser, String token, HttpServletRequest request) {
        String job = authUser.getUser().getDeptId() + "/" + authUser.getUser().getPositionId();
        String ip = IpUtil.getIpAddr(request);
        String browser = IpUtil.getBrowser(request);
        String address;
        try {
            address = IpUtil.getIpInfo(ip).getString("city");
        } catch (Exception e) {
            address = "未知";
        }

        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(authUser.getUsername(), authUser.getUser().getNickName(), job, browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  redisUtils.setEx(properties.getOnlineKey() +":"+ token, onlineUser, properties.getExpire(), TimeUnit.SECONDS);
        redisUtils.setEx(Constant.PREFIX_SECURITY_REFRESH_TOKEN + token, onlineUser, properties.getExpire(), TimeUnit.SECONDS);
        redisUtils.setEx(Constant.PREFIX_SECURITY_ACCESS_INFO + authUser.getUser().getUserName(), JSON.toJSON(authUser).toString(), properties.getExpire(), TimeUnit.SECONDS);
    }

    /**
     * 查询全部数据,分页
     *
     * @param filter   /
     * @param pageable /
     * @return /
     */
    public PageInfo getAll(String filter, Pageable pageable) {
        List<OnlineUser> onlineUsers = getAll(filter);
        Page paged = new Page(pageable.getPageNumber(), pageable.getPageSize());
        paged.setTotal(onlineUsers.size());
        paged.addAll(onlineUsers);
        PageInfo pageInfo = new PageInfo<>(paged);

        return pageInfo;
    }

    /**
     * 查询全部数据，不分页
     *
     * @param filter /
     * @return /
     */
    public List<OnlineUser> getAll(String filter) {
        List<String> keys = redisUtils.scan(Constant.PREFIX_SECURITY_REFRESH_TOKEN + "*");
        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    /**
     * 踢出用户
     *
     * @param key /
     * @throws Exception /
     */
    public void kickOut(String key) throws Exception {
        //   key = Constant.PREFIX_SECURITY_REFRESH_TOKEN + EncryptUtils.desDecrypt(key);
        key = Constant.PREFIX_SECURITY_REFRESH_TOKEN + key;
        redisUtils.delete(key);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    public void logout(String token) {
        String key = Constant.PREFIX_SECURITY_REFRESH_TOKEN + token;
        redisUtils.delete(key);
    }

    /**
     * 导出
     *
     * @param all      /
     * @param response /
     * @throws IOException /
     */
    public void download(List<OnlineUser> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUser user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUserName());
            map.put("岗位", user.getJob());
            map.put("登录IP", user.getIp());
            map.put("登录地点", user.getAddress());
            map.put("浏览器", user.getBrowser());
            map.put("登录日期", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    public OnlineUser getOne(String key) {
        //System.out.println("key: "+key);
        return (OnlineUser) redisUtils.get(key);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public AuthUser getUserDetails(String token) {
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        String redisAuthInfo = (String) redisUtils.get(Constant.PREFIX_SECURITY_ACCESS_INFO + userName);
        JSONObject json = (JSONObject) JSON.parse(redisAuthInfo);
        AuthUser authUser = JSONObject.parseObject(redisAuthInfo, AuthUser.class);
        //  AuthUser authUser =new AuthUser(json);


        return authUser;
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     *
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUser> onlineUsers = getAll(userName);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(onlineUser.getKey());
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(onlineUser.getKey());
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

}
