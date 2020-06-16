package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author : Wang.zx
 * @Description ：用户角色关联
 * @since : 2020-06-11
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_users_roles")
@ApiModel(value = "SysUsersRoles对象", description = "用户角色关联")
public class SysUsersRoles  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    @NotBlank(message = "用户ID不得为空！")
    private String userId;//用户ID

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    @NotBlank(message = "角色ID不得为空！")
    private String roleId;//角色ID


}
