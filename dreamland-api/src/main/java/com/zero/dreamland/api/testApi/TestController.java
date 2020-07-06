package com.zero.dreamland.api.testApi;

import com.zero.dreamland.biz.system.service.ISysUserService;
import com.zero.dreamland.common.annotation.AnonymousAccess;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: TestController
 * @Description: 测试类*
 * @Author: wzx
 * @Date: 2019-08-30 14:33
 * @Version 1.0.0
 **/
@RestController
@RequestMapping("test")
public class TestController {


    @Resource
    private ISysUserService iSystemUserService;

    @AnonymousAccess
    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")//http://192.168.30.112:8088/dreamland/test/1
    // @PreAuthorize("@el.check('roles:list')")
    public ResponseEntity<Object> query(@PathVariable String id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

   // @PreAuthorize("admin")
    @PreAuthorize("hasRoles({'ADMIN','dept:list'})")
    @GetMapping(value = "/a/{id}")//http://192.168.30.112:8088/dreamland/test/a/1
    // @PreAuthorize("@el.check('roles:list')")
    public ResponseEntity<Object> aquery(@PathVariable String id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
