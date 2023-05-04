//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cosmoplat.jtx.common.result;

public enum ResultFail implements IResultType {
    ERROR(10000, "数据请求失败"),
    PARAM_MISS_ERROR(10001, "缺少必要的请求参数"),
    PARAM_MISS_ID_ERROR(10001, "缺少主键"),
    PARAM_TYPE_ERROR(10002, "请求参数类型错误"),
    PARAM_BIND_ERROR(10003, "请求参数绑定错误"),
    PARAM_VALID_ERROR(10004, "请求参数格式不合规则"),
    PARAM_REPEAT_ERROR(10005, "请求参数重复"),
    METHOD_NOT_SUPPORTED_ERROR(10006, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED_ERROR(10008, "不支持当前媒体类型"),
    MEDIA_TYPE_NOT_ACCEPT_ERROR(10007, "不接受的媒体类型"),
    DATA_NOT_EXIST_ERROR(20001, "数据不存在"),
    DATA_EXISTED_ERROR(20003, "数据已存在"),
    DATA_ADD_ERROR(20004, "数据添加失败,请重试或联系管理员！"),
    DATA_UPDATE_ERROR(20005, "数据更新失败,请重试或联系管理员！"),
    DATA_REMOVE_ERROR(20006, "数据删除失败,请重试或联系管理员！"),
    DATA_IMPORT_ERROR(20007, "数据导入失败,请重试或联系管理员！"),
    DATA_EXPORT_ERROR(20008, "数据导出失败,请重试或联系管理员！"),
    DATA_DOWLOAD_ERROR(20010, "文件下载失败,请重试或联系管理员！"),
    DATA_UPLOAD_ERROR(20010, "文件下载失败,请重试或联系管理员！"),
    DATA_OFTEN_ERROR(20009, "请求数据提交过快,请勿频繁操作！"),
    INVALID_TOKEN(100201, "安全令牌失效"),
    INVALID_CAPTCHA(100202, "验证码输入有误,请重新输入"),
    INVALID_REQUEST(100203, "无效请求"),
    INVALID_CLIENT(100204, "未授权客户端"),
    INVALID_GRANT(100205, "不支持的授权类型"),
    INVALID_SCOPE(100206, "无效scope"),
    INVALID_EXCEPTION(10216, "授权认证异常！"),
    INSUFFICIENT_SCOPE(100207, "授权不足, 令牌失效或者缺失！"),
    REDIRECT_URI_MISMATCH(100208, "redirect url不匹配"),
    ACCESS_DENIED(100209, "拒绝访问"),
    METHOD_NOT_ALLOWED(100210, "不支持该方法"),
    UNAUTHORIZED(100213, "没有访问权限，需要进行身份认证！"),
    USERNAME_OR_PASSWORD_ERROR(100219, "用户名或者密码输入错误！"),
    USERNAME_NOT_FOUND(100218, "账号不存在！"),
    USERNAME_DISABLED(100217, "账号已被停用！"),
    USER_INIT_ERROR(100302, "用户初始化异常！"),
    SERVER_ERROR(100211, "权限服务错误"),
    INVALID_UNKNOWN(617, "授权认证异常！"),
    SERVICE_ERROR(100301, "业务服务异常！");

    final int code;
    final String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultFail(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
