package com.zero.dreamland.biz.zkb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.zkb.entity.ZkbXgyz;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 赚客吧-小鸽驿站 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2021-01-21
 */
public interface IZkbXgyzService extends IService<ZkbXgyz> {

    List<ZkbXgyz> list(ZkbXgyz zkbXgyz);

    int deleteBeforeDate(LocalDate day);

    Integer getLast();
}
