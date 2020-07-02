package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysUsersJobsDao;
import com.zero.dreamland.biz.system.entity.SysJob;
import com.zero.dreamland.biz.system.entity.SysUsersJobs;
import com.zero.dreamland.biz.system.service.ISysUsersJobsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service

public class SysUsersJobsServiceImpl extends ServiceImpl<SysUsersJobsDao, SysUsersJobs> implements ISysUsersJobsService {

    @Resource
    private SysUsersJobsDao sysUsersJobsDao;

    @Override
    public List<String> getJobIdsByUserId(String userId) {
        List<Object> sysJobsRolesList = sysUsersJobsDao.selectObjs(new QueryWrapper<SysUsersJobs>()
                .select("job_id")
                .eq("user_id", userId));
        return (List<String>) (List) sysJobsRolesList;
    }

    @Override
    public int insert(String userId, Set<SysJob> jobs) {
        jobs.forEach(x -> {
            SysUsersJobs sysUsersJobs = new SysUsersJobs();
            sysUsersJobs.setUserId(userId);
            sysUsersJobs.setJobId(x.getId());
            this.save(sysUsersJobs);
        });
        return 1;
    }

    @Override
    public int update(String userId, Set<SysJob> jobs) {
        sysUsersJobsDao.delete(new QueryWrapper<SysUsersJobs>().eq("user_id", userId));
        return this.insert(userId, jobs);
    }
}
