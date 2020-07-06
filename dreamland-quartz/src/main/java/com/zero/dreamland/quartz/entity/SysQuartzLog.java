package com.zero.dreamland.quartz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDateTime;

/**
*
* @Description ：定时任务日志
*
* @author : Wang.zx
* @since : 2020-07-02
*/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_quartz_log")
@ApiModel(value="SysQuartzLog对象", description="定时任务日志")
public class SysQuartzLog  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @Length(max = 32, message = "主键id长度不能超过32")
    @NotBlank(message = "id should not be empty", groups = {UpdateGroup.class})
    @Null(message = "id should  be empty", groups = {AddGroup.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//主键ID

    @TableField("bean_name")
    @Length(max=255 ,message="内容过长，请检查！")
    private String beanName;//

   /* @TableField("create_time")
    private LocalDateTime createTime;//*/

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;//插入时间

    @TableField("cron_expression")
    @Length(max=255 ,message="内容过长，请检查！")
    private String cronExpression;//

    @TableField("exception_detail")
    @Length(max=65535 ,message="内容过长，请检查！")
    private String exceptionDetail;//

    @TableField("is_success")
    private Boolean isSuccess;//

    @TableField("job_name")
    @Length(max=255 ,message="内容过长，请检查！")
    private String jobName;//

    @TableField("method_name")
    @Length(max=255 ,message="内容过长，请检查！")
    private String methodName;//

    @TableField("params")
    @Length(max=255 ,message="内容过长，请检查！")
    private String params;//

    @TableField("time")
    private Long time;//


}
