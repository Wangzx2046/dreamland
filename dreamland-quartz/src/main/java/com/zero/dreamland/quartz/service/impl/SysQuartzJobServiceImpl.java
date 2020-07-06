package com.zero.dreamland.quartz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.quartz.dao.SysQuartzJobDao;
import com.zero.dreamland.quartz.entity.SysQuartzJob;
import com.zero.dreamland.quartz.service.ISysQuartzJobService;
import com.zero.dreamland.quartz.utils.QuartzManage;
import com.zero.dreamland.redis.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-02
 */
@Service
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobDao, SysQuartzJob> implements ISysQuartzJobService {

    @Resource
    private SysQuartzJobDao sysQuartzJobDao;
    @Resource
    private QuartzManage quartzManage;
    @Resource
    private RedisUtil redisUtils;

    @Override
    public List<SysQuartzJob> list(SysQuartzJob sysQuartzJob) {
        QueryWrapper<SysQuartzJob> queryWrapper = new QueryWrapper<>(sysQuartzJob);

        return sysQuartzJobDao.selectList(queryWrapper);
    }

    @Override
    public List<SysQuartzJob> findByIsPauseIsFalse() {
        SysQuartzJob sysQuartzJob = new SysQuartzJob();
        sysQuartzJob.setIsPause(false);
        List<SysQuartzJob> list = list(sysQuartzJob);
        return list;
    }

    @Override
    public void updateIsPause(SysQuartzJob quartzJob) {


        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
        } else {
            quartzManage.pauseJob(quartzJob);
        }

        SysQuartzJob quartzJobNew = new SysQuartzJob();
        quartzJobNew.setId(quartzJob.getId());
        quartzJobNew.setIsPause(!quartzJob.getIsPause());
        sysQuartzJobDao.updateById(quartzJobNew);

    }

    @Override
    public void execution(SysQuartzJob quartzJob) {
        quartzManage.runJobNow(quartzJob);
    }

    @Override
    public void download(List<SysQuartzJob> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void executionSubJob(String[] tasks) throws InterruptedException {
        for (String id : tasks) {
            SysQuartzJob quartzJob = getById(id);
            // 执行任务
            String uuid = IdUtil.simpleUUID();
            quartzJob.setUuid(uuid);
            // 执行任务
            execution(quartzJob);
            // 获取执行状态，如果执行失败则停止后面的子任务执行
            Boolean result = (Boolean) redisUtils.get(uuid);
            while (result == null) {
                // 休眠5秒，再次获取子任务执行情况
                Thread.sleep(5000);
                result = (Boolean) redisUtils.get(uuid);
            }
            if (!result) {
                redisUtils.delete(uuid);
                break;
            }
        }
    }


    @Override
    public boolean save(SysQuartzJob sysQuartzJob) {
        if (!CronExpression.isValidExpression(sysQuartzJob.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        sysQuartzJobDao.insert(sysQuartzJob);
        quartzManage.addJob(sysQuartzJob);
        return true;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            SysQuartzJob quartzJob = getById(id);
            quartzManage.deleteJob(quartzJob);

        }
        return sysQuartzJobDao.deleteBatchIds(idList) > 0;

    }

    @Override
    public boolean updateById(SysQuartzJob sysQuartzJob) {
        if (!CronExpression.isValidExpression(sysQuartzJob.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        if (StringUtils.isNotBlank(sysQuartzJob.getSubTask())) {
            List<String> tasks = Arrays.asList(sysQuartzJob.getSubTask().split("[,，]"));
            if (tasks.contains(sysQuartzJob.getId().toString())) {
                throw new BadRequestException("子任务中不能添加当前任务ID");
            }
        }
        sysQuartzJobDao.updateById(sysQuartzJob);
        quartzManage.updateJobCron(sysQuartzJob);
        return true;
    }
}
