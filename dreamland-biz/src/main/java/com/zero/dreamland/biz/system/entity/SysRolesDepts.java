package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
*
* @Description ：角色部门关联
*
* @author : Wang.zx
* @since : 2020-06-11
*/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_roles_depts")
@ApiModel(value="SysRolesDepts对象", description="角色部门关联")
public class SysRolesDepts  {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    @NotBlank(message="不得为空！")
    @Length(max=32 ,message="内容过长，请检查！")
    private String roleId;//

    @TableField("dept_id")
    @NotBlank(message="不得为空！")
    @Length(max=32 ,message="内容过长，请检查！")
    private String deptId;//


}
