package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：
 * @since : 2020-06-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户")
    @TableField("user_name")
    @NotBlank(message = "账户不得为空！")
    @Length(max = 20, message = "账户内容过长，请检查！")
    private String userName;//账户

    @ApiModelProperty(value = "密码")
    @TableField("user_password")
    //@NotBlank(message = "密码不得为空！")
    @Length(max = 100, message = "密码内容过长，请检查！")
    @JsonIgnore
    private String userPassword;//密码

    @ApiModelProperty(value = "密码明文")
    @TableField("user_enable_password")
    @Length(max = 20, message = "密码明文内容过长，请检查！")
    @JsonIgnore
    private String userEnablePassword;//密码明文

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    @NotBlank(message = "昵称不得为空！")
    @Length(max = 20, message = "昵称内容过长，请检查！")
    private String nickName;//昵称

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    @NotBlank(message = "联系电话不得为空！")
    @Pattern(message = "非法手机号码", regexp = "^1[\\d]{10}$")
    private String phone;//联系电话

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    @Length(max = 50, message = "邮箱内容过长，请检查！")
    private String email;//邮箱

    @ApiModelProperty(value = "人脸照片")
    @TableField("face_img")
    @Length(max = 200, message = "人脸照片内容过长，请检查！")
    private String faceImg;//人脸照片

    @ApiModelProperty(value = "头像地址")
    @TableField("avatar_name")
    @Length(max = 255, message = "头像地址内容过长，请检查！")
    private String avatarName;//头像地址

    @ApiModelProperty(value = "头像真实路径")
    @TableField("avatar_path")
    @Length(max = 255, message = "头像真实路径内容过长，请检查！")
    private String avatarPath;//头像真实路径

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    @Length(max = 2, message = "性别内容过长，请检查！")
    private String gender;//性别

    @ApiModelProperty(value = "组织ID")
    @TableField("organization_id")
    @Length(max = 32, message = "组织ID内容过长，请检查！")
    private String organizationId;//组织ID

    @ApiModelProperty(value = "部门ID")
    @TableField("dept_id")
    @Length(max = 32, message = "部门ID内容过长，请检查！")
    private String deptId;//部门ID

    @ApiModelProperty(value = "岗位ID")
    @TableField("position_id")
    @Length(max = 32, message = "岗位ID内容过长，请检查！")
    private String positionId;//岗位ID

    @ApiModelProperty(value = "工号")
    @TableField("employee_id")
    @Length(max = 32, message = "工号内容过长，请检查！")
    private String employeeId;//工号

    @ApiModelProperty(value = "最后访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("last_login_date")
    private LocalDateTime lastLoginDate;//最后访问时间

    @ApiModelProperty(value = "修改密码的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("pwd_reset_time")
    private LocalDateTime pwdResetTime;//修改密码的时间

    @JsonIgnore
    @ApiModelProperty(value = "是否为admin账号")
    @TableField("is_admin")
    private Boolean isAdmin;//是否为admin账号

    @ApiModelProperty(value = "状态：1启用、0禁用")
    @TableField("enabled")
    private Boolean enabled;//状态：1启用、0禁用


    @TableField(exist = false)
    private Set<SysRole> roles;

    @TableField(exist = false)
    private Set<SysJob> jobs;

    @TableField(exist = false)
    private SysDept dept;

    @TableField(exist = false)
    private Set<String> deptIds=new HashSet<>();

}
