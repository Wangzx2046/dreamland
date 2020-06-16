package com.zero.dreamland.api.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.biz.system.entity.SystemDict;
import com.zero.dreamland.biz.system.service.ISystemDictService;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @author : Wang.zx
 * @Description ：数据字典
 * @since : 2020-04-07
 */
@Api(tags = "系统：字典管理")
@Slf4j
@RestController
@RequestMapping("/system/system-dict")
public class SystemDictController extends BaseController {

    @Resource
    private ISystemDictService systemDictService;


    @ApiOperation(value = "数据字典-查看全部", notes = "列表查看数据字典的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/all"})
    public Result all(Model model, HttpServletRequest request, SystemDict systemDict,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {


        QueryWrapper<SystemDict> queryWrapper = new QueryWrapper<>();
        PageHelper.startPage(pageNum,pageSize);
        List<SystemDict> list = systemDictService.list(queryWrapper);
        PageInfo pageInfo=new PageInfo<>(list);

        //    IPage<SystemDict> pages = systemDictService.page(new Page<SystemDict>(pageNum, pageSize), null);
        return ResultUtil.success(pageInfo);
    }


    @ApiOperation(value = "数据字典-查看列表", notes = "列表查看数据字典的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Result list(Model model, HttpServletRequest request, SystemDict systemDict,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "size", required = false, defaultValue = "20") Integer pageSize) {


        QueryWrapper<SystemDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(systemDict.getName()) ,"name",systemDict.getName());
        List<SystemDict> list = systemDictService.list(queryWrapper);
        IPage<SystemDict> pages = systemDictService.page(new Page<SystemDict>(pageNum, pageSize), queryWrapper);
        return ResultUtil.success(pages);
    }


    @ApiOperation(value = "数据字典-查看", notes = "列表查看数据字典的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    @ResponseBody
    public Result maps(Model model, HttpServletRequest request, SystemDict systemDict,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemDictService.page(new Page(pageNum, pageSize), null);
        QueryWrapper<SystemDict> queryWrapper = new QueryWrapper<>();

        //    IPage<SystemDict> pages = systemDictService.page(new Page<SystemDict>(pageNum, pageSize), null);
        IPage<SystemDict> pages = systemDictService.page(new Page<SystemDict>(pageNum, pageSize), queryWrapper);
        return ResultUtil.success(pages);
    }


    @ApiOperation(value = "数据字典-新增", notes = "新增一条数据字典的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public Result add(@Validated({AddGroup.class}) @RequestBody SystemDict systemDict, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDictService.save(systemDict);
        return ResultUtil.success();
    }


    @ApiOperation(value = "数据字典-编辑", notes = "编辑一条数据字典的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @ResponseBody
    public Result edit(@Validated({UpdateGroup.class}) @RequestBody SystemDict systemDict, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDictService.updateById(systemDict);
        return ResultUtil.success();
    }


    @ApiOperation(value = "数据字典-删除", notes = "删除一条数据字典的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/{id}"})
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemDictService.removeById(id);
        return ResultUtil.success();
    }

/*

    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void download(HttpServletResponse response, SystemDict criteria) throws IOException {
        systemDictService.download(systemDictService.queryAll(criteria), response);
    }
*/


}
