package com.zero.dreamland.quartz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
* @Description ：定时任务
*
* @author : Wang.zx
* @since : 2020-07-02
*/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_quartz_job")
@ApiModel(value="SysQuartzJob对象", description="定时任务")
public class SysQuartzJob {

    public static final String JOB_KEY = "JOB_KEY";
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @Length(max = 32, message = "主键id长度不能超过32")
    @NotBlank(message = "id should not be empty", groups = {UpdateGroup.class})
    @Null(message = "id should  be empty", groups = {AddGroup.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//主键ID


    @ApiModelProperty(value = "状态标志：0正常 1禁用 2删除")
    @TableLogic
    private Integer delFlag;//状态标志：0正常 1禁用 2删除

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;//插入时间

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;//插入者

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;//更新者

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;//更新时间

    @ApiModelProperty(value = "Spring Bean名称")
    @TableField("bean_name")
    @Length(max=255 ,message="Spring Bean名称内容过长，请检查！")
    private String beanName;//Spring Bean名称

    @ApiModelProperty(value = "cron 表达式")
    @TableField("cron_expression")
    @Length(max=255 ,message="cron 表达式内容过长，请检查！")
    private String cronExpression;//cron 表达式

    @ApiModelProperty(value = "状态：1暂停、0启用")
    @TableField("is_pause")
    private Boolean isPause;//状态：1暂停、0启用

    @ApiModelProperty(value = "任务名称")
    @TableField("job_name")
    @Length(max=50 ,message="任务名称内容过长，请检查！")
    private String jobName;//任务名称

    @ApiModelProperty(value = "方法名称")
    @TableField("method_name")
    @Length(max=100 ,message="方法名称内容过长，请检查！")
    private String methodName;//方法名称

    @ApiModelProperty(value = "参数")
    @TableField("params")
    @Length(max=255 ,message="参数内容过长，请检查！")
    private String params;//参数

    @ApiModelProperty(value = "备注")
    @TableField("description")
    @Length(max=255 ,message="备注内容过长，请检查！")
    private String description;//备注

    @ApiModelProperty(value = "负责人")
    @TableField("person_in_charge")
    @Length(max=100 ,message="负责人内容过长，请检查！")
    private String personInCharge;//负责人

    @ApiModelProperty(value = "报警邮箱")
    @TableField("email")
    @Length(max=100 ,message="报警邮箱内容过长，请检查！")
    private String email;//报警邮箱

    @ApiModelProperty(value = "子任务ID")
    @TableField("sub_task")
    @Length(max=100 ,message="子任务ID内容过长，请检查！")
    private String subTask;//子任务ID

    @ApiModelProperty(value = "任务失败后是否暂停")
    @TableField("pause_after_failure")
    private Boolean pauseAfterFailure;//任务失败后是否暂停


    @TableField(exist = false)
    @ApiModelProperty(value = "用于子任务唯一标识", hidden = true)
    private String uuid;
}
