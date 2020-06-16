package com.zero.dreamland.biz.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SystemRole;

import java.util.List;

/**
*
* @Description ：系统角色表
*
* @author : Wang.zx
* @since : 2019-08-21
*/
public interface ISystemRoleService extends IService<SystemRole> {

    List<SystemRole> findByUsersId(String id);
}
