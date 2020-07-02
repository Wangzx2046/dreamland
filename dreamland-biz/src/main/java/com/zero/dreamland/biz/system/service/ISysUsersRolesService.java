package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysUsersRoles;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysUsersRolesService extends IService<SysUsersRoles> {

    List<String> getRoleIdsByUserId(String userId);

    int update(String id, Set<SysRole> roles);

    int insert(String userId, Set<SysRole> roles);
}
