package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysMenuDao;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.service.ISysMenuService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysRolesMenusService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import com.zero.dreamland.biz.system.vo.MenuMetaVo;
import com.zero.dreamland.biz.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;
    @Resource
    private ISysRolesMenusService sysRolesMenusService;

    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysUsersRolesService sysUsersRolesService;

    @Override
 //   @Cacheable(key = "'user:' + #p0.id")
    public List<SysMenu> getByUserId(String currentUserId) {

        List<String> roleIds = sysUsersRolesService.getRoleIdsByUserId(currentUserId);

        List<String> menuIds = sysRolesMenusService.getMenuIdsByRoleIds(roleIds);

        List<SysMenu> menuList = sysMenuDao.selectList(new QueryWrapper<SysMenu>()
                .in("id", menuIds)
                .ne("type", 2));
        return menuList;
    }

    @Override
    public List<SysMenu> buildTree(List<SysMenu> menuList) {
        List<SysMenu> trees = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        for (SysMenu menuDTO : menuList) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (SysMenu it : menuList) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menuList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;

    }


    @Override
    public List<MenuVo> buildMenus(List<SysMenu> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<SysMenu> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getName()) ? menuDTO.getName() : menuDTO.getTitle());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == null) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getTitle(), menuDTO.getIcon(), !menuDTO.getCache()));
                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == null) {
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }
}
