package com.zero.dreamland.biz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.dreamland.biz.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

    Integer getHighestLevel( Set<String> roles);
}
