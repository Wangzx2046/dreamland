package com.zero.dreamland.api.system;


import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.auth.utils.SecurityUtils;
import com.zero.dreamland.biz.system.entity.SysMenu;
import com.zero.dreamland.biz.system.service.ISysMenuService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.annotation.LogAnnotation;
import com.zero.dreamland.common.exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：系统菜单
 * @since : 2020-04-01
 */
@Api(tags = "系统菜单表")
@Slf4j
@RestController
@RequestMapping("/sys/sys-menu")
public class SysMenuController extends BaseController {

    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysRoleService iSystemRoleService;

    @LogAnnotation(operateContent = "拉取web端页面菜单", operateType = "系统日志")
    @ApiOperation("获取前端所需菜单")
    @GetMapping(value = "/build")
    public ResponseEntity<Object> buildMenus() {
        List<SysMenu> menuList = sysMenuService.getByUserId(SecurityUtils.getCurrentUserId());
        List<SysMenu> menuDtos = sysMenuService.buildTree(menuList);
        return new ResponseEntity<>(sysMenuService.buildMenus(menuDtos), HttpStatus.OK);
    }

    // @Log("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping
    //  @PreAuthorize("@el.check('menu:list')")
    public ResponseEntity<Object> query(SysMenu sysMenu, Pageable pageable) throws Exception {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysMenu> list = sysMenuService.queryAll(sysMenu, true);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy")
    // @PreAuthorize("@el.check('menu:list','roles:list')")
    public ResponseEntity<Object> query(@RequestParam String pid) {
        return new ResponseEntity<>(sysMenuService.getMenus(pid), HttpStatus.OK);
    }

    // @Log("查询菜单")
    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
//    @PreAuthorize("@el.check('menu:list')")
    public ResponseEntity<Object> getSuperior(@RequestBody List<String> ids) {
        Set<SysMenu> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (String id : ids) {
                SysMenu menuDto = sysMenuService.getById(id);
                menuDtos.addAll(sysMenuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(sysMenuService.buildTree(new ArrayList<>(menuDtos)), HttpStatus.OK);
        }
        return new ResponseEntity<>(sysMenuService.getMenus(null), HttpStatus.OK);
    }


    @ApiOperation(value = "系统菜单-新增", notes = "新增一条系统菜单的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysMenuService.save(sysMenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "系统菜单-编辑", notes = "编辑一条系统菜单的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysMenuService.updateById(sysMenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "系统菜单-删除", notes = "删除一条系统菜单的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysMenuService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
