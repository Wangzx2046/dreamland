package com.zero.dreamland.biz.system.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description ：系统角色和权限的关系表
 * @Author wzx
 * @Date  2019-07-31T15:08:00.197
 */
@Data
@ToString(callSuper = true)
public class SystemRolePermi  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max=32 ,message="id长度不能超过32")
    @NotBlank(message="id不能为空")
    private String id;//id

    @Length(max=32 ,message="角色id长度不能超过32")
    @NotBlank(message="角色id不能为空")
    private String roleId;//角色id

    @Length(max=32 ,message="权限表id长度不能超过32")
    @NotBlank(message="权限表id不能为空")
    private String permiId;//权限表id



}