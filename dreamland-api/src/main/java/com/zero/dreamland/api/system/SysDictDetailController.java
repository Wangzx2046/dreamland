package com.zero.dreamland.api.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.system.entity.SysDict;
import com.zero.dreamland.biz.system.entity.SysDictDetail;
import com.zero.dreamland.biz.system.service.ISysDictDetailService;
import com.zero.dreamland.biz.system.service.ISysDictService;
import com.zero.dreamland.biz.system.vo.SysDictDetailVo;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.exception.BadRequestException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：数据字典详情
 * @since : 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/sys/sys-dict-detail")
public class SysDictDetailController extends BaseController {

    @Resource
    private ISysDictDetailService sysDictDetailService;
    @Resource
    private ISysDictService sysDictService;


    @ApiOperation(value = "数据字典详情-查看", notes = "列表查看数据字典详情的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> list(SysDictDetailVo sysDictDetail,
                                       Pageable pageable) {

        if (StringUtils.isNotBlank(sysDictDetail.getDictName())) {
            sysDictDetail.setDictId(
                    sysDictService.getOne(
                            new QueryWrapper<SysDict>()
                                    .eq("name", sysDictDetail.getDictName()))
                            .getId()
            );
            sysDictDetail.setDictName(null);
        }


        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysDictDetail> list = sysDictDetailService.list(sysDictDetail);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);

    }


    @ApiOperation(value = "数据字典详情-新增", notes = "新增一条数据字典详情的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysDictDetail sysDictDetail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDictDetailService.save(sysDictDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "数据字典详情-编辑", notes = "编辑一条数据字典详情的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @ResponseBody
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysDictDetail sysDictDetail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDictDetailService.updateById(sysDictDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "数据字典详情-删除", notes = "删除一条数据字典详情的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysDictDetailService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


/*
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
    }*/


}
