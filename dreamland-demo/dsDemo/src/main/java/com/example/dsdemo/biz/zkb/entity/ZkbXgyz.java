package com.example.dsdemo.biz.zkb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : Wang.zx
 * @Description ：赚客吧-小鸽驿站
 * @since : 2021-01-21
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("zkb_xgyz")
public class ZkbXgyz {

    private static final long serialVersionUID = 1L;

    @TableField("siteId")
    private String siteId;//站点ID

    @TableField("label")
    private String label;//标题

    @TableField("mainText")
    private String mainText;//正文

    @TableField("href")
    private String href;//链接

    @TableField("author")
    private String author;//作者

    @TableField("imgList")
    private String imgList;//图片列表（逗号分隔）

    @TableField("relTime")
    private String relTime;//发帖时间

    @TableField("replyNum")
    private String replyNum;//回复数

    @TableField("readNum")
    private String readNum;//阅读数


    @TableField("keyWord")
    private String keyWord;//关键字

}
