package com.zero.dreamland.biz.zkb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.zkb.entity.ZkbArticle;

import java.util.List;

/**
 * <p>
 * 赚客吧-文章 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2021-01-20
 */
public interface IZkbArticleService extends IService<ZkbArticle> {

    List<ZkbArticle> list(ZkbArticle zkbArticle);

}
