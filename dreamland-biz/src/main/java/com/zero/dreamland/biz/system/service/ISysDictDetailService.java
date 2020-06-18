package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysDictDetail;
import com.zero.dreamland.biz.system.vo.SysDictDetailVo;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysDictDetailService extends IService<SysDictDetail> {

    List<SysDictDetail> list(SysDictDetailVo sysDictDetail);
}
