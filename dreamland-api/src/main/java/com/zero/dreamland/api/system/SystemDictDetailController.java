package com.zero.dreamland.api.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.dreamland.biz.system.entity.SystemDictDetail;
import com.zero.dreamland.biz.system.service.ISystemDictDetailService;
import com.zero.dreamland.biz.system.vo.SystemDictDetailVo;
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
import org.springframework.web.bind.annotation.RequestBody;
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
 * @Description ：数据字典详情
 * @since : 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/system/system-dict-detail")
public class SystemDictDetailController extends BaseController {

    @Resource
    private ISystemDictDetailService systemDictDetailService;


    @ApiOperation(value = "数据字典详情-查看", notes = "列表查看数据字典详情的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Result list(Model model, HttpServletRequest request, SystemDictDetailVo systemDictDetail,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "size", required = false, defaultValue = "20") Integer pageSize) {
      /*  QueryWrapper<SystemDictDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(systemDictDetail.getDictId()), "dict_id", systemDictDetail.getDictId());
        queryWrapper.like(StringUtils.isNotBlank(systemDictDetail.getLabel()), "label", systemDictDetail.getLabel());
        List<SystemDictDetail> list = systemDictDetailService.list(queryWrapper);
        IPage<SystemDictDetail> pages = systemDictDetailService.page(new Page<SystemDictDetail>(pageNum, pageSize), queryWrapper);
*/
       /* PageHelper.startPage(pageNum,pageSize);
        List<SystemDictDetail> list=systemDictDetailService.list(systemDictDetail);
        PageInfo pageInfo=new PageInfo<>(list);

        return ResultUtil.success(pageInfo);*/
        return null;
    }


    @ApiOperation(value = "数据字典详情-查看", notes = "列表查看数据字典详情的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/maps"})
    @ResponseBody
    public Result maps(Model model, HttpServletRequest request, SystemDictDetail systemDictDetail,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        IPage<HashMap<String, Object>> pageMap = systemDictDetailService.page(new Page(pageNum, pageSize), null);

        return ResultUtil.success(pageMap);
    }


    @ApiOperation(value = "数据字典详情-新增", notes = "新增一条数据字典详情的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public Result add(@Validated({AddGroup.class}) @RequestBody SystemDictDetail systemDictDetail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDictDetailService.save(systemDictDetail);
        return ResultUtil.success();
    }


    @ApiOperation(value = "数据字典详情-编辑", notes = "编辑一条数据字典详情的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @ResponseBody
    public Result edit(@Validated({UpdateGroup.class}) @RequestBody SystemDictDetail systemDictDetail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(20000, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        systemDictDetailService.updateById(systemDictDetail);
        return ResultUtil.success();
    }


    @ApiOperation(value = "数据字典详情-删除", notes = "删除一条数据字典详情的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/{id}"})
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemDictDetailService.removeById(id);
        return ResultUtil.success();
    }


}
