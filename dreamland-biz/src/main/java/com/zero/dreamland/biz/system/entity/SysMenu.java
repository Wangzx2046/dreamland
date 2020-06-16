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
import java.util.List;

/**
*
* @Description ：系统菜单
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="系统菜单")
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级菜单ID")
    @TableField("pid")
    @NotBlank(message="上级菜单ID不得为空！")
    @Length(max=32 ,message="上级菜单ID内容过长，请检查！")
    private String pid;//上级菜单ID

    @ApiModelProperty(value = "子菜单数目")
    @TableField("sub_count")
    private Integer subCount;//子菜单数目

    @ApiModelProperty(value = "菜单类型")
    @TableField("type")
    private Integer type;//菜单类型

    @ApiModelProperty(value = "菜单标题")
    @TableField("title")
    @Length(max=255 ,message="菜单标题内容过长，请检查！")
    private String title;//菜单标题

    @ApiModelProperty(value = "组件名称")
    @TableField("name")
    @Length(max=255 ,message="组件名称内容过长，请检查！")
    private String name;//组件名称

    @ApiModelProperty(value = "组件")
    @TableField("component")
    @Length(max=255 ,message="组件内容过长，请检查！")
    private String component;//组件

    @ApiModelProperty(value = "排序")
    @TableField("menu_sort")
    private Long menuSort;//排序

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    @Length(max=255 ,message="图标内容过长，请检查！")
    private String icon;//图标

    @ApiModelProperty(value = "链接地址")
    @TableField("path")
    @Length(max=255 ,message="链接地址内容过长，请检查！")
    private String path;//链接地址

    @ApiModelProperty(value = "是否外链")
    @TableField("i_frame")
    private Boolean iFrame;//是否外链

    @ApiModelProperty(value = "缓存")
    @TableField("cache")
    private Boolean cache;//缓存

    @ApiModelProperty(value = "隐藏")
    @TableField("hidden")
    private Boolean hidden;//隐藏

    @ApiModelProperty(value = "权限")
    @TableField("permission")
    @Length(max=255 ,message="权限内容过长，请检查！")
    private String permission;//权限

    @ApiModelProperty(value = "菜单级别")
    @TableField("menu_level")
    private Integer menuLevel;//菜单级别

    @ApiModelProperty(value = "菜单描述")
    @TableField("remark")
    @Length(max=255 ,message="菜单描述内容过长，请检查！")
    private String remark;//菜单描述


    @TableField(exist = false)
    private List<SysMenu> children;
}
