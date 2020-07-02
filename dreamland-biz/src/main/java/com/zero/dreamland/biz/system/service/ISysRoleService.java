package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(SysUser user);

    List<SysRole> findByUsersId(String currentUserId);

    List<SysRole> list(SysRole sysRole);

    boolean save(SysRole sysRole);

    boolean updateById(SysRole sysRole);

    void updateMenu(SysRole newRole, SysRole oldRole);

    void verification(Set<String> ids);

    Integer getHighestLevel(Set<String> roles);
}
