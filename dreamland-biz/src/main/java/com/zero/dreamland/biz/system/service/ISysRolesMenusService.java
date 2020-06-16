package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysRolesMenus;

import java.util.List;

/**
 * <p>
 * 角色菜单关联 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysRolesMenusService extends IService<SysRolesMenus> {

    List<String> getMenuIdsByRoleIds(List<String> roleIds);
}
