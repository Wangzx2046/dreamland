package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemDictDetailDao;
import com.zero.dreamland.biz.system.entity.SystemDictDetail;
import com.zero.dreamland.biz.system.service.ISystemDictDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-04-07
 */
@Service
public class SystemDictDetailServiceImpl extends ServiceImpl<SystemDictDetailDao, SystemDictDetail> implements ISystemDictDetailService {

    @Resource
    private SystemDictDetailDao systemDictDetailDao;


}
