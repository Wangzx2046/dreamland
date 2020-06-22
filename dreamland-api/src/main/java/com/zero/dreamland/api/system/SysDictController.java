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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping
    public ResponseEntity<Object> all(SysDict sysDict, Pageable pageable) {


        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysDict> list = sysDictService.list(sysDict);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "数据字典-新增", notes = "新增一条数据字典的记录")
    // @PreAuthorize("hasRole('admin')")
    @PostMapping
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
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysDictService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // @Log("导出字典数据")
    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    //  @PreAuthorize("@el.check('dict:list')")
    public void download(HttpServletResponse response, SysDict sysDict) throws IOException {
        sysDictService.download(sysDict, response);
    }


}
