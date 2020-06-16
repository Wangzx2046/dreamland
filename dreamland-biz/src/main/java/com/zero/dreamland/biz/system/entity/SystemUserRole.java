package com.zero.dreamland.biz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description ：系统用户角色关系表
 * @Author wzx
 * @Date 2019-07-31T11:09:20.755
 */
@Data
@ToString(callSuper = true)
@TableName("system_user_role")
public class SystemUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max = 32, message = "id长度不能超过32")
    @NotBlank(message = "id不能为空")
    private String id;//id

    @Length(max = 32, message = "用户id长度不能超过32")
    @NotBlank(message = "用户id不能为空")
    private String userId;//用户id

    @Length(max = 32, message = "角色id长度不能超过32")
    @NotBlank(message = "角色id不能为空")
    private String roleId;//角色id


}