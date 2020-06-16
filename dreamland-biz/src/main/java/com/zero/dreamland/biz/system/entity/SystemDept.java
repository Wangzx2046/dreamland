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
*
* @Description ：系统部门表
*
* @author : Wang.zx
* @since : 2020-04-03
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemDept对象", description="系统部门表")
public class SystemDept extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    @NotBlank(message="名称不得为空！")
    @Length(max=255 ,message="名称内容过长，请检查！")
    private String name;//名称

    @ApiModelProperty(value = "上级部门")
    @NotBlank(message="上级部门不得为空！")
    private String pid;//上级部门

    @ApiModelProperty(value = "状态")
    @NotBlank(message="状态不得为空！")
    private Boolean enabled;//状态

    @ApiModelProperty(value = "备注信息")
    @Length(max=255 ,message="备注信息内容过长，请检查！")
    private String remarks;//备注信息


}
