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
* @Description ：岗位
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_job")
@ApiModel(value="SysJob对象", description="岗位")
public class SysJob extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位名称")
    @TableField("name")
    @NotBlank(message="岗位名称不得为空！")
    @Length(max=20 ,message="岗位名称内容过长，请检查！")
    private String name;//岗位名称

    @ApiModelProperty(value = "岗位状态")
    @TableField("enabled")
    @NotBlank(message="岗位状态不得为空！")
    private Boolean enabled;//岗位状态

    @ApiModelProperty(value = "排序")
    @TableField("job_sort")
    private Integer jobSort;//排序

    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;//创建日期

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;//更新时间


}
