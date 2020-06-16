package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysRolesMenusDao;
import com.zero.dreamland.biz.system.entity.SysRolesMenus;
import com.zero.dreamland.biz.system.service.ISysRolesMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色菜单关联 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusDao, SysRolesMenus> implements ISysRolesMenusService {

    @Resource
    private SysRolesMenusDao sysRolesMenusDao;

    @Override
    public List<String> getMenuIdsByRoleIds(List<String> roleIds) {
        List<Object> menuIds = sysRolesMenusDao.selectObjs(new QueryWrapper<SysRolesMenus>()
                .select("menu_id")
                .in("role_id", roleIds));
        return (List<String>) (List) menuIds;
    }
}
