package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysUser;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */

public interface ISysUserService extends IService<SysUser> {


    List<SysUser> list(SysUser sysUser, String currentUsername);

    boolean save(SysUser sysUser, String currentUserId);

    boolean updateById(SysUser sysUser, String currentUserId);

    void checkLevel(SysUser resources, String currentUserId);

    SysUser getByUserName(String currentUsername);

    Integer getHighestLevel(String currentUserId);

    HashMap<String, Object> getUserRolesAndPermissionsByUserId(String id);
}
