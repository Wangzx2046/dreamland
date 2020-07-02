package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

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

    @ApiModelProperty(value = "主键ID")
    @Length(max = 32, message = "主键id长度不能超过32")
    @NotBlank(message = "id should not be empty", groups = {UpdateGroup.class})
    @Null(message = "id should  be empty", groups = {AddGroup.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//主键ID

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    @NotBlank(message = "用户ID不得为空！")
    private String userId;//用户ID

    @ApiModelProperty(value = "岗位ID")
    @TableField("job_id")
    @NotBlank(message = "岗位ID不得为空！")
    private String jobId;//岗位ID


}
