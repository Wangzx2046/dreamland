package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysMenuService extends IService<SysMenu> {


    List<SysMenu> list(SysMenu sysMenu);

    List<SysMenu> getByUserId(String currentUserId);

    List<SysMenu> buildTree(List<SysMenu> menuList);

    Object buildMenus(List<SysMenu> menuDtos);

    List<SysMenu> queryAll(SysMenu sysMenu, boolean b);

    boolean save(SysMenu sysMenu);

    boolean updateById(SysMenu sysMenu);

    List<SysMenu> getMenus(String pid);

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menuDto /
     * @param objects /
     * @return /
     */
    List<SysMenu> getSuperior(SysMenu menuDto, List<SysMenu> objects);
}
