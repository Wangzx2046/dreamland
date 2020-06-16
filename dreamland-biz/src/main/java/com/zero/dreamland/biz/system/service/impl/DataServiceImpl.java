/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.entity.SysRole;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.DataService;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import com.zero.dreamland.common.instance.enums.DataScopeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zheng Jie
 * @website https://el-admin.vip
 * @description 数据权限服务实现
 * @date 2020-05-07
 **/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class DataServiceImpl implements DataService {

    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUsersRolesService sysUsersRolesService;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 用户角色改变时需清理缓存
     *
     * @param user /
     * @return /
     */
    @Override
    //@Cacheable(key = "'user:' + #p0.id")
    public List<String> getDeptIds(SysUser user) {

        // 用于存储部门id
        List<String> deptIds = new ArrayList<>();
        // 查询用户角色
        List<String> roles = sysUsersRolesService.getRoleIdsByUserId(user.getId());
        List<SysRole> roleSet = roleService.listByIds((List<String>) (List) roles);
        // 获取对应的部门ID
        for (SysRole role : roleSet) {
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            switch (Objects.requireNonNull(dataScopeEnum)) {
                case THIS_LEVEL:
                    deptIds.add(user.getDept().getId());
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(getCustomize(deptIds, role));
                    break;
                default:
                    break;
            }
        }
        return new ArrayList<>(deptIds);
    }

    /**
     * 获取自定义的数据权限
     *
     * @param deptIds 部门ID
     * @param role    角色
     * @return 数据权限ID
     */
    public List<String> getCustomize(List<String> deptIds, SysRole role) {
        List<SysDept> depts = deptService.list(new QueryWrapper<SysDept>().eq("role_id", role.getId()));
        for (SysDept dept : depts) {
            deptIds.add(dept.getId());
            List<SysDept> deptChildren = deptService.list(new QueryWrapper<SysDept>().eq("pid", dept.getId()));
            if (deptChildren != null && deptChildren.size() != 0) {
                deptIds.addAll(deptService.getDeptChildren(dept.getId(), deptChildren));
            }
        }
        return deptIds;
    }
}
