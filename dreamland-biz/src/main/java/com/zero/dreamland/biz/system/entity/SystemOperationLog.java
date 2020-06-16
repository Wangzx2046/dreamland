package com.zero.dreamland.biz.system.entity;


import com.zero.dreamland.biz.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *   @Description    ：登录日志
 *   @Creation Date    ：2019/9/16 14:38       
 *   @Author           ：wzx
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemOperationLog对象", description = "登录日志")
public class SystemOperationLog extends BaseModel {
    private static final long serialVersionUID = 1L;

    //日志类型
    private String logType;
    //日志名称
    private String logName;
    //用户ID
    private Integer userId;
    //类名称
    private String className;
    //方法名称
    private String method;
    //是否成功
    private String succeed;
    //备注
    private String message;


}
