package com.zero.dreamland.api.testApi;

import com.zero.dreamland.biz.system.service.ISystemUserService;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ISystemUserService iSystemUserService;


    @GetMapping("/get")
    public Result getJwt() {

        return ResultUtil.success(iSystemUserService.list());
    }
}
