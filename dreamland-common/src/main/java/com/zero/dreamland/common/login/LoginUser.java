package com.zero.dreamland.common.login;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @ClassName: SysUser
 * @Description: 系统用户表*
 * @Author: wzx
 * @Date: 2019-04-12 15:50
 * @Version 1.0.0
 **/

@Data
@TableName("system_user")
public class LoginUser {

    @NotBlank(message = "账户不能为空")
    @TableField("user_name")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @TableField("user_password")
    private String passWord;

    @NotBlank(message = "验证码不能为空")
    private String code;

    @NotBlank(message = "加载异常，请刷新后重试！")
    private String uuid;
}
