package com.zero.dreamland.api.system;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.system.entity.SysJob;
import com.zero.dreamland.biz.system.service.ISysJobService;
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
 * @Description ：岗位
 * @since : 2020-06-19
 */
@Api(tags = "岗位")
@Slf4j
@RestController
@RequestMapping("/sys/sys-job")
public class SysJobController extends BaseController {

    @Resource
    private ISysJobService sysJobService;


    @ApiOperation(value = "岗位-查看", notes = "列表查看岗位的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> all(SysJob sysJob, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysJob> list = sysJobService.list(sysJob);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "岗位-新增", notes = "新增一条岗位的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysJob sysJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysJobService.save(sysJob);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "岗位-编辑", notes = "编辑一条岗位的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysJob sysJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysJobService.updateById(sysJob);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "岗位-删除", notes = "删除一条岗位的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysJobService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // @Log("导出岗位数据")
    @ApiOperation("导出岗位数据")
    @GetMapping(value = "/download")
    //  @PreAuthorize("@el.check('job:list')")
    public void download(HttpServletResponse response, SysJob sysJob) throws IOException {
        sysJobService.download(sysJobService.list(sysJob), response);
    }


}
