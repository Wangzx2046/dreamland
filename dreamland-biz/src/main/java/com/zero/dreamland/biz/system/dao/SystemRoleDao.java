package com.zero.dreamland.biz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.dreamland.biz.system.entity.SystemRole;

import java.util.List;

/**
*
* @Description ：系统角色表
*
* @author : Wang.zx
* @since : 2019-08-21
*/
public interface SystemRoleDao extends BaseMapper<SystemRole> {

    List<SystemRole> findByUsersId(String id);
}
