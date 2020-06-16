package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
*
* @Description ：系统角色表
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="系统角色表")
public class SysRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编码")
    @TableField("role_type")
    @Length(max=32 ,message="角色编码内容过长，请检查！")
    private String roleType;//角色编码

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    @NotBlank(message="角色名称不得为空！")
    @Length(max=16 ,message="角色名称内容过长，请检查！")
    private String roleName;//角色名称

    @ApiModelProperty(value = "角色级别")
    @TableField("level")
    private Integer level;//角色级别

    @ApiModelProperty(value = "排序")
    @TableField("role_sort")
    private Integer roleSort;//排序

    @ApiModelProperty(value = "数据权限")
    @TableField("data_scope")
    @Length(max=255 ,message="数据权限内容过长，请检查！")
    private String dataScope;//数据权限

    @ApiModelProperty(value = "是否系统默认 0 非默认 1默认")
    @TableField("role_is_default")
    @Length(max=1 ,message="是否系统默认 0 非默认 1默认内容过长，请检查！")
    private String roleIsDefault;//是否系统默认 0 非默认 1默认

    @ApiModelProperty(value = "角色描述")
    @TableField("description")
    @Length(max=255 ,message="角色描述内容过长，请检查！")
    private String description;//角色描述

    @TableField(exist = false)
    private Set<SysMenu> menus;

    @TableField(exist = false)
    private Set<SysDept> depts;
}
