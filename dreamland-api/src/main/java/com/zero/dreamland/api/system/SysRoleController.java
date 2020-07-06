package com.zero.dreamland.api.system;


import cn.hutool.core.lang.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.auth.utils.SecurityUtils;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Wang.zx
 * @Description ：系统角色表
 * @since : 2019-08-22
 */
@Api(tags = "系统角色表")
@Slf4j
@RestController
@RequestMapping("/sys/sys-role")
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService;

    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")
    // @PreAuthorize("@el.check('roles:list')")
    public ResponseEntity<Object> query(@PathVariable String id) {
        return new ResponseEntity<>(sysRoleService.getById(id), HttpStatus.OK);
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
   // @PreAuthorize("@el.check('roles:list','user:add','user:edit')")
    public ResponseEntity<Object> query() {
        return new ResponseEntity<>(sysRoleService.list(new SysRole()), HttpStatus.OK);
    }

    // @Log("查询角色")
    @ApiOperation("查询角色")
    @GetMapping
    //  @PreAuthorize("@el.check('roles:list')")
    public ResponseEntity<Object> query(SysRole sysRole, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysRole> list = sysRoleService.list(sysRole);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation("获取用户级别")
    @GetMapping(value = "/level")
    public ResponseEntity<Object> getLevel() {
        return new ResponseEntity<>(Dict.create().set("level", getLevels(null)), HttpStatus.OK);
    }

    /**
     * 获取用户的角色级别
     *
     * @return /
     */
    private int getLevels(Integer level) {
        List<Integer> levels = sysRoleService.getByUsersId(SecurityUtils.getCurrentUserId()).stream().map(SysRole::getLevel).collect(Collectors.toList());

        int min = Collections.min(levels);
        if (level != null) {
            if (level < min) {
                throw new BadRequestException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }

    @ApiOperation(value = "角色-新增", notes = "新增一条角色的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysRole sysRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }

        getLevels(sysRole.getLevel());
        sysRoleService.save(sysRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ApiOperation(value = "角色-编辑", notes = "编辑一条角色的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysRole sysRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        getLevels(sysRole.getLevel());
        sysRoleService.updateById(sysRole);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //  @Log("修改角色菜单")
    @ApiOperation("修改角色菜单")
    @PutMapping(value = "/menu")
//    @PreAuthorize("@el.check('roles:edit')")
    public ResponseEntity<Object> updateMenu(@RequestBody SysRole resources) {
        SysRole role = sysRoleService.getById(resources.getId());
        getLevels(role.getLevel());
        sysRoleService.updateMenu(resources, role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   // @Log("删除角色")
    @ApiOperation("删除角色")
    @DeleteMapping
    //@PreAuthorize("@el.check('roles:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<String> ids) {
        for (String id : ids) {
            SysRole role = sysRoleService.getById(id);
            getLevels(role.getLevel());
        }
        // 验证是否被用户关联
        sysRoleService.verification(ids);
        sysRoleService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*    @Log("导出角色数据")
    @ApiOperation("导出角色数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('role:list')")
    public void download(HttpServletResponse response, SysRole sysRole) throws IOException {
        sysRoleService.download(sysRoleService.queryAll(sysRole), response);
    }



**/


}
