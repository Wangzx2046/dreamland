package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysUsersRolesDao;
import com.zero.dreamland.biz.system.entity.SysUsersRoles;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesDao, SysUsersRoles> implements ISysUsersRolesService {
    @Resource
    private SysUsersRolesDao sysUsersRolesDao;

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        List<Object> sysUsersRolesList = sysUsersRolesDao.selectObjs(new QueryWrapper<SysUsersRoles>()
                .select("role_id")
                .eq("user_id", userId));
        return (List<String>) (List) sysUsersRolesList;
    }
}
