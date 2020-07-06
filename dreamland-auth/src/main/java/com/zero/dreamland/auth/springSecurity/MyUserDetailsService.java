package com.zero.dreamland.auth.springSecurity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.DataService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysUserService;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.exception.EntityNotFoundException;
import com.zero.dreamland.common.instance.State;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 用于Security查询角色进行认证
 * 根据用户名获取数据库该用户信息，spring security在登录时自动调用
 * WebSecurityConfigurerAdapter会拿这里的User里的password与用户输入的对比验证
 * GYB
 * 20190219
 */
@Service("MyUserDetailsService")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private DataService dataService;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUser sysUser;

        try {
            sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_name", account)
                    .eq("del_flag", State.NORMAL));
        } catch (EntityNotFoundException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }

        if (sysUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username:" + account));
        } else {
            if (!sysUser.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }


            HashMap<String, Object> map = sysUserService.getUserRolesAndPermissionsByUserId(sysUser.getId());
            List<SysMenu> permiList = (List<SysMenu>) map.get("permiList");
            List<SysRole> roleList = (List<SysRole>) map.get("roleList");
            sysUser.setPermissions((new HashSet(permiList)));
            sysUser.setRoles(new HashSet(roleList));
            return AuthUserFactory.create(sysUser);


          /*  AuthUser authUser = new AuthUser(sysUser,
                    dataService.getDeptIds(sysUser),
                    sysRoleService.mapToGrantedAuthorities(sysUser));
            return authUser;*/
            /*HashMap<String, Object> map = sysUserService.getUserRolesAndPermissionsByUserId(sysUser.getId());
            List<SystemPermi> permiList = (List<SystemPermi>) map.get("permiList");
            List<SystemRole> roleList = (List<SystemRole>) map.get("roleList");
            systemUser.setPermissions(permiList);
            systemUser.setRoles(roleList);
            return AuthUserFactory.create(systemUser);*/
        }


    }

}

