//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cosmoplat.jtx.common.result;

public enum ResultOk implements IResultType {
    SUCCESS(200, "数据请求成功！"),
    GET(200, "数据获取成功！"),
    ADD(200, "数据添加成功！"),
    UPDATE(200, "数据更新成功！"),
    REMOVE(200, "数据删除成功！"),
    STATUS(200, "数据状态修改成功！"),
    IMPORT(200, "数据导入成功！"),
    EXPORT(200, "数据导出成功！"),
    DOWNLOAD(200, "文件下载成功！"),
    UPLOAD(200, "文件上传成功！"),
    AUDIT(200, "审核通过！");

    final int code;
    final String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultOk(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
