/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zero.dreamland.quartz.task;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.zero.dreamland.biz.zkb.entity.ZkbXgyz;
import com.zero.dreamland.biz.zkb.service.IZkbXgyzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 定时任务-小鸽驿站
 *
 * @author wzx
 * @date 2021-01-22
 */
@Slf4j
@Component
public class ZkbXgyzTask {

    @Autowired
    private IZkbXgyzService iZkbXgyzService;

    private boolean flag = false;

    /*
        小鸽驿站每分钟抓一次赚吧数据，因此定时任务设置为每分钟的第5S去抓一次小鸽驿站的数据
     */
    public void CrawlerXgyz(String keys) {

        String[] strs = keys.split(",");

        String url = "http://zuan.xiaogeyizhan.com/msgPush/contentList?keys=" + keys + "&offset=0";
        String rel = HttpUtil.get(url);

        JSONArray ja = JSONArray.parseArray(rel);
        List<ZkbXgyz> list = ja.toJavaList(ZkbXgyz.class);

        list.forEach(x -> {
            try {
                if (StrUtil.isNotBlank(keys)) {
                    x.setKeyWord(StrUtil.containsAny(x.getLabel(), strs) ? "1" : "0");
                } else {
                    x.setKeyWord("");
                }

                iZkbXgyzService.saveOrUpdate(x);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }


    public void delHistory() {
        log.info("小鸽驿站执行：删除历史数据");
        LocalDate day = LocalDate.now().minusDays(2);

        iZkbXgyzService.deleteBeforeDate(day);

    }

    public void delHistoryByDays(int i) {
        log.info("小鸽驿站执行：删除历史数据");
        LocalDate day = LocalDate.now().minusDays(i);
        iZkbXgyzService.deleteBeforeDate(day);

    }


    public void CrawlerXgyzAll() {
        CrawlerXgyz("");
    }


}
