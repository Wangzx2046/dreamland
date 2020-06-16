package com.zero.dreamland.common.returnMsg;

public class EnumUtil {

    public static <T extends ResultEnum> String getByCode(Integer code, Class<T> t) {
        for (T item : t.getEnumConstants()) {
            if (item.getCode().equals(code)) {
                return item.getMsg();
            }
        }
        return "";
    }
}
