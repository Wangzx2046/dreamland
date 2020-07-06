package com.zero.dreamland.api.system;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.auth.utils.SecurityUtils;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.ISysUserService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：
 * @since : 2020-06-23
 */
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/sys/sys-user")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysUsersRolesService iSysUsersRolesService;


    @ApiOperation(value = "-查看", notes = "列表查看的记录")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')||hasAuthority('user:list')")
    @GetMapping
    public ResponseEntity<Object> all(SysUser sysUser, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysUser> list = sysUserService.list(sysUser, SecurityUtils.getCurrentUsername());
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "-新增", notes = "新增一条的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysUser sysUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysUserService.save(sysUser, SecurityUtils.getCurrentUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "-编辑", notes = "编辑一条的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysUser sysUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        sysUserService.updateById(sysUser, SecurityUtils.getCurrentUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "-删除", notes = "删除一条的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        sysUserService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
