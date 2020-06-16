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

/**
 *
 * @Description ：操作日志表
 *
 * @author : Wang.zx
 * @since : 2020-03-24
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemLog对象", description="操作日志表")
public class SystemLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志类型")
    @Length(max=1 ,message="日志类型内容过长，请检查！")
    private String logType;//日志类型

    @ApiModelProperty(value = "日志标题")
    @NotBlank(message="日志标题不得为空！")
    @Length(max=50 ,message="日志标题内容过长，请检查！")
    private String logTitle;//日志标题

    @ApiModelProperty(value = "操作ip地址")
    @Length(max=50 ,message="操作ip地址内容过长，请检查！")
    private String logRemoteAddr;//操作ip地址

    @ApiModelProperty(value = "用户代理")
    @Length(max=200 ,message="用户代理内容过长，请检查！")
    private String logUserAgent;//用户代理

    @ApiModelProperty(value = "请求uri")
    @Length(max=255 ,message="请求uri内容过长，请检查！")
    private String logRequestUri;//请求uri

    @ApiModelProperty(value = "操作方式")
    @Length(max=50 ,message="操作方式内容过长，请检查！")
    private String logOperateMethod;//操作方式

    @ApiModelProperty(value = "操作提交的数据")
    @Length(max=65535 ,message="操作提交的数据内容过长，请检查！")
    private String logOperateParams;//操作提交的数据

    @ApiModelProperty(value = "异常信息")
    @Length(max=65535 ,message="异常信息内容过长，请检查！")
    private String logOperateException;//异常信息

    @ApiModelProperty(value = "数据域")
    @Length(max=255 ,message="数据域内容过长，请检查！")
    private String dataScope;//数据域

    @ApiModelProperty(value = "备注")
    @Length(max=255 ,message="备注内容过长，请检查！")
    private String remarks;//备注


}
