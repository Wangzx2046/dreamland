package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

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
    @ApiModelProperty(value = "主键ID")
    @Length(max = 32, message = "主键id长度不能超过32")
    @NotBlank(message = "id should not be empty", groups = {UpdateGroup.class})
    @Null(message = "id should  be empty", groups = {AddGroup.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//主键ID

    @TableField("role_id")
    @NotBlank(message="不得为空！")
    @Length(max=32 ,message="内容过长，请检查！")
    private String roleId;//

    @TableField("dept_id")
    @NotBlank(message="不得为空！")
    @Length(max=32 ,message="内容过长，请检查！")
    private String deptId;//


}
