package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysDictDetailDao;
import com.zero.dreamland.biz.system.entity.SysDictDetail;
import com.zero.dreamland.biz.system.service.ISysDictDetailService;
import com.zero.dreamland.biz.system.vo.SysDictDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailDao, SysDictDetail> implements ISysDictDetailService {

    @Resource
    private SysDictDetailDao sysDictDetailDao;

    @Override
    public List<SysDictDetail> list(SysDictDetailVo sysDictDetail) {
        QueryWrapper<SysDictDetail> queryWrapper = new QueryWrapper<>(sysDictDetail);
        queryWrapper.like(StringUtils.isNotBlank(sysDictDetail.getLabel()), "label", sysDictDetail.getLabel());
        return sysDictDetailDao.selectList(queryWrapper);
    }
}
