package com.zero.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JdTryUtil {
    private static final String URL = "https://api.m.jd.com/client.action";
    private static final String ORIGIN = "https://prodev.m.jd.com";
    private static final String HOST = "api.m.jd.com";

    /**
     * 申请试用
     */
    public static void tryApply() throws JSONException {
        //1.获取试用商品列表
        //链式构建请求
        JSONObject  jb= new JSONObject();
        jb.put("activityId", 2062052);
        jb.put("previewTime", "");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "newtry");
        paramMap.put("functionId", "try_apply");
        paramMap.put("body", jb.toString());


        String cookie = "pt_key=AAJiN-nWADBuLzvAtOkrUgEDShjQHXrs_1xTzyG2xhh2rkqHjykOiIFzRsbzkeUEh8eGL7W5fdE; pt_pin=as15621009921;";
        String result2 = HttpRequest.post(URL)
                .header(Header.USER_AGENT, "jdapp;android;10.5.0;;;appBuild/95837;ef/1;ep/{\"hdid\":\"JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw=\",\"ts\":1649917022756,\"ridx\":-1,\"cipher\":{\"sv\":\"CJO=\",\"ad\":\"DNLsCzHsCzK2DzU4DwCmYG==\",\"od\":\"YJY5EJq0CzcnCwS5Czq3Zq==\",\"ov\":\"CzK=\",\"ud\":\"DNLsCzHsCzK2DzU4DwCmYG==\"},\"ciphertype\":5,\"version\":\"1.2.0\",\"appname\":\"com.jingdong.app.mall\"};jdSupportDarkMode/0;Mozilla/5.0 (Linux; Android 11; MI 9 Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/89.0.4389.72 MQQBrowser/6.2 TBS/045947 Mobile Safari/537.36")//头信息，多个头信息多次调用此方法即可
                .header(Header.ORIGIN, ORIGIN)
                .header(Header.HOST, HOST)
                .header(Header.CONNECTION, "keep-alive")
                .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(Header.CONTENT_LENGTH, String.valueOf(paramMap.toString().length()))
                .header(Header.COOKIE, cookie)
                //      .header("X-Requested-With", "com.jingdong.app.mall")
                .form(paramMap)

                .timeout(20000)//超时，毫秒;
                .execute().body();
        Console.log(result2);

    }

    public static void main(String[] args) throws JSONException {
        tryApply();
    }
}
