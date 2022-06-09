package com.zero.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.dfa.WordTree;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zero.jdTry.domain.JdGoods;
import com.zero.jdTry.service.IJdGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JdTryUtil {
    private static IJdGoodsService goodsService = null;

    public JdTryUtil(IJdGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    private static final String URL = "https://api.m.jd.com/client.action";
    private static final String ORIGIN = "https://prodev.m.jd.com";
    private static final String HOST = "api.m.jd.com";
    //每个Tab页要便遍历的申请页数
    private static final int JD_TRY_TOTALPAGES = 50;
    //获取试用商品请求发送间隔
    private static final int REQUEST_INTERVAL = 3;
    //申请试用商品请求发送间隔
    private static final int REQUEST_APPLY = 5;
    //是否过滤已申请
    private static final boolean FILTER_APPLIED = true;
    //是否过滤种草官
    private static final boolean FILTER_PLANTING_OFFICER = true;
    //是否过滤付费
    private static final boolean FILTER_PAY = true;
    //是否过滤付费
    private static final BigDecimal JD_PRICE_MIN = new BigDecimal(45);
    //提供的最大份数
    private static final int SUPPLY_NUM_MAX = 20;
    //获取试用商品类型
    private static final int[] JD_TRY_TABID = {104, 3, 4, 5, 9, 10, 15, 12, 6, 7, 8, 13, 14, 11, 16};
    //过滤的关键词
    private static final String[] INNER_KEYS = {
            "小靓美", "赠品", "MLB", "饲料", "手机电池", "样品", "面膜", "染发", "挂件", "戒烟", "宠物", "脚气", "跳蛋",
            "卷尺", "种子", "档案袋", "癣", "老太太", "妇女", "孕妇", "卫生条", "培训", "狐臭", "少女内衣", "胸罩", "洋娃娃", "男孩玩具",
            "女孩玩具", "女性内衣", "女性内裤", "女内裤", "女内衣", "女孩", "鱼饵", "钓鱼", "吊带", "黑丝", "钢圈", "娃娃",
            "网课", "网校", "电商", "网络课程", "手机壳", "手机膜", "钢化膜", "水凝膜", "车载充电器", "女纯棉", "三角裤", "英语",
            "俄语", "四级", "六级", "在线网络", "阴道炎", "宫颈", "糜烂", "打底裤", "狗", "和田", "课程", "礼物", "文玩", "检查", "电子烟",
            "体验装", "体验课", "试用装", "猫粮", "猫零食", "礼品", "玉石", "雨衣", "疤痕", "痘印", "美瞳", "帐号", "足贴", "膏药", "新视界",
            "非卖品", "微信", "睫毛", "蟑螂", "老鼠夹", "精品课", "VIP陪练", "单片装", "订金", "定金", "特权金", "热敷贴", "湿疣", "一拖三", "手镯",
            "看房", "购房", "痔疮", "商学院", "鹦鹉", "发蜡", "百安居", "猫砂", "试用", "假阳具", "震动棒", "延时喷", "延迟喷", "假发",
            "激活码", "电话卡"
            // "幼儿","儿童","纸尿裤","童装"
    };

    private static int allpuNum = 0;


    /**
     * 申请试用
     *
     * @return
     */
    public static boolean tryApply(String cookie, JdGoods goods) throws JSONException, InterruptedException {
        //1.获取试用商品列表
        //链式构建请求
        JSONObject jb = new JSONObject();
        jb.put("activityId", goods.getTrialActivityId());
        jb.put("previewTime", "");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "newtry");
        paramMap.put("functionId", "try_apply");
        paramMap.put("body", jb.toString());

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
        int sleepTimes = REQUEST_APPLY + RandomUtil.randomInt(0, 55);
        if ("Error request, response status: 403".equals(result2)) {
            TimeUnit.SECONDS.sleep(120);
            log.error("【异常】 403错误，休眠120秒后重新申请" + " " + goods.getSkuTitle());
            return true;
        } else {
            log.info("【申请商品】" + "休眠" + sleepTimes + "秒，价值" + goods.getJdPrice() + " " + goods.getSkuTitle());
            JSONObject data = JSONObject.parseObject(result2);
            if (data.getBoolean("success") && data.getString("code").equals("1")) {  // 申请成功
                allpuNum += 1;
                log.info("【申请提交成功】 第" + allpuNum + "个" + goods.getSkuTitle());
            } else if (data.getString("code").equals("-106")) {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message")); // 未在申请时间内！
            } else if (data.getString("code").equals("-110")) {
                log.info("【申请提交失败】 " + goods.getApplyState() + " " + goods.getSkuTitle() + " \n" + data.getString("message")); // 您的申请已成功提交，请勿重复申请…
            } else if (data.getString("code").equals("-110")) {
                log.info("【申请提交失败】 " + goods.toString() + "\n" + data.getString("message")); // 您的申请已成功提交，请勿重复申请…
            } else if (data.getString("code").equals("-120")) {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message")); // 您还不是会员，本品只限会员申请试用，请注册会员后申请！
            } else if (data.getString("code").equals("-167")) {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message"));// 抱歉，此试用需为种草官才能申请。查看下方详情了解更多。
            } else if (data.getString("code").equals("-131")) {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message"));  // 申请次数上限。
                allpuNum = 300;
            } else if (data.getString("code").equals("-113")) {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message")); // 操作不要太快哦！
            } else {
                log.info("【申请提交失败】 " + goods.getSkuTitle() + " \n" + data.getString("message"));
            }
            TimeUnit.SECONDS.sleep(sleepTimes);
            return false;
        }


    }

    public static Set<JdGoods> getAllGoodsList(String cookie) throws JSONException, InterruptedException {
        Set<JdGoods> list = new HashSet<>();
        for (int j = 0; j < JD_TRY_TOTALPAGES; j++) {
            for (int i = 0; i < JD_TRY_TABID.length; i++) {
                log.info("=================开始获取第" + JD_TRY_TABID[i] + "TAB,第" + j + "页=======================");
                list.addAll(getGoodsList(JD_TRY_TABID[i], j, cookie));
                log.info("=================结束获取第" + JD_TRY_TABID[i] + "TAB,第" + j + "页，共计得到" + list.size() + "条有效商品=======================");
                TimeUnit.SECONDS.sleep(REQUEST_INTERVAL);
            }
        }
        return list;
    }

    public static List<JdGoods> getGoodsList(int tabId, int page, String cookie) {
        List<JdGoods> list = new ArrayList<>();
        //1.获取试用商品列表
        //链式构建请求{"tabId":"3","page":1,"version":2,"source":"default","client":"app","previewTime":""}
        JSONObject jb = new JSONObject();
        jb.put("tabId", tabId);
        jb.put("page", page);
        jb.put("version", 2);
        jb.put("source", "default");
        jb.put("client", "app");
        jb.put("previewTime", "");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "newtry");
        paramMap.put("functionId", "try_feedsList");
        paramMap.put("body", jb.toString());


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
        JSONObject result = JSONObject.parseObject(result2);
        JSONObject data = result.getJSONObject("data");
        JSONArray feedList = data.getJSONArray("feedList");
        for (int i = 0; i < feedList.size(); i++) {
            JdGoods goods = feedList.getJSONObject(i).toJavaObject(JdGoods.class);
            if (ObjectUtil.isNotNull(goods) && validGoods(goods)) {
                list.add(goods);
            }
        }
        return list;

    }

    public static boolean validGoods(JdGoods goods) {
        boolean flag = true;
        WordTree tree = new WordTree();
        tree.addWords(INNER_KEYS);

        if (ObjectUtil.isNull(goods.getSkuTitle())) {
            flag = false;
            log.info("【商品过滤】无效商品：信息获取失败");
        } else if (FILTER_APPLIED && (ObjectUtil.isNotNull(goods.getApplyState())
                && (goods.getApplyState().equals(1)))) {//已申请过滤
            flag = false;
            log.info("【商品过滤】已申请：" + goods.getActivityStatus() + " " + goods.getTrialActivityId() + " " + goods.getSkuTitle());
        } else if (goods.getTagList().size() > 0) {
            int tagType = goods.getTagList().get(0).getTagType();
            if (FILTER_PLANTING_OFFICER && tagType == 3) {//种草官过滤
                flag = false;
                log.info("【商品过滤】种草官过滤：" + goods.getSkuTitle());
            } else if (FILTER_PAY && tagType == 5) {//付费过滤
                flag = false;
                log.info("【商品过滤】付费过滤：" + goods.getSkuTitle());
            }
        } else if (JD_PRICE_MIN.compareTo(goods.getJdPrice()) >= 0) {//最低价过滤
            flag = false;
            log.info("【商品过滤】最低价过滤：" + goods.getJdPrice() + "元  " + goods.getSkuTitle());
        } else if (SUPPLY_NUM_MAX < goods.getSupplyNum()) {
            flag = false;
        } else if (keyWordsFilter(goods.getSkuTitle(), tree)) {
            flag = false;
        }

        return flag;
    }

    private static boolean keyWordsFilter(String skuTitle, WordTree tree) {
        List<String> matchAll = tree.matchAll(skuTitle, -1, false, false);
        if (matchAll.size() > 0) {
            log.info("【商品过滤】关键词过滤：" + matchAll.toString() + "  " + skuTitle);
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws JSONException, InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        String cookie = "pt_key=AAJiitlYADB2Hfeiq2BRpPVkph2OSW_bPFBX0JStVe09lqWsH1erO6x8193lB2jDQg2sIYjbLvM; pt_pin=as15621009921;";
        Set<JdGoods> list = getAllGoodsList(cookie);
        goodsService.saveBatch(list);
        list.stream().sorted(Comparator.comparing(JdGoods::getJdPrice).reversed())
                .forEach(x -> {
                    if (allpuNum >= 300) {
                        log.info("申请数目已经达到300");
                        return;
                    }
                    try {
                        do {
                        } while (tryApply(cookie, x));
                    } catch (JSONException e) {
                        log.warn(e.getMessage());
                    } catch (InterruptedException e) {
                        log.warn(e.getMessage());
                    }
                });
        log.info("本次执行结束");
        log.info("本次申请商品：" + list.size());
        log.info("本次提交商品：" + allpuNum);
        sw.stop();
        log.info("任务執行结束 {}，耗時：{}分钟", LocalDateTime.now(), sw.getTotalTimeSeconds() / 60);
    }
}
