package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemLogMapper;
import com.zero.dreamland.biz.system.entity.SystemLog;
import com.zero.dreamland.biz.system.service.ISystemLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-03-24
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {

}
