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


    List<SysMenu> getByUserId(String currentUserId);

    List<SysMenu> buildTree(List<SysMenu> menuList);

    Object buildMenus(List<SysMenu> menuDtos);
}
