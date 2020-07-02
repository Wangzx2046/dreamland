package com.zero.dreamland.biz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.dreamland.biz.system.entity.SysUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    Integer getHighestLevel(@Param("userId") String currentUserId);


}
