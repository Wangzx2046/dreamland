package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 *
 * @Description ：
 *
 * @author : Wang.zx
 * @since : 2019-09-16
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemLoginLog对象", description="")
public class SystemLoginLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志名称")
    @Length(max=10 ,message="日志名称内容过长，请检查！")
    private String logName;//日志名称

    @ApiModelProperty(value = "账户名称")
    @Length(max=20 ,message="账户名称内容过长，请检查！")
    private String loginAccount;//账户名称

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginDate;//登录时间

    @ApiModelProperty(value = "userAgent")
    @TableField("login_userAgent")
    @Length(max=50 ,message="userAgent内容过长，请检查！")
    private String loginUseragent;//userAgent

    @ApiModelProperty(value = "IP地址")
    @Length(max=16 ,message="IP地址内容过长，请检查！")
    private String loginIp;//IP地址

    @ApiModelProperty(value = "系统")
    @Length(max=20 ,message="系统内容过长，请检查！")
    private String loginOs;//系统

    @ApiModelProperty(value = "浏览器")
    @Length(max=20 ,message="浏览器内容过长，请检查！")
    private String loginBrowser;//浏览器

    @ApiModelProperty(value = "返回信息")
    @Length(max=255 ,message="返回信息内容过长，请检查！")
    private String loginRes;//返回信息


}
