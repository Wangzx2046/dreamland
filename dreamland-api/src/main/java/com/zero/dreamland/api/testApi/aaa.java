package com.zero.dreamland.api.testApi;

import cn.hutool.core.util.StrUtil;

/**
 * @ClassName: aaa
 * @Description: aaa*
 * @Author: wzx
 * @Date: 2020-07-09 17:03
 * @Version 1.0.0
 **/
public class aaa {
    public static void main(String[] args) {
        System.out.println(String.join("", "1", "2"));

        String strs[] = {"asda", "大水"};
        System.out.println(StrUtil.containsAny("中航大水啦啊啊啊 啊", strs));
    }
}
