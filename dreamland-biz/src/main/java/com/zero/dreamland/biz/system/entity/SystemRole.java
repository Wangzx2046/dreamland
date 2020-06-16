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

;

/**
 * @author : Wang.zx
 * @Description ：系统角色表
 * @since : 2019-08-22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemRole对象", description = "系统角色表")
public class SystemRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    public static  final SystemRole INSTANCE = new SystemRole();

    @ApiModelProperty(value = "角色编码")
    @Length(max = 32, message = "角色编码内容过长，请检查！")
    private String roleType;//角色编码

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不得为空！")
    @Length(max = 16, message = "角色名称内容过长，请检查！")
    private String roleName;//角色名称

    @ApiModelProperty(value = "排序")
    private Integer roleSort;//排序

    @ApiModelProperty(value = "是否系统默认 0 非默认 1默认")
    @Length(max = 1, message = "是否系统默认 0 非默认 1默认内容过长，请检查！")
    private String roleIsDefault;//是否系统默认 0 非默认 1默认

    @ApiModelProperty(value = "角色描述")
    @Length(max = 255, message = "角色描述内容过长，请检查！")
    private String content;//角色描述


}
