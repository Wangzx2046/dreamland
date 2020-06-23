package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysRoleDao;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysRolesMenus;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.ISysMenuService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysRolesMenusService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;
    @Resource
    private ISysUsersRolesService sysUsersRolesService;
    @Resource
    private ISysRolesMenusService sysRolesMenusService;
    @Resource
    private ISysMenuService sysMenuService;

    @Override
    public List<SysRole> list(SysRole sysRole) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>(sysRole);
        if (StringUtils.isNotBlank(sysRole.getBlurry())) {
            queryWrapper.eq("role_name", sysRole.getBlurry())
                    .or().eq("description", sysRole.getBlurry());
        }
        List<SysRole> roleList = sysRoleDao.selectList(queryWrapper);
        roleList.stream().forEach(x -> {
            List<SysRolesMenus> rms = sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>()
                    .eq("role_id", x.getId()));
            if (rms.size() != 0) {
                List<String> menuIds = new ArrayList<>();
                rms.stream().collect(
                        collectingAndThen(
                                toCollection(() -> new TreeSet<>(Comparator.comparing(SysRolesMenus::getMenuId))), ArrayList::new)
                ).forEach(y -> {
                    menuIds.add(y.getMenuId());
                });


                x.setMenus(new HashSet<>(sysMenuService.listByIds((List<String>) (List) menuIds)));
            }
        });


        return roleList;
    }

    @Override
    // @Cacheable(key = "'auth:' + #p0.id")
    public List<GrantedAuthority> mapToGrantedAuthorities(SysUser user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        // 查询用户角色
        List<String> roleIds = sysUsersRolesService.getRoleIdsByUserId(user.getId());

        List<SysRolesMenus> rms = sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>()
                .in("role_id", (List<String>) (List) roleIds));
        List<String> menuIds = new ArrayList<>();
        rms.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(SysRolesMenus::getMenuId))), ArrayList::new)
        ).forEach(x -> {
            menuIds.add(x.getMenuId());
        });

        List<SysMenu> menusList = sysMenuService.listByIds((List<String>) (List) menuIds);


        //  List<SysRole> roles = sysRoleDao.selectList(new QueryWrapper<SysRole>().eq("user_id", user.getId()));
        menusList.stream()
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .forEach(x -> {
                    permissions.add(x.getPermission());
                    System.out.println(x.getPermission());
                });

        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * 根据userID获取角色列表
     *
     * @param currentUserId /
     * @return /
     */
    @Override
    public List<SysRole> findByUsersId(String currentUserId) {
        List<String> sysUsersRolesList = sysUsersRolesService.getRoleIdsByUserId(currentUserId);
        List<SysRole> roleList = sysRoleDao.selectBatchIds((List<String>) (List) sysUsersRolesList);
        return roleList;
    }


}
