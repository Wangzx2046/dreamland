package com.zero.jdTry.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.common.annotation.Excel;
import com.zero.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * 试用商品对象 jd_goods
 *
 * @author wzx
 * @date 2022-04-26
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("jd_goods")
@ApiModel(value = "JdGoods对象", description = "试用商品")
public class JdGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    @ApiModelProperty(value = "${comment}")
    @TableField("id")
    private Long id;

    /**
     * 类型3种草官 5付费
     */

    @Excel(name = "类型3种草官 5付费")
    @ApiModelProperty(value = "类型3种草官 5付费")
    @TableField("type")
    private Long type;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("sort")
    private Long sort;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("isConfig")
    private Long isConfig;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("filterType")
    private String filterType;

    /**
     * 商品编码
     */

    @Excel(name = "商品编码")
    @ApiModelProperty(value = "商品编码")
    @TableField("trialActivityId")
    private Long trialActivityId;

    /**
     * 编号
     */

    @Excel(name = "编号")
    @ApiModelProperty(value = "编号")
    @TableField("sku")
    private String sku;

    /**
     * 图片
     */

    @Excel(name = "图片")
    @ApiModelProperty(value = "图片")
    @TableField("skuImg")
    private String skuImg;

    /**
     * 标题
     */

    @Excel(name = "标题")
    @ApiModelProperty(value = "标题")
    @TableField("skuTitle")
    private String skuTitle;

    /**
     * 试用价格
     */

    @Excel(name = "试用价格")
    @ApiModelProperty(value = "试用价格")
    @TableField("trialPrice")
    private String trialPrice;

    /**
     * 原价
     */

    @Excel(name = "原价")
    @ApiModelProperty(value = "原价")
    @TableField("jdPrice")
    private BigDecimal jdPrice =new BigDecimal(0);

    /**
     * 标签
     */

    @Excel(name = "标签")
    @ApiModelProperty(value = "标签")
    @TableField("tagList")
    private List<TagList> tagList;

    /**
     * 提供数目
     */

    @Excel(name = "提供数目")
    @ApiModelProperty(value = "提供数目")
    @TableField("supplyNum")
    private Long supplyNum;

    /**
     * 申请人数
     */

    @Excel(name = "申请人数")
    @ApiModelProperty(value = "申请人数")
    @TableField("applyNum")
    private String applyNum;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("brokerInfo")
    private String brokerInfo;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("applyState")
    private Integer applyState;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("activityType")
    private Long activityType;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("trialProcess")
    private Long trialProcess;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("activityStatus")
    private Integer activityStatus;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("showPoint")
    private String showPoint;

    /**
     *
     */

    @Excel(name = "")
    @ApiModelProperty(value = "")
    @TableField("isNewActivity")
    private Long isNewActivity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JdGoods goods = (JdGoods) o;
        return Objects.equals(trialActivityId, goods.trialActivityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trialActivityId);
    }
}