package com.zero.dreamland.biz.zkb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.dreamland.biz.zkb.entity.ZkbXgyz;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * 赚客吧-小鸽驿站 Mapper 接口
 * </p>
 *
 * @author Wang.zx
 * @since 2021-01-21
 */
public interface ZkbXgyzDao extends BaseMapper<ZkbXgyz> {

    int deleteBeforeDate(@Param("dayTime") LocalDate day);

    Integer getLast();
}
