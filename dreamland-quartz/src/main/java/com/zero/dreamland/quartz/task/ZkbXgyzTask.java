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

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.zero.dreamland.biz.zkb.entity.ZkbXgyz;
import com.zero.dreamland.biz.zkb.service.IZkbXgyzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 测试用
 *
 * @author Zheng Jie
 * @date 2019-01-08
 */
@Slf4j
@Component
public class ZkbXgyzTask {

    @Autowired
    private IZkbXgyzService iZkbXgyzService;


    public void CrawlerXgyz(String keys) {


        log.info("小鸽驿站执行访问");
        String url = "http://zuan.xiaogeyizhan.com/msgPush/contentList?keys" + keys + "&offset=0";
        String rel = HttpUtil.get(url);

        JSONArray ja = JSONArray.parseArray(rel);
        log.info(ja.toJSONString());
        List<ZkbXgyz> list = ja.toJavaList(ZkbXgyz.class);

        list.forEach(x -> {
            try {
                iZkbXgyzService.save(x);
            } catch (DuplicateKeyException e) {
                iZkbXgyzService.updateById(x);
            }
        });
        log.info("小鸽驿站执行访问结束");
    }



    public void delHistory(){
        log.info("小鸽驿站执行：删除历史数据");
        LocalDate day=LocalDate.now().minusDays(2);

        iZkbXgyzService.deleteBeforeDate(day);

    }


    public void CrawlerXgyzAll() {
        CrawlerXgyz("");
    }


}
