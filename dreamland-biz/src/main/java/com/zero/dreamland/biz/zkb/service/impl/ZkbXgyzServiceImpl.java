package com.zero.dreamland.biz.zkb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.zkb.dao.ZkbXgyzDao;
import com.zero.dreamland.biz.zkb.entity.ZkbXgyz;
import com.zero.dreamland.biz.zkb.service.IZkbXgyzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 赚客吧-小鸽驿站 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2021-01-21
 */
@Service
public class ZkbXgyzServiceImpl extends ServiceImpl<ZkbXgyzDao, ZkbXgyz> implements IZkbXgyzService {

    @Resource
    private ZkbXgyzDao zkbXgyzDao;

    @Override
    public List<ZkbXgyz> list(ZkbXgyz zkbXgyz) {
        QueryWrapper<ZkbXgyz> queryWrapper = new QueryWrapper<>(zkbXgyz);
        /*if (StringUtils.isNotBlank(zkbXgyz.getBlurry())) {
            queryWrapper.eq("name", zkbXgyz.getBlurry())
                    .or().eq("description", zkbXgyz.getBlurry());
        }*/
        return zkbXgyzDao.selectList(queryWrapper);
    }


}
