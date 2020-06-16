package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Wang.zx
 * @Description ：系统菜单
 * @since : 2020-04-01
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemMenu对象", description = "系统菜单")
public class SystemMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否外链")
    private Boolean iFrame;//是否外链

    @ApiModelProperty(value = "菜单名称")
    @Length(max = 255, message = "菜单名称内容过长，请检查！")
    private String name;//菜单名称

    @ApiModelProperty(value = "组件")
    @Length(max = 255, message = "组件内容过长，请检查！")
    private String component;//组件

    @ApiModelProperty(value = "上级菜单ID")
    @NotBlank(message = "上级菜单ID不得为空！")
    @Length(max = 50, message = "上级菜单ID内容过长，请检查！")
    private String pid;//上级菜单ID

    @ApiModelProperty(value = "排序")
    private Long sort;//排序

    @ApiModelProperty(value = "图标")
    @Length(max = 255, message = "图标内容过长，请检查！")
    private String icon;//图标

    @ApiModelProperty(value = "链接地址")
    @Length(max = 255, message = "链接地址内容过长，请检查！")
    private String path;//链接地址

    @ApiModelProperty(value = "缓存")
    private Boolean cache;//缓存

    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;//隐藏

    @ApiModelProperty(value = "组件名称")
    @Length(max = 20, message = "组件名称内容过长，请检查！")
    private String componentName;//组件名称

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;//创建日期

    @ApiModelProperty(value = "权限")
    @Length(max = 255, message = "权限内容过长，请检查！")
    private String permission;//权限

    @ApiModelProperty(value = "类型")
    private Integer type;//类型

    @ApiModelProperty(value = "菜单级别")
    private Integer menuLevel;//菜单级别

    @ApiModelProperty(value = "菜单描述")
    @Length(max = 255, message = "菜单描述内容过长，请检查！")
    private String remark;//菜单描述

    @TableField(exist = false)
    private List<SystemMenu> children;
}
