package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysDeptDao;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.entity.SysRolesDepts;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import com.zero.dreamland.biz.system.service.ISysRolesDeptsService;
import com.zero.dreamland.biz.system.service.ISysUserService;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.utils.FileUtil;
import com.zero.dreamland.redis.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private ISysUserService iSysUserService;
    @Resource
    private ISysRolesDeptsService iSysRolesDeptsService;


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
    public boolean updateById(SysDept sysDept) {
        // 旧的部门
        String oldPid = sysDeptDao.selectById(sysDept.getId()).getPid();
        String newPid = sysDept.getPid();
        if (sysDept.getPid() != null && sysDept.getId().equals(sysDept.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        SysDept dept = sysDeptDao.selectById(sysDept.getId());
        if (dept == null) {
            throw new BadRequestException("部门不存在");
        }

        sysDept.setId(dept.getId());
        sysDeptDao.updateById(sysDept);
        // 更新父节点中子节点数目
        updateSubCnt(oldPid);
        updateSubCnt(newPid);

        return true;
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
            depts.addAll(sysDeptDao.selectList(new QueryWrapper<SysDept>().isNull("pid")));
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
        map.put("total", sysDepts.size());
        map.put("list", CollectionUtil.isEmpty(trees) ? sysDepts : trees);
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


    @Override
    public void verification(Set<SysDept> deptDtos) {
        Set<String> deptIds = deptDtos.stream().map(SysDept::getId).collect(Collectors.toSet());
        if (iSysUserService.count(new QueryWrapper<SysUser>().in("dept_id", deptIds)) > 0) {
            throw new BadRequestException("所选部门存在用户关联，请解除后再试！");
        }
        if (iSysRolesDeptsService.count(new QueryWrapper<SysRolesDepts>().in("dept_id", deptIds)) > 0) {
            throw new BadRequestException("所选部门存在角色关联，请解除后再试！");
        }
    }

    @Override
    public void download(SysDept sysDept, HttpServletResponse response) throws IOException {
        List<SysDept> deptList=this.list(sysDept);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysDept deptDTO : deptList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("部门名称", deptDTO.getName());
            map.put("部门状态", deptDTO.getEnabled() ? "启用" : "停用");
            map.put("创建日期", deptDTO.getCreateDate());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}