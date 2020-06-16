package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SystemMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-04-01
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    /**
     * 构建菜单树
     * @param menuDtos 原始数据
     * @return /
     */
    Map<String,Object> buildTree(List<SystemMenu> menuDtos);



    /**
     * 构建菜单树
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<SystemMenu> menuDtos);
}
