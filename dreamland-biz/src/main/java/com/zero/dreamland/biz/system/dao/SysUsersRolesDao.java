package com.zero.dreamland.biz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.dreamland.biz.system.entity.SysUsersRoles;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Mapper
public interface SysUsersRolesDao extends BaseMapper<SysUsersRoles> {

}
