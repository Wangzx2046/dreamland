package com.zero.dreamland.api.system;


import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.auth.utils.SecurityUtils;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.service.ISysMenuService;
import com.zero.dreamland.biz.system.service.ISystemRoleService;
import com.zero.dreamland.common.annotation.LogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Wang.zx
 * @Description ：系统菜单
 * @since : 2020-04-01
 */
@Api(tags = "系统菜单表")
@Slf4j
@RestController
@RequestMapping("/sys/sys-menu")
public class SystemMenuController extends BaseController {

    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISystemRoleService iSystemRoleService;

    @LogAnnotation(operateContent = "拉取web端页面菜单", operateType = "系统日志")
    @ApiOperation("获取前端所需菜单")
    @GetMapping(value = "/build")
    public ResponseEntity<Object> buildMenus() {

        List<SysMenu> menuList = sysMenuService.getByUserId(SecurityUtils.getCurrentUserId());

        List<SysMenu> menuDtos = sysMenuService.buildTree(menuList);

        return new ResponseEntity<>(sysMenuService.buildMenus(menuDtos), HttpStatus.OK);

    }


}
