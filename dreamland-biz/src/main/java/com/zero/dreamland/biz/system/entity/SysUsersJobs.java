package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author : Wang.zx
 * @Description ：
 * @since : 2020-06-11
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_users_jobs")
@ApiModel(value = "SysUsersJobs对象", description = "")
public class SysUsersJobs {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    @NotBlank(message = "用户ID不得为空！")
    private Long userId;//用户ID

    @ApiModelProperty(value = "岗位ID")
    @TableField("job_id")
    @NotBlank(message = "岗位ID不得为空！")
    private Long jobId;//岗位ID


}
