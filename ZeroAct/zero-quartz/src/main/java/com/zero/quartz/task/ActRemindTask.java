package com.zero.quartz.task;

import com.zero.act.service.IActInfoService;
import com.zero.act.service.IActRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author wzx
 */
@Component("actRemindTask")
public class ActRemindTask {

    @Autowired
    private IActRemindService actRemindService;

    @Autowired
    private IActInfoService actInfoService;

    /**
     * 生成提醒
     */
    public void generateReminder() {
        System.out.println("执行无参方法==>活动提醒");


    }


}
