package com.zero.dreamland.api.system;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.system.entity.SysDict;
import com.zero.dreamland.biz.system.service.ISysDictService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：数据字典
 * @since : 2020-04-07
 */
@Api(tags = "系统：字典管理")
@Slf4j
@RestController
@RequestMapping("/sys/sys-dict")
public class SysDictController extends BaseController {

    @Resource
    private ISysDictService sysDictService;


    @ApiOperation(value = "数据字典-查看全部", notes = "列表查看数据字典的记录")
    //  @PreAuthorize("hasRole('admin')")
    @GetMapping(value = {"/all"})
    public ResponseEntity<Object> all(SysDict sysDict, Pageable pageable) {


        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysDict> list = sysDictService.list(sysDict);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "数据字典-新增", notes = "新增一条数据字典的记录")
    // @PreAuthorize("hasRole('admin')")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysDict sysDict, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDictService.save(sysDict);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "数据字典-编辑", notes = "编辑一条数据字典的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @ResponseBody
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysDict sysDict, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDictService.updateById(sysDict);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "数据字典-删除", notes = "删除一条数据字典的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysDictService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


   // @Log("导出字典数据")
    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('dict:list')")
    public void download(HttpServletResponse response,SysDict sysDict) throws IOException {
        sysDictService.download(sysDict, response);
    }


   /* @ApiOperation(value = "数据字典-查看列表", notes = "列表查看数据字典的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Result list(Model model, HttpServletRequest request, SystemDict systemDict,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "size", required = false, defaultValue = "20") Integer pageSize) {


        QueryWrapper<SystemDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(systemDict.getName()), "name", systemDict.getName());
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







    @ApiOperation(value = "数据字典-删除", notes = "删除一条数据字典的记录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = {"/{id}"})
    @ResponseBody
    public Result del(@NotBlank(message = "id should not be empty") @PathVariable("id") String id) {
        systemDictService.removeById(id);
        return ResultUtil.success();
    }
*/
/*

    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void download(HttpServletResponse response, SystemDict criteria) throws IOException {
        systemDictService.download(systemDictService.queryAll(criteria), response);
    }
*/


}
