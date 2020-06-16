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
* @Description ：数据字典
*
* @author : Wang.zx
* @since : 2020-04-07
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemDict对象", description="数据字典")
public class SystemDict extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    @NotBlank(message="字典名称不得为空！")
    @Length(max=255 ,message="字典名称内容过长，请检查！")
    private String name;//字典名称

    @ApiModelProperty(value = "描述")
    @Length(max=255 ,message="描述内容过长，请检查！")
    private String remark;//描述


}
