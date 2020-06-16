package com.zero.dreamland.api.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.dreamland.biz.system.entity.SystemDept;
import com.zero.dreamland.biz.system.service.ISystemDeptService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @author : Wang.zx
 * @Description ：系统部门表
 * @since : 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/system/system-dept")
public class SystemDeptController extends BaseController {

    @Resource
    private ISystemDeptService systemDeptService;


    @ApiOperation(value = "系统部门表-查看", notes = "列表查看系统部门表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Result list(Model model, HttpServletRequest request, SystemDept systemDept,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "size", required = false, defaultValue = "20") Integer pageSize) {
        IPage<SystemDept> pages = systemDeptService.page(new Page<SystemDept>(pageNum, pageSize), null);
        return ResultUtil.success(pages);
    }


    @ApiOperation(value = "系统部门表-查看", notes = "列表查看系统部门表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    @ResponseBody
    public Result maps(Model model, HttpServletRequest request, SystemDept systemDept,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemDeptService.page(new Page(pageNum, pageSize), null);
        return ResultUtil.success(pageMap);
    }


    @ApiOperation(value = "系统部门表-新增", notes = "新增一条系统部门表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public Result add(@Validated({AddGroup.class}) SystemDept systemDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDeptService.save(systemDept);
        return ResultUtil.success();
    }


    @ApiOperation(value = "系统部门表-编辑", notes = "编辑一条系统部门表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @ResponseBody
    public Result edit(@Validated({UpdateGroup.class}) SystemDept systemDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDeptService.updateById(systemDept);
        return ResultUtil.success();
    }


    @ApiOperation(value = "系统部门表-删除", notes = "删除一条系统部门表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemDeptService.removeById(id);
        return ResultUtil.success();
    }


}
