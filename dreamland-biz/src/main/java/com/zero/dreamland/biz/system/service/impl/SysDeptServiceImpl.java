package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysDeptDao;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements ISysDeptService {
    @Resource
    private SysDeptDao sysDeptDao;

    @Override
    public List<String> getDeptChildren(String deptId, List<SysDept> deptList) {
        List<String> list = new ArrayList<>();
        deptList.forEach(dept -> {
                    if (dept != null && dept.getEnabled()) {
                        List<SysDept> depts = sysDeptDao.selectList(new QueryWrapper<SysDept>().eq("pid", dept.getId()));
                        if (deptList.size() != 0) {
                            list.addAll(getDeptChildren(dept.getId(), depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }


}