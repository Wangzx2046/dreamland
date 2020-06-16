package com.zero.dreamland.common.returnMsg;

public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    INSUFFICIENT_AUTHORITY(401,"用户权限不足，请使用拥有访问权限的账户进行操作!"),
    USERNAME_OR_PASSWORD_NOT_EXIST(10000,"账号或密码错误！"),
    USER_NOT_EXIST(10001,"用户不存在"),
    USER_IS_EXISTS(10002,"用户已存在"),
    MISSING_TOKEN(10003,"缺少TOKEN"),
    MISSING_TIMEOUT(10004,"过期的TOKEN"),
    ACCOUNT_LOCKED(10007,"账户锁定，请联系管理员处理"),
    PARAMETER_ERROR(20000,"参数错误"),
    MISSING_NECESSARY_PARAMETERS(20001,"缺少必要参数"),

    ;
    private Integer code;
    private String msg;
 
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
 
    public Integer getCode() {
        return code;
    }
 
    public String getMsg() {
        return msg;
    }
}
