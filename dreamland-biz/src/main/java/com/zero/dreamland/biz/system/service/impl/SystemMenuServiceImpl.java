package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemMenuDao;
import com.zero.dreamland.biz.system.entity.SystemMenu;
import com.zero.dreamland.biz.system.service.ISystemMenuService;
import com.zero.dreamland.biz.system.vo.MenuMetaVo;
import com.zero.dreamland.biz.system.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-04-01
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuDao, SystemMenu> implements ISystemMenuService {


    @Override
    public Map<String, Object> buildTree(List<SystemMenu> menuDtos) {
        List<SystemMenu> trees = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        for (SystemMenu menuDTO : menuDtos) {
            if ("0".equals(menuDTO.getPid())) {
                trees.add(menuDTO);
            }
            for (SystemMenu it : menuDtos) {
                if (it.getPid().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String,Object> map = new HashMap<>(2);
        if(trees.size() == 0){
            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("content",trees);
        map.put("totalElements", menuDtos.size());
        return map;
    }

    @Override
    public List<MenuVo>  buildMenus(List<SystemMenu> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<SystemMenu> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName())  ? menuDTO.getComponentName() : menuDTO.getName());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath("0".equals(menuDTO.getPid()) ? "/" + menuDTO.getPath() :menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if(!menuDTO.getIFrame()){
                            if("0".equals(menuDTO.getPid())){
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                            }else if(!StrUtil.isEmpty(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon(),!menuDTO.getCache()));
                        if(menuDtoList !=null && menuDtoList.size()!=0){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if("0".equals(menuDTO.getPid())){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if(!menuDTO.getIFrame()){
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
