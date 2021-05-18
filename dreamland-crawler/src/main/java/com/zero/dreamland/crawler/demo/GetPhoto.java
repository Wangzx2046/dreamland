package com.zero.dreamland.crawler.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GetPhoto implements PageProcessor {

    // 设置参数
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000);

    /**
     * 主方法启动爬虫
     */
    public static void main(String[] args) {

        // 这里只爬取第一页的壁纸，如果要爬取其他页数修改for循环参数即可
        for (int i = 1; i <= 50; i++) {
            // 启动爬虫
            Spider.create(new GetPhoto())
                    // 添加初始化的URL
                    .addUrl("https://wallhaven.cc/toplist?page=" + i)
                    // 使用单线程
                    .thread(10)
                    // 运行
                    .run();
        }
    }

    /**
     * 页面处理逻辑
     * 也就是访问主程序中的URL后得到的页面
     *
     * 爬虫思路：
     *      1. 主程序访问URL后得到页面
     *      2. 将得到的页面解析出需要的参数，并将解析出来并且需要爬取的链接放入爬取目标中 （45-59行）
     *          TIPS：WebMagic会自动去识别哪些连接爬取过哪些没有。
     *      3. 访问第二步中放入的链接得到页面，并解析（62行）
     *      4. 将图片的名字和后缀提取出来以便保存（64-77行）
     */
    public void process(Page page) {
        // 一页是24张图片
        for (int i = 1; i <= 1; i++) {

            // 使用Xpath解析，获取到单个图片的网页
            // WebMagic支持使用Xpath、CSS选择器、正则表达式、JsonPath来解析页面
            String str = page.getHtml().xpath("//div[@id=thumbs]/section/ul/li[" + i + "]/figure/a[@class=preview]/@href").toString();

            // 获取到的连接为null则退出循环，不添加进爬取目标
            if (str == null)
                break;

            // 将爬取到的链接添加到待爬取页面中
            page.addTargetRequest(str);
        }

        // 将页面中图片的地址提取出来，以便于使用工具类保存
        String pageURL = page.getHtml().xpath("//img[@id=wallpaper]/@src").toString();

        if (pageURL != null) {
            try {
                // 获取图片的名字和后缀提取出来用于保存
                String name = pageURL.substring(pageURL.length() - 9, pageURL.length() - 4);
                String suffix = pageURL.substring(pageURL.length() - 4);

                // 将图片的地址、名字、保存路径传入文件工具类进行下载
                DownloadImage.downLoadFromUrl(pageURL, name + suffix, "D:\\data/file/");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public Site getSite() {
        return site;
    }
}