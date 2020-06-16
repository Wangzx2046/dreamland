package com.zero.dreamland.api.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.dreamland.biz.system.entity.SystemRole;
import com.zero.dreamland.biz.system.service.ISystemRoleService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @author : Wang.zx
 * @Description ：系统角色表
 * @since : 2019-08-22
 */
@Api(tags = "系统角色表")
@Slf4j
@RestController
@RequestMapping("/system/system-role")
public class SystemRoleController {

    @Resource
    private ISystemRoleService systemRoleService;

    @ApiOperation(value = "系统角色表-查看", notes = "列表查看系统角色表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/list"})
    public Result list(Model model, HttpServletRequest request, SystemRole systemRole,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<SystemRole> pages = systemRoleService.page(new Page<SystemRole>(pageNum, pageSize), null);
        return ResultUtil.success(pages);
    }

    @ApiOperation(value = "系统角色表-查看", notes = "列表查看系统角色表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    public Result maps(Model model, HttpServletRequest request, SystemRole systemRole,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemRoleService.page(new Page(pageNum, pageSize), null);
        return ResultUtil.success(pageMap);
    }

    @ApiOperation(value = "系统角色表-新增", notes = "新增一条系统角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = {"/add"})
    public Result add(@Validated({AddGroup.class}) SystemRole systemRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemRoleService.save(systemRole);
        return ResultUtil.success();
    }

    @ApiOperation(value = "系统角色表-编辑", notes = "编辑一条系统角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = {"/edit"})
    public Result edit(@Validated({UpdateGroup.class}) SystemRole systemRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemRoleService.updateById(systemRole);
        return ResultUtil.success();
    }

    @ApiOperation(value = "系统角色表-删除", notes = "删除一条系统角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/del/{id}"})
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemRoleService.removeById(id);
        return ResultUtil.success();
    }


}
