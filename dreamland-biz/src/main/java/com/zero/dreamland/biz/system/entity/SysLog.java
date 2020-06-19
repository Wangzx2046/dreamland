package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
*
* @Description ：系统日志
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="系统日志")
public class SysLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableField("description")
    @Length(max=255 ,message="内容过长，请检查！")
    private String description;//

    @TableField("log_type")
    @Length(max=255 ,message="内容过长，请检查！")
    private String logType;//

    @TableField("method")
    @Length(max=255 ,message="内容过长，请检查！")
    private String method;//

    @TableField("params")
    @Length(max=65535 ,message="内容过长，请检查！")
    private String params;//

    @TableField("request_ip")
    @Length(max=255 ,message="内容过长，请检查！")
    private String requestIp;//

    @TableField("time")
    private Long time;//

    @TableField("username")
    @Length(max=255 ,message="内容过长，请检查！")
    private String username;//

    @TableField("address")
    @Length(max=255 ,message="内容过长，请检查！")
    private String address;//

    @TableField("browser")
    @Length(max=255 ,message="内容过长，请检查！")
    private String browser;//

    @TableField("exception_detail")
    @Length(max=65535 ,message="内容过长，请检查！")
    private String exceptionDetail;//



}
