package com.zero.generator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zero.common.annotation.Excel;
import com.zero.common.core.domain.BaseEntity;

/**
 * 代码生成模版对象 gen_template
 * 
 * @author auto
 * @date 2021-07-23
 */
public class GenTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 模版组 */
    @Excel(name = "模版组")
    private String templateGroup;

    /** 模版名（文件名） */
    @Excel(name = "模版名", readConverterExp = "文=件名")
    private String templateName;

    /** 存放路径 */
    @Excel(name = "存放路径")
    private String path;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTemplateGroup(String templateGroup) 
    {
        this.templateGroup = templateGroup;
    }

    public String getTemplateGroup() 
    {
        return templateGroup;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
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
            .append("id", getId())
            .append("templateGroup", getTemplateGroup())
            .append("templateName", getTemplateName())
            .append("path", getPath())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
