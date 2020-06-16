package com.zero.dreamland.api.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.biz.system.entity.SystemUser;
import com.zero.dreamland.biz.system.service.ISystemUserService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import java.util.List;

/**
 * @ClassName: SystemUserController
 * @Description: 系统用户表*
 * @Author: wzx
 * @Date: 2019-07-18 15:33
 * @Version 1.0.0
 **/
@Api(tags = "系统用户表")
@Slf4j
@RequestMapping("/mappers/system/system-user")
@RestController
public class SystemUserController extends BaseController {
    @Resource
    private ISystemUserService systemUserService;


    @GetMapping("/currentUser")
    public Result getMyInfo() {
        return ResultUtil.success(getCurrentUser());
    }



    @ApiOperation(value = "系统用户表-查看", notes = "列表查看系统用户表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/pagelist"})
    @ResponseBody
    public Result pagelist(Model model, HttpServletRequest request, SystemUser systemUser,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(systemUser.getUserName())) {
            queryWrapper.eq("user_name",systemUser.getUserName());
        }

        //PageHelper分页
        PageHelper.startPage(pageNum, pageSize);
        List<SystemUser> userList = systemUserService.list( queryWrapper);
        PageInfo<SystemUser> pageInfo = new PageInfo<>(userList);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation(value = "系统用户表-查看", notes = "列表查看系统用户表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/list"})
    @ResponseBody
    public Result list(Model model, HttpServletRequest request, SystemUser systemUser,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(systemUser.getUserName())) {
            queryWrapper.eq("user_name",systemUser.getUserName());
        }
        //mp分页
        IPage<SystemUser> pages = systemUserService.page(new Page<SystemUser>(pageNum, pageSize), queryWrapper);
        return ResultUtil.success(pages);
    }

    @ApiOperation(value = "系统用户表-查看", notes = "列表查看系统用户表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    @ResponseBody
    public Result maps(Model model, HttpServletRequest request, SystemUser systemUser,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemUserService.page(new Page(pageNum, pageSize), null);
        return ResultUtil.success(pageMap);
    }

    @ApiOperation(value = "系统用户表-新增", notes = "新增系统用户")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = {"/add"})
    @ResponseBody
    public Result add(@Validated({AddGroup.class}) SystemUser systemUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }

        return systemUserService.add(systemUser);
    }

    @ApiOperation(value = "系统用户表-编辑", notes = "编辑系统用户")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = {"/edit"})
    @ResponseBody
    public Result edit(@Validated({UpdateGroup.class}) SystemUser systemUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemUserService.updateById(systemUser);
        return ResultUtil.success();
    }

    @ApiOperation(value = "系统用户表-删除", notes = "删除系统用户")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/del/{id}"})
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemUserService.removeById(id);
        return ResultUtil.success();
    }


}
