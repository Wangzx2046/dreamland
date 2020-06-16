package com.zero.dreamland.api.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.dreamland.biz.system.entity.SystemPermi;
import com.zero.dreamland.biz.system.service.ISystemPermiService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @Description ：系统权限表
 * Author wzx
 * Date  2019-07-31
 */
@Api(tags = "系统权限表")
@Slf4j
@Controller
@RequestMapping(value = "/mappers/system/system-permi")
public class SystemPermiController extends BaseController {
    @Autowired
    private ISystemPermiService systemPermiService;

    @ApiOperation(value = "系统权限表-查看", notes = "列表查看系统权限表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/list"})
    @ResponseBody
    public Result list(Model model, HttpServletRequest request, SystemPermi systemPermi,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        IPage<SystemPermi> pages = systemPermiService.page(new Page<SystemPermi>(pageNum, pageSize), null);
        return ResultUtil.success(pages);
    }

    @ApiOperation(value = "系统权限表-查看", notes = "列表查看系统权限表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    @ResponseBody
    public Result maps(Model model, HttpServletRequest request, SystemPermi systemPermi,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemPermiService.page(new Page(pageNum, pageSize), null);
        return ResultUtil.success(pageMap);
    }

    @ApiOperation(value = "系统权限表-查看", notes = "列表查看系统权限表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = {"/add"})
    @ResponseBody
    public Result add(@Validated({AddGroup.class}) SystemPermi systemPermi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemPermiService.save(systemPermi);
        return ResultUtil.success();
    }

    @ApiOperation(value = "系统权限表-查看", notes = "列表查看系统权限表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = {"/edit"})
    @ResponseBody
    public Result edit(@Validated({UpdateGroup.class}) SystemPermi systemPermi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemPermiService.updateById(systemPermi);
        return ResultUtil.success();
    }

    @ApiOperation(value = "系统权限表-查看", notes = "列表查看系统权限表的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/del/{id}"})
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemPermiService.removeById(id);
        return ResultUtil.success();
    }


}
