package com.zero.dreamland.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.quartz.entity.SysQuartzLog;

import java.util.List;

/**
 * <p>
 * 定时任务日志 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-02
 */
public interface ISysQuartzLogService extends IService<SysQuartzLog> {

 List<SysQuartzLog> list(SysQuartzLog sysQuartzLog);
}
