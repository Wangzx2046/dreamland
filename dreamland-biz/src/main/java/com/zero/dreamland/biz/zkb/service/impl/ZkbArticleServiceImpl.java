package com.zero.dreamland.biz.zkb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.zkb.dao.ZkbArticleDao;
import com.zero.dreamland.biz.zkb.entity.ZkbArticle;
import com.zero.dreamland.biz.zkb.service.IZkbArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 赚客吧-文章 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2021-01-20
 */
@Service
public class ZkbArticleServiceImpl extends ServiceImpl<ZkbArticleDao, ZkbArticle> implements IZkbArticleService {

    @Resource
    private ZkbArticleDao zkbArticleDao;

    @Override
    public List<ZkbArticle> list(ZkbArticle zkbArticle) {
        QueryWrapper<ZkbArticle> queryWrapper = new QueryWrapper<>(zkbArticle);
        if (StringUtils.isNotBlank(zkbArticle.getBlurry())) {
            queryWrapper.eq("name", zkbArticle.getBlurry())
                    .or().eq("description", zkbArticle.getBlurry());
        }
        return zkbArticleDao.selectList(queryWrapper);
    }


}
