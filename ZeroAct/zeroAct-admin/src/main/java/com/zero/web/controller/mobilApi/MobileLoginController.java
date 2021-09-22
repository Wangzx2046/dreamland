package com.zero.web.controller.mobilApi;

import com.alibaba.fastjson.JSONObject;
import com.zero.common.core.domain.Result;
import com.zero.common.core.domain.entity.SysUser;
import com.zero.common.core.domain.model.LoginBody;
import com.zero.common.utils.SecurityUtils;
import com.zero.framework.web.service.SysLoginService;
import com.zero.framework.web.service.SysPermissionService;
import com.zero.system.service.ISysMenuService;
import com.zero.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * @author zero
 */
@RestController
public class MobileLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/mlogin")
    public Result<JSONObject> mlogin(@RequestBody LoginBody loginBody) {
        Result<JSONObject> result = new Result<JSONObject>();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());

        SysUser user =sysUserService.selectUserByUserName(loginBody.getUsername()) ;

        JSONObject obj = new JSONObject();

        //用户登录信息
        obj.put("userInfo", user);

        //token 信息
        obj.put("token", token);
        result.setResult(obj);
        result.setSuccess(true);
        result.setCode(200);

        return result;
    }


}
