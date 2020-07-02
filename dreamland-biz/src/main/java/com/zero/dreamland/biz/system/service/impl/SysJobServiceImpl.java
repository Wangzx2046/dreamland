package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysJobDao;
import com.zero.dreamland.biz.system.entity.SysJob;
import com.zero.dreamland.biz.system.entity.SysUsersJobs;
import com.zero.dreamland.biz.system.service.ISysJobService;
import com.zero.dreamland.biz.system.service.ISysUsersJobsService;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.utils.FileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-19
 */
@Service

public class SysJobServiceImpl extends ServiceImpl<SysJobDao, SysJob> implements ISysJobService {

    @Resource
    private SysJobDao sysJobDao;

    @Resource
    private ISysUsersJobsService iSysUsersJobsService;


    @Override
    public List<SysJob> list(SysJob sysJob) {
        QueryWrapper<SysJob> queryWrapper = new QueryWrapper<>(sysJob);
        if (StringUtils.isNotBlank(sysJob.getBlurry())) {
            queryWrapper.eq("name", sysJob.getBlurry())
                    .or().eq("description", sysJob.getBlurry());
        }
        return sysJobDao.selectList(queryWrapper);
    }

    @Override
    public List<SysJob> findByUsersId(String currentUserId) {
        List<String> sysUsersJobsList = iSysUsersJobsService.getJobIdsByUserId(currentUserId);
        if (sysUsersJobsList.size() == 0) {
            return new ArrayList<SysJob>();
        }
        List<SysJob> jobList = sysJobDao.selectBatchIds((List<String>) (List) sysUsersJobsList);
        return jobList;
    }


    @Override
    public boolean removeByIds(Set<String> ids) {
        this.verification(ids);
        sysJobDao.deleteBatchIds(ids);
        return true;
    }

    @Override
    public void verification(Set<String> ids) {
        if (iSysUsersJobsService.count(new QueryWrapper<SysUsersJobs>().in("job_id", ids)) > 0) {
            throw new BadRequestException("所选的岗位中存在用户关联，请解除关联再试！");
        }
    }

    @Override
    public void download(List<SysJob> jobDtos, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysJob jobDTO : jobDtos) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("岗位名称", jobDTO.getName());
            map.put("岗位状态", jobDTO.getEnabled() ? "启用" : "停用");
            map.put("创建日期", jobDTO.getCreateDate());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
