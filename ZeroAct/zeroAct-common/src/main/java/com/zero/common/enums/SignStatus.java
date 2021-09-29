package com.zero.common.enums;

/**
 * 签到状态
 *
 * @author zero
 */
public enum SignStatus {
    YES("1", "已签到"), NO("0", "未签到");

    private final String code;
    private final String info;

    SignStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
