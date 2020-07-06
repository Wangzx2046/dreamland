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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 测试用
 *
 * @author Zheng Jie
 * @date 2019-01-08
 */
@Slf4j
@Component
public class EmailRemindTask {

    @Autowired
    private JavaMailSender mailSender;

    public void Remind() {
        log.info("run 执行成功");
    }

    public void Remind2(String str) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jbinfoecard@163.com");// 发送者.
        message.setTo("443082604@qq.com");// 接收者.
        message.setSubject("密码找回");// 邮件主题.
        message.setText(str);// 邮件内容.
        mailSender.send(message);// 发送邮件
        log.info("Remind2 执行成功，参数为： {}" + str);
    }

    public void Remind3() {
        log.info("run2 执行成功");
    }


}
