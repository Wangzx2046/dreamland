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
* @Description ：数据字典详情
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_detail")
@ApiModel(value="SysDictDetail对象", description="数据字典详情")
public class SysDictDetail extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典id")
    @TableField("dict_id")
    @Length(max=32 ,message="字典id内容过长，请检查！")
    private String dictId;//字典id

    @ApiModelProperty(value = "字典标签")
    @TableField("label")
    @NotBlank(message="字典标签不得为空！")
    @Length(max=255 ,message="字典标签内容过长，请检查！")
    private String label;//字典标签

    @ApiModelProperty(value = "字典值")
    @TableField("value")
    @NotBlank(message="字典值不得为空！")
    @Length(max=20 ,message="字典值内容过长，请检查！")
    private String value;//字典值

    @ApiModelProperty(value = "排序")
    @TableField("dict_sort")
    private Integer dictSort;//排序

    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;//创建日期

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;//更新时间


}
