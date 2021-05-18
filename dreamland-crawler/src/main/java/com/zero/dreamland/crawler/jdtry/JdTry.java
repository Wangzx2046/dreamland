package com.zero.dreamland.crawler.jdtry;

import us.codecraft.webmagic.Spider;

import java.util.Map;

public class JdTry {
    public static void main(String[] args) {
        //1.获取总商品数
        Map map = (new BaseInfoProcessor()).getBaseInfo();

        //2.获取商品列表
        for (int i = 1; i <= (int)map.get("pages"); i++) {
            // 启动爬虫
            Spider.create(new ActivityListProcessor())
                    // 添加初始化的URL
                    .addUrl("http://try.jd.com/activity/getActivityList/?page=" + i)
                    // 使用单线程
                    .thread(10)
                    // 运行
                    .run();
        }
    }
}
