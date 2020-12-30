package com.zero.dreamland.api.common.captcha;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.zero.dreamland.common.annotation.AnonymousAccess;
import com.zero.dreamland.common.properties.Constant;
import com.zero.dreamland.redis.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CaprchaController
 * @Description: 验证码
 * @Author: wzx
 * @Date: 2019-08-30 14:33
 * @Version 1.0.0
 **/
@Api(tags = "验证码")
@RestController
@RequestMapping("captcha")
public class CaprchaController {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${login.verification_code.expiration}")
    private Long expiration;


    /***
     *@Description:/获取验证码
     *@Method_Name：getCaptcha
     *@Param            : []
     *@return           : com.zero.devil.common.returnMsg.Result
     *@Creation Date    ：2020/3/1616:39
     *@Author           ：wzx
     */
    @AnonymousAccess
    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public ResponseEntity<Object> captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = Constant.PREFIX_VERIFICATION_CODE_KEY+ IdUtil.simpleUUID();
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};

        // 存入redis并设置过期时间为yml中的expiration（分钟）
        redisUtil.setEx(uuid, result.toLowerCase(), expiration, TimeUnit.MINUTES);
        // 将key和base64返回给前端
        return ResponseEntity.ok(imgResult);
    }


    @AnonymousAccess
    @ApiOperation("校验验证码")
    @PostMapping("/checkCaptcha")
    public ResponseEntity<Object> checkCaptcha(@NotNull @RequestParam String verCode, @NotNull @RequestParam String verKey) {
        // 获取redis中的验证码
        String redisCode = (String) redisUtil.get(verKey);
        // 判断验证码
        if (verCode == null || !redisCode.equals(verCode.trim().toLowerCase())) {
            return ResponseEntity.ok("验证码不正确");
        } else {
            return ResponseEntity.ok("验证码正确");
        }
    }


}
