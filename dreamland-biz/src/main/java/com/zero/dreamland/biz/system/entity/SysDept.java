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
import java.time.LocalDateTime;

/**
*
* @Description ：部门
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
@ApiModel(value="SysDept对象", description="部门")
public class SysDept extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级部门")
    @TableField("pid")
    @Length(max=32 ,message="上级部门内容过长，请检查！")
    private String pid;//上级部门

    @ApiModelProperty(value = "子部门数目")
    @TableField("sub_count")
    private Integer subCount;//子部门数目

    @ApiModelProperty(value = "名称")
    @TableField("name")
    @NotBlank(message="名称不得为空！")
    @Length(max=20 ,message="名称内容过长，请检查！")
    private String name;//名称

    @ApiModelProperty(value = "排序")
    @TableField("dept_sort")
    private Integer deptSort;//排序

    @ApiModelProperty(value = "状态")
    @TableField("enabled")
    @NotBlank(message="状态不得为空！")
    private Boolean enabled;//状态

    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;//创建日期

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;//更新时间


}
