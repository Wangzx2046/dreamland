package com.zero.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zero.common.annotation.Excel;
import com.zero.common.core.domain.BaseEntity;

/**
 * 公司信息对象 t_company
 * 
 * @author zero
 * @date 2021-03-03
 */
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String companyContacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String companyTel;

    /** 联系邮箱 */
    @Excel(name = "联系邮箱")
    private String companyEmail;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String companyAddr;

    /** 精度 */
    @Excel(name = "精度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 公司简介 */
    @Excel(name = "公司简介")
    private String companyIntroduction;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setCompanyContacts(String companyContacts) 
    {
        this.companyContacts = companyContacts;
    }

    public String getCompanyContacts() 
    {
        return companyContacts;
    }
    public void setCompanyTel(String companyTel) 
    {
        this.companyTel = companyTel;
    }

    public String getCompanyTel() 
    {
        return companyTel;
    }
    public void setCompanyEmail(String companyEmail) 
    {
        this.companyEmail = companyEmail;
    }

    public String getCompanyEmail() 
    {
        return companyEmail;
    }
    public void setCompanyAddr(String companyAddr) 
    {
        this.companyAddr = companyAddr;
    }

    public String getCompanyAddr() 
    {
        return companyAddr;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setCompanyIntroduction(String companyIntroduction) 
    {
        this.companyIntroduction = companyIntroduction;
    }

    public String getCompanyIntroduction() 
    {
        return companyIntroduction;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("companyContacts", getCompanyContacts())
            .append("companyTel", getCompanyTel())
            .append("companyEmail", getCompanyEmail())
            .append("companyAddr", getCompanyAddr())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("companyIntroduction", getCompanyIntroduction())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
