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
package com.zero.dreamland.quartz.utils;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.zero.dreamland.common.StringUtils.UUIDGenerator;
import com.zero.dreamland.common.email.domain.EmailVo;
import com.zero.dreamland.common.thread.ThreadPoolExecutorUtil;
import com.zero.dreamland.common.utils.SpringContextHolder;
import com.zero.dreamland.common.utils.ThrowableUtil;
import com.zero.dreamland.quartz.entity.SysQuartzJob;
import com.zero.dreamland.quartz.entity.SysQuartzLog;
import com.zero.dreamland.quartz.service.ISysQuartzJobService;
import com.zero.dreamland.quartz.service.ISysQuartzLogService;
import com.zero.dreamland.redis.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 参考人人开源，https://gitee.com/renrenio/renren-security
 *
 * @author /
 * @date 2019-01-07
 */
@Async
@SuppressWarnings({"unchecked", "all"})
public class ExecutionJob extends QuartzJobBean {

    /**
     * 该处仅供参考
     */
    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();



    @Override
    public void executeInternal(JobExecutionContext context) {
        SysQuartzJob quartzJob = (SysQuartzJob) context.getMergedJobDataMap().get(SysQuartzJob.JOB_KEY);
        // 获取spring bean
         ISysQuartzLogService iSysQuartzLogService= SpringContextHolder.getBean(ISysQuartzLogService.class);
         ISysQuartzJobService iSysQuartzJobService= SpringContextHolder.getBean(ISysQuartzJobService.class);
         RedisUtil redisUtils = SpringContextHolder.getBean(RedisUtil.class);

        String uuid = UUIDGenerator.getUUID();

        SysQuartzLog log = new SysQuartzLog();
        log.setJobName(quartzJob.getJobName());
        log.setCreateDate(LocalDateTime.now());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            System.out.println("--------------------------------------------------------------");
            System.out.println("任务开始执行，任务名称：" + quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = EXECUTOR.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            if (StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, "true");
            }
            // 任务状态
            log.setIsSuccess(true);
            System.out.println("任务执行完毕，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            System.out.println("--------------------------------------------------------------");
            // 判断是否存在子任务
            if (quartzJob.getSubTask() != null) {
                String[] tasks = quartzJob.getSubTask().split("[,，]");
                // 执行子任务
                iSysQuartzJobService.executionSubJob(tasks);
            }
        } catch (Exception e) {
            if (StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, "false");
            }
            System.out.println("任务执行失败，任务名称：" + quartzJob.getJobName());
            System.out.println("--------------------------------------------------------------");
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
            if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
                quartzJob.setIsPause(false);
                //更新状态
                iSysQuartzJobService.updateIsPause(quartzJob);
            }
            if (quartzJob.getEmail() != null) {
            /*    EmailService emailService = SpringContextHolder.getBean(EmailService.class);
                // 邮箱报警
                EmailVo emailVo = taskAlarm(quartzJob, ThrowableUtil.getStackTrace(e));
                emailService.send(emailVo, emailService.find());*/
            }
        } finally {
            iSysQuartzLogService.save(log);
        }
    }

    private EmailVo taskAlarm(SysQuartzJob quartzJob, String msg) {
        EmailVo emailVo = new EmailVo();
        emailVo.setSubject("定时任务【" + quartzJob.getJobName() + "】执行失败，请尽快处理！");
        Map<String, Object> data = new HashMap<>(16);
        data.put("task", quartzJob);
        data.put("msg", msg);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email/taskAlarm.ftl");
        emailVo.setContent(template.render(data));
        List<String> emails = Arrays.asList(quartzJob.getEmail().split("[,，]"));
        emailVo.setTos(emails);
        return emailVo;
    }
}
