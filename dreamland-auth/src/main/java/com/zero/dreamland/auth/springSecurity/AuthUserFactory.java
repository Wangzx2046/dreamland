package com.zero.dreamland.auth.springSecurity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Auth user factory.
 *
 * @author Guoqing
 */
public final class AuthUserFactory {

    private AuthUserFactory() {
    }

    /**
     * Create auth user.
     *
     * @param user the user
     * @return the auth user
     */
    public static AuthUser create(SysUser user) {
        AuthUser authUser = new AuthUser();
        authUser.setUser(user);
        authUser.setAuthorities(mapToGrantedAuthorities(new ArrayList<>(user.getRoles()), new ArrayList<>(user.getPermissions())));
        return authUser;
    }

    public static AuthUser create(JSONObject json) {
        AuthUser authUser = new AuthUser();
        authUser.setUser(JSONObject.toJavaObject((JSON) json.get("user"), SysUser.class));
        authUser.setAuthorities(jsonToGrantedAuthorities((JSONArray) json.get("authorities")));
        return authUser;
    }

    /**
     * 权限转换
     *
     * @param systemRoles 角色列表
     * @param permissions 权限列表
     * @return 权限列表
     */
    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<SysRole> systemRoles, List<SysMenu> permissions) {
        //权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
        List<SimpleGrantedAuthority> authorities =
                systemRoles.stream()
                        .map(systemRole -> new SimpleGrantedAuthority("ROLE_" + systemRole.getRoleType())).collect(Collectors.toList());


        permissions.stream().forEach(x -> {
            if (!StringUtils.isEmpty(x.getPermission())) {
                authorities.add(new SimpleGrantedAuthority(x.getPermission()));
            }

        });


        return authorities;
    }


    /***
     *  @Description    ：将redis中保存的权限信息转化回来
     *  @Method_Name    ：jsonToGrantedAuthorities 
     *  @Param            : [json]
     *  @return           : java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority>    
     *  @Creation Date    ：2019/9/5 14:56       
     *  @Author           ：wzx
     */
    private static List<SimpleGrantedAuthority> jsonToGrantedAuthorities(JSONArray json) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            authorities.add(new SimpleGrantedAuthority((String) json.getJSONObject(i).get("authority")));
        }
        return authorities;
    }


}
