package com.zero.dreamland.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.quartz.dao.SysQuartzLogDao;
import com.zero.dreamland.quartz.entity.SysQuartzLog;
import com.zero.dreamland.quartz.service.ISysQuartzLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-02
 */
@Service
public class SysQuartzLogServiceImpl extends ServiceImpl<SysQuartzLogDao, SysQuartzLog> implements ISysQuartzLogService {

    @Resource
    private SysQuartzLogDao sysQuartzLogDao;


    @Override
    public List<SysQuartzLog> list(SysQuartzLog sysQuartzLog) {
        QueryWrapper<SysQuartzLog> queryWrapper = new QueryWrapper<>(sysQuartzLog);
        queryWrapper.orderBy(true, false, "create_date");
        return sysQuartzLogDao.selectList(queryWrapper);
    }


    @Override
    public boolean save(SysQuartzLog entity) {
        return sysQuartzLogDao.insert(entity) == 1;
    }
}
