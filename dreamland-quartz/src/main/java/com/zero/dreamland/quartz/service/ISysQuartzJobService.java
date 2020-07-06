package com.zero.dreamland.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.quartz.entity.SysQuartzJob;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-02
 */
public interface ISysQuartzJobService extends IService<SysQuartzJob> {

    List<SysQuartzJob> list(SysQuartzJob sysQuartzJob);

    /**
     * 查询启用的任务
     *
     * @return List
     */
    List<SysQuartzJob> findByIsPauseIsFalse();

    /**
     * 更改定时任务状态
     *
     * @param quartzJob /
     */
    void updateIsPause(SysQuartzJob quartzJob);

    /**
     * 立即执行定时任务
     *
     * @param quartzJob /
     */
    void execution(SysQuartzJob quartzJob);

    /**
     * 导出定时任务
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<SysQuartzJob> queryAll, HttpServletResponse response) throws IOException;


    /**
     * 执行子任务
     *
     * @param tasks /
     * @throws InterruptedException /
     */
    void executionSubJob(String[] tasks) throws InterruptedException;

}
