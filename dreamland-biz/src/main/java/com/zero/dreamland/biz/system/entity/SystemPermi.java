package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.dreamland.biz.common.base.BaseModel;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description ：系统权限表
 * @Author wzx
 * @Date  2019-07-31T15:07:59.225
 */
@Data
@ToString(callSuper = true)
@TableName("system_permi")
public class SystemPermi extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max=16 ,message="权限名称长度不能超过16")
    @NotBlank(message = "权限名称不能为空")
    private String permiName;//权限名称

    @Length(max=20 ,message="权限编码长度不能超过20")
    private String permiCode;//权限编码

    @Length(max=16 ,message="权限标题长度不能超过16")
    private String permiTitle;//权限标题

    @Length(max=32 ,message="权限的父级长度不能超过32")
    private String permiParentId;//权限的父级

    @Length(max=32 ,message="菜单外键长度不能超过32")
    private String fkMenuId;//菜单外键

    @Length(max=255 ,message="权限标识长度不能超过255")
    @NotBlank(message="权限标识不能为空")
    private String permiSign;//权限标识

    @Min(value = 0,message = "排序从0开始，不得小于0")
    private BigDecimal permiSort;//权限排序

    @Length(max=255 ,message="权限描述长度不能超过255")
    private String permiNote;//权限描述



}