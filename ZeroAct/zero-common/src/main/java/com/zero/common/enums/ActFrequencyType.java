package com.zero.common.enums;

/**
 * 周期类型
 *
 * @Author: cosmoPlat
 * @Date: 2021/7/28 11:08
 */
public enum ActFrequencyType {

    EVERY_DAY("0", "每日"),
    EVERY_MONDAY("1", "每周一"),
    EVERY_TUESDAY("2", "每周二"),
    EVERY_WEDNESDAY("3", "每周三"),
    EVERY_THURSDAY("4", "每周四"),
    EVERY_FRIDAY("5", "每周五"),
    EVERY_SATURDAY("6", "每周六"),
    EVERY_SUNDAY("7", "每周日"),
    WEEKLY("8", "每周"),
    MONTHLY("9", "每月"),
    QUARTERLY("10", "每季度"),
    EVERY_YEAR("11", "每年");


    private String code;
    private String name;

    private ActFrequencyType(String statusCode, String statusName) {
        this.code = statusCode;
        this.name = statusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 转化
     *
     * @param code
     * @return
     */
    public static String getActFrequencyTypeName(String code) {
        for (ActFrequencyType obj : ActFrequencyType.values()) {
            if (obj.getCode().equals(code)) {
                return obj.name;
            }
        }
        return "";
    }
}
