package com.zero.dreamland.biz.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.zero.dreamland.biz.common.base.BaseModel;
import com.zero.dreamland.common.MyValidation.AddGroup;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description ：系统用户表
 * @Author wzx
 * @Date 2019-07-18T14:07:41.339
 */
@Data
@ToString(callSuper = true)
@TableName("system_user")
public class SystemUser extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max = 20, message = "账户长度不能超过20")
    @NotBlank(message = "账户不能为空", groups = {AddGroup.class})
    @TableField(value = "user_name")
    private String userName;//账户

    @Length(max = 50, message = "密码长度不能超过50")
    @JsonIgnore
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
    private String userPassword;//密码

    @Length(max = 20, message = "密码明文长度不能超过20")
    @JsonIgnore
    private String userEnablePassword;//密码明文

    @Length(max = 20, message = "昵称长度不能超过20")
    @NotBlank(message = "昵称不能为空")
    private String nickName;//昵称

    @Length(max = 255, message = "用户头像长度不能超过20")
    private String userAvatar;//用户头像

    private String gender;

    private Integer phone;//联系电话

    @Email(message = "请使用合法的邮箱格式")
    @Length(max = 50, message = "邮箱长度不能超过50")
    private String email;//邮箱

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;//最后访问时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pwdResetTime;

    @JsonIgnore
    private Boolean isAdmin = false;

    private Boolean enabled;

    @TableField(exist = false)
    private List<SystemRole> roles = Lists.newArrayList();//角色列表
    @TableField(exist = false)
    private List<SystemPermi> permissions = Lists.newArrayList();//权限列表


}