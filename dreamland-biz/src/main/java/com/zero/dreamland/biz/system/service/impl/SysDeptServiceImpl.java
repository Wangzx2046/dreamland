package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysDeptDao;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import com.zero.dreamland.redis.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Resource
    private RedisUtil redisUtils;


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

    @Override
    public List<SysDept> queryAll(SysDept sysDept, boolean isQuery) throws Exception {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>(sysDept);

        if (isQuery) {
            if (StringUtils.isBlank(sysDept.getPid())) {
                queryWrapper.isNull("pid");
            }
        }

        List<SysDept> allList = sysDeptDao.selectList(queryWrapper);
        return allList;
    }

    @Override
    public List<SysDept> list(SysDept sysDept) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>(sysDept);

        return sysDeptDao.selectList(queryWrapper);
    }


    @Override
    public boolean save(SysDept sysDept) {

        sysDeptDao.insert(sysDept);
        // 计算子节点数目
        sysDept.setSubCount(0);
        // 清理缓存
        redisUtils.delete("dept::pid:" + (sysDept.getPid() == null ? 0 : sysDept.getPid()));
        updateSubCnt(sysDept.getPid());

        return true;
    }

    @Override
    public List<SysDept> getSuperior(SysDept sysDept, List<SysDept> depts) {
        if (sysDept.getPid() == null) {
            depts.addAll(sysDeptDao.selectList(new QueryWrapper<SysDept>().isNotNull("pid")));
            return depts;
        }
        depts.addAll(sysDeptDao.selectList(new QueryWrapper<SysDept>().eq("pid", sysDept.getPid())));
        return getSuperior(sysDeptDao.selectById(sysDept.getPid()), depts);
    }

    @Override
    public Object buildTree(List<SysDept> sysDepts) {
        Set<SysDept> trees = new LinkedHashSet<>();
        Set<SysDept> depts = new LinkedHashSet<>();
        List<String> deptNames = sysDepts.stream().map(SysDept::getName).collect(Collectors.toList());
        boolean isChild;
        for (SysDept sysDept : sysDepts) {
            isChild = false;
            if (sysDept.getPid() == null) {
                trees.add(sysDept);
            }
            for (SysDept it : sysDepts) {
                if (it.getPid() != null && sysDept.getId().equals(it.getPid())) {
                    isChild = true;

                    if (sysDept.getChildren() == null) {
                        sysDept.setChildren(new ArrayList<>());
                    }
                    sysDept.getChildren().add(it);
                }
            }
            if (isChild) {
                depts.add(sysDept);
            } else if (sysDept.getPid() != null && !deptNames.contains(sysDeptDao.selectById(sysDept.getPid()).getName())) {
                depts.add(sysDept);
            }
        }

        if (CollectionUtil.isEmpty(trees)) {
            trees = depts;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("totalElements", sysDepts.size());
        map.put("content", CollectionUtil.isEmpty(trees) ? sysDepts : trees);
        return map;
    }


    private void updateSubCnt(String deptId) {
        if (deptId != null) {
            int count = sysDeptDao.selectCount(new QueryWrapper<SysDept>().eq("pid", deptId));

            SysDept sysDept = new SysDept();
            sysDept.setSubCount(count);
            sysDept.setId(deptId);
            sysDeptDao.updateById(sysDept);
        }
    }
}