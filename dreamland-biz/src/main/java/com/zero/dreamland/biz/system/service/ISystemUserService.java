package com.zero.dreamland.biz.system.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SystemUser;
import com.zero.dreamland.common.returnMsg.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: SystemUserService
 * @Description: 系统用户*
 * @Author: wzx
 * @Date: 2019-07-18 15:21
 * @Version 1.0.0
 **/

public interface ISystemUserService extends IService<SystemUser> {

    Result add(SystemUser systemUser);

    List<SystemUser> list();

    SystemUser getOne(QueryWrapper<SystemUser> wrapper);

    Result update(SystemUser systemUser);

    //根据用户名获取全部角色
    Set<String> getUserRolesSetByAccount(String userName);

    Set<String> getUserRolesSetByUserId(String userId);

    Set<String> getUserPermissionsSetByRoles(Set<String> roleIds);

    HashMap<String,Object> getUserRolesAndPermissionsByAccount(String userName);

    HashMap<String, Object> getUserRolesAndPermissionsByUserId(String userId);
}
