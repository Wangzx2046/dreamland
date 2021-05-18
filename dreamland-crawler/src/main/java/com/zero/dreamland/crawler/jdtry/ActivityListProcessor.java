package com.zero.dreamland.crawler.jdtry;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class ActivityListProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000);

    private static int total = 0;

    private static int pages = 0;

    /**
     * 主方法启动爬虫
     */
    public static void main(String[] args) {

        Spider.create(new ActivityListProcessor())
                .addUrl("http://try.jd.com/activity/getActivityList/")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .thread(1)
                .run();


        // 这里只爬取第一页的壁纸，如果要爬取其他页数修改for循环参数即可
        for (int i = 1; i <= pages; i++) {
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

    public Map<String, Integer> getBaseInfo() {
        Spider.create(new ActivityListProcessor())
                .addUrl("http://try.jd.com/activity/getActivityList/")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .thread(1)
                .run();
        Map<String, Integer> map = new HashMap<>();
        map.put("total", total);
        map.put("pages", pages);
        return map;
    }

    /**
     * 页面处理逻辑
     * 也就是访问主程序中的URL后得到的页面
     * <p>
     * 爬虫思路：
     * 1. 主程序访问URL后得到页面
     * 2. 将得到的页面解析出需要的参数，并将解析出来并且需要爬取的链接放入爬取目标中 （45-59行）
     * TIPS：WebMagic会自动去识别哪些连接爬取过哪些没有。
     * 3. 访问第二步中放入的链接得到页面，并解析（62行）
     * 4. 将图片的名字和后缀提取出来以便保存（64-77行）
     */
    public void process(Page page) {
        //总数
        total = Integer.valueOf(page.getHtml().xpath("//div[@class='f-result-sum']/span/text()").toString());
        //页数
        pages = Integer.valueOf(page.getHtml().xpath("//div[@class='page clearfix']/div/span[@class='p-skip']/em/b/text()").toString());

    }


    public Site getSite() {
        return site;
    }
}