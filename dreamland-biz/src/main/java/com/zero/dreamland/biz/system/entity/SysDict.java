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
* @Description ：数据字典
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="SysDict对象", description="数据字典")
public class SysDict extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    @TableField("name")
    @NotBlank(message="字典名称不得为空！")
    @Length(max=255 ,message="字典名称内容过长，请检查！")
    private String name;//字典名称

    @ApiModelProperty(value = "描述")
    @TableField("description")
    @Length(max=255 ,message="描述内容过长，请检查！")
    private String description;//描述

    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;//创建日期

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;//更新时间


}
