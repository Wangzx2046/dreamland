package com.zero.dreamland.biz.system.entity;

import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author : Wang.zx
 * @Description ：数据字典详情
 * @since : 2020-04-07
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDictDetail对象", description = "数据字典详情")
public class SystemDictDetail extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典标签")
    @NotBlank(message = "字典标签不得为空！")
    @Length(max = 255, message = "字典标签内容过长，请检查！")
    private String label;//字典标签

    @ApiModelProperty(value = "字典值")
    @NotBlank(message = "字典值不得为空！")
    @Length(max = 255, message = "字典值内容过长，请检查！")
    private String value;//字典值

    @ApiModelProperty(value = "排序")
    @Length(max = 255, message = "排序内容过长，请检查！")
    private String sort;//排序

    @ApiModelProperty(value = "字典id")
    @Length(max = 32, message = "字典id内容过长，请检查！")
    private String dictId;//字典id


   // @TableField(exist = false)
    private SystemDict dict;


}
