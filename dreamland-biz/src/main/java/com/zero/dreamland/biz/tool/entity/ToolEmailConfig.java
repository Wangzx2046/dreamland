package com.zero.dreamland.biz.tool.entity;

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

/**
*
* @Description ：邮箱配置
*
* @author : Wang.zx
* @since : 2020-07-03
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tool_email_config")
@ApiModel(value="ToolEmailConfig对象", description="邮箱配置")
public class ToolEmailConfig extends BaseModel {

    private static final long serialVersionUID = 1L;


    @TableField("config_id")
    @NotBlank(message="ID不得为空！")
    private String configId;//ID

    @ApiModelProperty(value = "收件人")
    @TableField("from_user")
    @Length(max=255 ,message="收件人内容过长，请检查！")
    private String fromUser;//收件人

    @ApiModelProperty(value = "邮件服务器SMTP地址")
    @TableField("host")
    @Length(max=255 ,message="邮件服务器SMTP地址内容过长，请检查！")
    private String host;//邮件服务器SMTP地址

    @ApiModelProperty(value = "密码")
    @TableField("pass")
    @Length(max=255 ,message="密码内容过长，请检查！")
    private String pass;//密码

    @ApiModelProperty(value = "端口")
    @TableField("port")
    @Length(max=255 ,message="端口内容过长，请检查！")
    private String port;//端口

    @ApiModelProperty(value = "发件者用户名")
    @TableField("user")
    @Length(max=255 ,message="发件者用户名内容过长，请检查！")
    private String user;//发件者用户名


}
