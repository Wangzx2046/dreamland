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
* @Description ：赚客吧-文章
*
* @author : Wang.zx
* @since : 2021-01-20
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zkb_article")
@ApiModel(value="ZkbArticle对象", description="赚客吧-文章")
public class ZkbArticle extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @TableField("tid")
    private Long tid;//文章ID

    @TableField("readperm")
    @Length(max=20 ,message="内容过长，请检查！")
    private String readperm;//

    @TableField("author")
    @Length(max=20 ,message="内容过长，请检查！")
    private String author;//

    @TableField("authorid")
    private Integer authorid;//

    @TableField("subject")
    @Length(max=255 ,message="内容过长，请检查！")
    private String subject;//

    @TableField("dateline")
    @Length(max=20 ,message="内容过长，请检查！")
    private String dateline;//

    @TableField("lastpost")
    @Length(max=20 ,message="内容过长，请检查！")
    private String lastpost;//

    @TableField("lastposter")
    @Length(max=20 ,message="内容过长，请检查！")
    private String lastposter;//

    @TableField("views")
    @Length(max=10 ,message="内容过长，请检查！")
    private String views;//

    @TableField("replies")
    @Length(max=10 ,message="内容过长，请检查！")
    private String replies;//

    @TableField("digest")
    @Length(max=10 ,message="内容过长，请检查！")
    private String digest;//

    @TableField("attachment")
    @Length(max=10 ,message="内容过长，请检查！")
    private String attachment;//

    @TableField("dbdateline")
    @Length(max=20 ,message="内容过长，请检查！")
    private String dbdateline;//

    @TableField("dblastpost")
    @Length(max=20 ,message="内容过长，请检查！")
    private String dblastpost;//

    @TableField("avatar")
    @Length(max=255 ,message="内容过长，请检查！")
    private String avatar;//

    @TableField("details")
    @Length(max=500 ,message="内容过长，请检查！")
    private String details;//


}
