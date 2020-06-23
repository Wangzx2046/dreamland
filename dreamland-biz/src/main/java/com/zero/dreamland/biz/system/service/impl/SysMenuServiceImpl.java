package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysMenuDao;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.service.ISysMenuService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysRolesMenusService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import com.zero.dreamland.biz.system.vo.MenuMetaVo;
import com.zero.dreamland.biz.system.vo.MenuVo;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.exception.EntityExistException;
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
    public List<SysMenu> list(SysMenu sysMenu) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>(sysMenu);
        if (StringUtils.isNotBlank(sysMenu.getBlurry())) {
            queryWrapper.eq("title", sysMenu.getBlurry())
                    .or().eq("component", sysMenu.getBlurry())
                    .or().eq("permission", sysMenu.getBlurry());
        }
        return sysMenuDao.selectList(queryWrapper);
    }

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
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getTitle());
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

    @Override
    public List<SysMenu> queryAll(SysMenu sysMenu, boolean isQuery) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>(sysMenu);

        if (isQuery) {
            if (StringUtils.isBlank(sysMenu.getPid())) {
                queryWrapper.isNull("pid");
            }
        }

        if (StringUtils.isNotBlank(sysMenu.getBlurry())) {
            queryWrapper.eq("title", sysMenu.getBlurry())
                    .or().eq("component", sysMenu.getBlurry())
                    .or().eq("permission", sysMenu.getBlurry());
        }
        List<SysMenu> allList = sysMenuDao.selectList(queryWrapper);
        return allList;
    }

    @Override
    public boolean save(SysMenu sysMenu) {
        int count = sysMenuDao.selectCount(new QueryWrapper<SysMenu>()
                .eq("title", sysMenu.getTitle())
                .or().eq("component_name", sysMenu.getComponentName()));
        if (count > 0) {
            throw new EntityExistException(SysMenu.class, "title or component_name", sysMenu.getTitle());
        }
        if ("0".equals(sysMenu.getPid())) {
            sysMenu.setPid(null);
        }

        if (sysMenu.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(sysMenu.getPath().toLowerCase().startsWith(http) || sysMenu.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        sysMenuDao.insert(sysMenu);
        // 计算子节点数目
        sysMenu.setSubCount(0);
        // 更新父节点菜单数目
        updateSubCnt(sysMenu.getPid());
        //      redisUtils.del("menu::pid:" + (sysMenu.getPid() == null ? 0 : sysMenu.getPid()));

        return true;
    }

    @Override
    public boolean updateById(SysMenu sysMenu) {
        if (sysMenu.getId().equals(sysMenu.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        SysMenu menu = sysMenuDao.selectById(sysMenu.getId());

        if (sysMenu.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(sysMenu.getPath().toLowerCase().startsWith(http) || sysMenu.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        SysMenu menu1 = sysMenuDao.selectOne(new QueryWrapper<SysMenu>().eq("title", sysMenu.getTitle()));
        if (menu1 != null && !menu1.getId().equals(menu.getId())) {
            throw new EntityExistException(SysMenu.class, "title", sysMenu.getTitle());
        }


        if (sysMenu.getPid().equals(0L)) {
            sysMenu.setPid(null);
        }

        // 记录的父节点ID
        String oldPid = menu.getPid();
        String newPid = sysMenu.getPid();

        if (StringUtils.isNotBlank(sysMenu.getComponentName())) {
            menu1 = sysMenuDao.selectOne(new QueryWrapper<SysMenu>().eq("component_name", sysMenu.getComponentName()));
            if (menu1 != null && !menu1.getId().equals(menu.getId())) {
                throw new EntityExistException(SysMenu.class, "component_name", sysMenu.getComponentName());
            }
        }
        menu.setTitle(sysMenu.getTitle());
        menu.setComponent(sysMenu.getComponent());
        menu.setPath(sysMenu.getPath());
        menu.setIcon(sysMenu.getIcon());
        menu.setIFrame(sysMenu.getIFrame());
        menu.setPid(sysMenu.getPid());
        menu.setMenuSort(sysMenu.getMenuSort());
        menu.setCache(sysMenu.getCache());
        menu.setHidden(sysMenu.getHidden());
        menu.setComponentName(sysMenu.getComponentName());
        menu.setPermission(sysMenu.getPermission());
        menu.setType(sysMenu.getType());
        sysMenuDao.insert(menu);
        // 计算父级菜单节点数目
        updateSubCnt(oldPid);
        updateSubCnt(newPid);

        return true;
    }

    @Override
    //@Cacheable(key = "'pid:' + #p0")
    public List<SysMenu> getMenus(String pid) {
        List<SysMenu> menusList;
        if (StringUtils.isNotBlank(pid) && !pid.equals("0")) {
            menusList = sysMenuDao.selectList(new QueryWrapper<SysMenu>().eq("pid", pid));
        } else {
            menusList = sysMenuDao.selectList(new QueryWrapper<SysMenu>().isNull("pid"));
        }
        return menusList;
    }


    @Override
    public List<SysMenu> getSuperior(SysMenu menuDto, List<SysMenu> menus) {
        if (menuDto.getPid() == null) {
            menus.addAll(sysMenuDao.selectList(new QueryWrapper<SysMenu>().isNull("pid")));
            return menus;
        }
        menus.addAll(sysMenuDao.selectList(new QueryWrapper<SysMenu>().eq("pid", menuDto.getPid())));
        return getSuperior(sysMenuDao.selectById(menuDto.getPid()), menus);
    }

    private void updateSubCnt(String menuId) {
        if (menuId != null) {
            int count = sysMenuDao.selectCount(new QueryWrapper<SysMenu>().eq("pid", menuId));
            SysMenu sysMenu = new SysMenu();
            sysMenu.setSubCount(count);
            sysMenu.setId(menuId);
            sysMenuDao.updateById(sysMenu);
        }
    }

}