package com.zero.act.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.common.annotation.Excel;
import com.zero.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 活动信息对象 t_act_info
 *
 * @author wzx
 * @date 2021-08-27
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_act_info")
@ApiModel(value="ActInfo对象", description="活动信息")
public class ActInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 活动ID */

    @ApiModelProperty(value = "${comment}")
    @TableId("act_id")
    private Long actId;

    /** 活动名称 */

    @Excel(name = "活动名称")
    @ApiModelProperty(value = "活动名称")
    @TableField("act_name")
    private String actName;

    /** 活动简介 */

    @Excel(name = "活动简介")
    @ApiModelProperty(value = "活动简介")
    @TableField("act_introduction")
    private String actIntroduction;

    /** 活动频率 */

    @Excel(name = "活动频率")
    @ApiModelProperty(value = "活动频率")
    @TableField("act_frequency")
    private String actFrequency;

    /** 活动开始时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GTM+8")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "活动开始时间")
    @TableField("act_start_time")
    private LocalDateTime actStartTime;

    /** 活动结束时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GTM+8")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "活动结束时间")
    @TableField("act_end_time")
    private LocalDateTime actEndTime;

    /** 提醒时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GTM+8")
    @Excel(name = "提醒时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提醒时间")
    @TableField("remind_time")
    private LocalDateTime remindTime;

    /** 周期内可参与次数：0代表不限次数 */

    @Excel(name = "周期内可参与次数：0代表不限次数")
    @ApiModelProperty(value = "周期内可参与次数：0代表不限次数")
    @TableField("cycle_times")
    private Long cycleTimes;

    /** 活动链接 */

    @Excel(name = "活动链接")
    @ApiModelProperty(value = "活动链接")
    @TableField("act_link")
    private String actLink;

    /** 活动渠道 */

    @Excel(name = "活动渠道")
    @ApiModelProperty(value = "活动渠道")
    @TableField("act_channel")
    private String actChannel;

    /** 删除标志（0代表存在 2代表删除） */

    @ApiModelProperty(value = "活动渠道")
    @TableField("del_flag")
    private String delFlag;




}