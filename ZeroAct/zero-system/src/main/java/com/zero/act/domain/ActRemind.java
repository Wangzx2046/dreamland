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
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 活动打卡记录对象 t_act_remind
 *
 * @author wzx
 * @date 2021-08-27
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_act_remind")
@ApiModel(value="ActRemind对象", description="活动打卡记录")
public class ActRemind extends BaseEntity{

private static final long serialVersionUID = 1L;

    /** 活动ID */

    @Excel(name = "活动ID")
    @ApiModelProperty(value = "活动ID")
    @TableField("act_id")
    private Long actId;

    /** 主键 */

    @ApiModelProperty(value = "活动ID")
    @TableId("remind_id")
    private Long remindId;

    /** 活动名称 */

    @Excel(name = "活动名称")
    @ApiModelProperty(value = "活动名称")
    @TableField("act_name")
    private String actName;

    /** 提醒时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "提醒时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提醒时间")
    @TableField("remind_time")
    private LocalDateTime remindTime;

    /** 活动链接 */

    @Excel(name = "活动链接")
    @ApiModelProperty(value = "活动链接")
    @TableField("act_link")
    private String actLink;

    /** 打卡状态 */

    @Excel(name = "打卡状态")
    @ApiModelProperty(value = "打卡状态")
    @TableField("sign_flag")
    private String signFlag;

    /** 打卡时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "打卡时间")
    @TableField("sign_time")
    private LocalDateTime signTime;

    /** 用户ID */

    @Excel(name = "用户ID")
    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    /** 用户昵称 */

    @Excel(name = "用户昵称")
    @ApiModelProperty(value = "用户昵称")
    @TableField("user_name")
    private String userName;

    /** 是否开启提醒（预留字段） */

    @Excel(name = "是否开启提醒", readConverterExp = "预=留字段")
    @ApiModelProperty(value = "是否开启提醒")
    @TableField("is_message")
    private String isMessage;

    /** 删除标志（0代表存在 2代表删除） */

    @ApiModelProperty(value = "是否开启提醒")
    @TableField("del_flag")
    private String delFlag;




}
