//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cosmoplat.jtx.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ApiModel(
        description = "REST请求的返回模型，所有REST正常都返回该类的对象"
)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("处理结果Boolean")
    private boolean success;
    @ApiModelProperty("处理结果数据信息")
    private T data;
    @ApiModelProperty("返回非主数据")
    private Map<String, Object> variables = new HashMap();
    @ApiModelProperty("处理结果描述信息")
    private String message;
    @ApiModelProperty("处理结果编码")
    private Integer code;
    @ApiModelProperty("处理结果请求得URL")
    private String url;
    @ApiModelProperty("处理结果错误信息堆栈")
    private String errorStackTrack;
    @ApiModelProperty("处理结果生成得时间戳")
    private Instant time;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(boolean success, T data, String message, Integer code) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(boolean success, String message, Integer code) {
        of(success, (Object)null, message, code);
    }

    public Result(IResultType resultTypeEnum) {
        this.code = resultTypeEnum.getCode();
        this.message = resultTypeEnum.getMessage();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(boolean success, IResultType resultType) {
        of(success, (Object)null, resultType.getMessage(), resultType.getCode());
    }

    public Result(boolean success, IResultType resultType, T data) {
        of(success, data, resultType.getMessage(), resultType.getCode());
    }

    public static <T> Result<T> of(boolean success, T data, String message, Integer code) {
        return new Result(success, data, message, code);
    }

    public static <T> Result<T> of(boolean success, T data, IResultType resultType) {
        return new Result(success, data, resultType.getMessage(), resultType.getCode());
    }

    public static <T> Result<T> ok() {
        return ok((IResultType) ResultOk.SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return of(true, data, ResultOk.SUCCESS);
    }

    public static <T> Result<T> ok(T data, String message) {
        return of(true, data, message, 200);
    }

    public static <T> Result<T> ok(IResultType resultType) {
        return (Result<T>) of(true, (Object)null, resultType);
    }

    public static <T> Result<T> ok(T data, IResultType resultType) {
        return of(true, data, resultType);
    }

    public static <T> Result<T> ok(String message) {
        return (Result<T>) of(true, (Object)null, message, 200);
    }

    public static <T> Result<T> ok(String message, Integer code) {
        return (Result<T>) of(true, (Object)null, message, code);
    }

    public static <T> Result<T> fail() {
        return fail(ResultFail.ERROR);
    }

    public static <T> Result<T> fail(T data) {
        return of(false, data, ResultFail.ERROR);
    }

    public static <T> Result<T> fail(IResultType resultType) {
        return (Result<T>) of(false, (Object)null, resultType);
    }

    public static <T> Result<T> fail(T data, IResultType resultTypeEnum) {
        return of(false, data, resultTypeEnum);
    }

    public static <T> Result<T> fail(String message) {
        return (Result<T>) of(false, (Object)null, message, 10000);
    }

    public static <T> Result<T> fail(String message, Integer code) {
        return (Result<T>) of(false, (Object)null, message, code);
    }

    public void addVariable(String key, Object value) {
        this.variables.put(key, value);
    }

    public void clearVariable() {
        this.variables.clear();
    }

    public Result(boolean success, T data, Map<String, Object> variables, String message, Integer code, String url, String errorStackTrack, Instant time) {
        this.success = success;
        this.data = data;
        this.variables = variables;
        this.message = message;
        this.code = code;
        this.url = url;
        this.errorStackTrack = errorStackTrack;
        this.time = time;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setErrorStackTrack(String errorStackTrack) {
        this.errorStackTrack = errorStackTrack;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public Map<String, Object> getVariables() {
        return this.variables;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getUrl() {
        return this.url;
    }

    public String getErrorStackTrack() {
        return this.errorStackTrack;
    }

    public Instant getTime() {
        return this.time;
    }

    public String toString() {
        return "Result(success=" + this.isSuccess() + ", data=" + this.getData() + ", variables=" + this.getVariables() + ", message=" + this.getMessage() + ", code=" + this.getCode() + ", url=" + this.getUrl() + ", errorStackTrack=" + this.getErrorStackTrack() + ", time=" + this.getTime() + ")";
    }
}
