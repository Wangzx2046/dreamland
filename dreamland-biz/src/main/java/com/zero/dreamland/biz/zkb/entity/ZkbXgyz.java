package com.zero.dreamland.biz.zkb.entity;

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

/**
*
* @Description ：赚客吧-小鸽驿站
*
* @author : Wang.zx
* @since : 2021-01-21
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zkb_xgyz")
@ApiModel(value="ZkbXgyz对象", description="赚客吧-小鸽驿站")
public class ZkbXgyz extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "站点ID")
    @TableField("siteId")
    @Length(max=20 ,message="站点ID内容过长，请检查！")
    private String siteId;//站点ID

    @ApiModelProperty(value = "标题")
    @TableField("label")
    @Length(max=255 ,message="标题内容过长，请检查！")
    private String label;//标题

    @ApiModelProperty(value = "正文")
    @TableField("mainText")
    @Length(max=2000 ,message="正文内容过长，请检查！")
    private String mainText;//正文

    @ApiModelProperty(value = "链接")
    @TableField("href")
    @Length(max=255 ,message="链接内容过长，请检查！")
    private String href;//链接

    @ApiModelProperty(value = "作者")
    @TableField("author")
    @Length(max=20 ,message="作者内容过长，请检查！")
    private String author;//作者

    @ApiModelProperty(value = "图片列表（逗号分隔）")
    @TableField("imgList")
    @Length(max=255 ,message="图片列表（逗号分隔）内容过长，请检查！")
    private String imgList;//图片列表（逗号分隔）

    @ApiModelProperty(value = "发帖时间")
    @TableField("relTime")
    @Length(max=20 ,message="发帖时间内容过长，请检查！")
    private String relTime;//发帖时间

    @ApiModelProperty(value = "回复数")
    @TableField("replyNum")
    @Length(max=20 ,message="回复数内容过长，请检查！")
    private String replyNum;//回复数

    @ApiModelProperty(value = "阅读数")
    @TableField("readNum")
    @Length(max=20 ,message="阅读数内容过长，请检查！")
    private String readNum;//阅读数


}
