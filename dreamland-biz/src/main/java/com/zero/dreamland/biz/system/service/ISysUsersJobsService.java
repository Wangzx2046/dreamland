package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysJob;
import com.zero.dreamland.biz.system.entity.SysUsersJobs;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysUsersJobsService extends IService<SysUsersJobs> {

    List<String> getJobIdsByUserId(String userId);

    int insert(String userId, Set<SysJob> jobs);

    int update(String userId, Set<SysJob> jobs);
}
