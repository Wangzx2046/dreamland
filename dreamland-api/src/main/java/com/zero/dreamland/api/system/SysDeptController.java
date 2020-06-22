package com.zero.dreamland.api.system;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.exception.BadRequestException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：系统部门表
 * @since : 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/sys/sys-dept")
public class SysDeptController extends BaseController {

    @Resource
    private ISysDeptService sysDeptService;


    @ApiOperation(value = "系统部门表-查看", notes = "列表查看系统部门表的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> all(SysDept sysDept) throws Exception {

        PageHelper.startPage(1, 99999);
        List<SysDept> list = sysDeptService.queryAll(sysDept, true);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "系统部门表-新增", notes = "新增一条系统部门表的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysDept sysDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDeptService.save(sysDept);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ApiOperation(value = "系统部门表-编辑", notes = "编辑一条系统部门表的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysDept sysDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysDeptService.updateById(sysDept);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "系统部门表-删除", notes = "删除一条系统部门表的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysDeptService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //  @Log("查询部门")
    @ApiOperation("查询部门:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    //  @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> getSuperior(@RequestBody List<String> ids) {
        Set<SysDept> deptDtos = new LinkedHashSet<>();
        for (String id : ids) {
            SysDept deptDto = sysDeptService.getById(id);
            List<SysDept> depts = sysDeptService.getSuperior(deptDto, new ArrayList<>());
            deptDtos.addAll(depts);
        }
        return new ResponseEntity<>(sysDeptService.buildTree(new ArrayList<>(deptDtos)), HttpStatus.OK);
    }



    // @Log("导出字典数据")
    @ApiOperation("导出部门数据")
    @GetMapping(value = "/download")
    //  @PreAuthorize("@el.check('dict:list')")
    public void download(HttpServletResponse response, SysDept sysDept) throws IOException {
        sysDeptService.download(sysDept, response);
    }

}
