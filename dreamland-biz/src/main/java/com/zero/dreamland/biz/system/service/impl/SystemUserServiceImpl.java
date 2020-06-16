package com.zero.dreamland.biz.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemUserDao;
import com.zero.dreamland.biz.system.entity.SystemPermi;
import com.zero.dreamland.biz.system.entity.SystemRole;
import com.zero.dreamland.biz.system.entity.SystemRolePermi;
import com.zero.dreamland.biz.system.entity.SystemUser;
import com.zero.dreamland.biz.system.entity.SystemUserRole;
import com.zero.dreamland.biz.system.service.ISystemPermiService;
import com.zero.dreamland.biz.system.service.ISystemRoleService;
import com.zero.dreamland.biz.system.service.ISystemUserService;
import com.zero.dreamland.biz.system.service.SystemRolePermiService;
import com.zero.dreamland.biz.system.service.SystemUserRoleService;
import com.zero.dreamland.common.password.MyPasswordEncoder;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: SystemUserServiceImpl
 * @Description: 系统用户表*
 * @Author: wzx
 * @Date: 2019-07-18 15:31
 * @Version 1.0.0
 **/
@Service("systemUserService")
public class SystemUserServiceImpl extends ServiceImpl<SystemUserDao, SystemUser> implements ISystemUserService {


    @Resource
    private SystemUserDao systemUserDao;
    @Resource
    private ISystemRoleService systemRoleService;
    @Resource
    private ISystemPermiService systemPermiService;
    @Resource
    private SystemRolePermiService systemRolePermiService;
    @Resource
    private SystemUserRoleService systemUserRoleService;


    @Override
    public Result add(SystemUser systemUser) {
        systemUser.setUserEnablePassword(systemUser.getUserPassword());
        systemUser.setUserPassword((new MyPasswordEncoder()).encode(systemUser.getUserPassword()));
        if (systemUserDao.insert(systemUser) == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failed();
        }

    }

    @Override
    public List<SystemUser> list() {
        return systemUserDao.selectList(new QueryWrapper<>());
    }

    @Override
    public SystemUser getOne(QueryWrapper<SystemUser> wrapper) {
        return systemUserDao.selectOne(wrapper);
    }

    @Override
    public Result update(SystemUser systemUser) {
        return null;
    }

    //根据用户名获取全部角色
    @Override
    public Set<String> getUserRolesSetByAccount(String userName) {
        SystemUser systemUser = systemUserDao.selectOne(new QueryWrapper<SystemUser>().eq("user_name", userName));
        return this.getUserRolesSetByUserId(systemUser.getId());
    }

    //根据用户ID获取全部角色
    @Override
    public Set<String> getUserRolesSetByUserId(String userId) {
        List<SystemUserRole> list = systemUserRoleService.list(new QueryWrapper<SystemUserRole>().eq("user_id", userId));
        List<String> roleIdList = list.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        List<SystemRole> roleList = systemRoleService.list(new QueryWrapper<SystemRole>().in("id", roleIdList));
        List<String> reList = roleList.stream().map(e -> e.getRoleType()).collect(Collectors.toList());
        return new HashSet(reList);
    }


    //根据角色获取全部权限
    @Override
    public Set<String> getUserPermissionsSetByRoles(Set<String> roles) {
        List<SystemRolePermi> list = systemRolePermiService.list(new QueryWrapper<SystemRolePermi>().in("permi_id", roles));
        List<String> permiList = list.stream().map(e -> e.getPermiId()).collect(Collectors.toList());
        List<SystemPermi> rplist = systemPermiService.list(new QueryWrapper<SystemPermi>().in("id", permiList));
        List<String> reList = rplist.stream().map(e -> e.getPermiSign()).collect(Collectors.toList());
        return new HashSet(reList);
    }

    @Override
    public HashMap<String, Object> getUserRolesAndPermissionsByAccount(String userName) {
        //1.获取账户
        SystemUser systemUser = systemUserDao.selectOne(new QueryWrapper<SystemUser>().eq("user_name", userName));
        return this.getUserRolesAndPermissionsByUserId(systemUser.getId());
    }


    @Override
    public HashMap<String, Object> getUserRolesAndPermissionsByUserId(String userId) {
        //1.获取全部角色
        List<SystemUserRole> list = systemUserRoleService.list(new QueryWrapper<SystemUserRole>().eq("user_id", userId));
        List<String> roleIdList = list.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        List<SystemRole> roleList = systemRoleService.list(new QueryWrapper<SystemRole>().in("id", roleIdList));
        List<String> roleTypes = roleList.stream().map(e -> e.getRoleType()).collect(Collectors.toList());


        //获取角色的全部权限
        List<SystemRolePermi> rplist = systemRolePermiService.list(new QueryWrapper<SystemRolePermi>().in("role_id", new HashSet(roleIdList)));
        List<String> permiIdList = rplist.stream().map(e -> e.getPermiId()).collect(Collectors.toList());
        List<SystemPermi> permiList = systemPermiService.list(new QueryWrapper<SystemPermi>().in("id", permiIdList));
        List<String> permiSignList = permiList.stream().map(e -> e.getPermiSign()).collect(Collectors.toList());

        HashMap<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("roleList", roleList);
        reMap.put("permiList", permiList);
      /*HashMap<String,Set<String>> reMap=new  HashMap<String,Set<String>>();
        reMap.put("roles",new HashSet(roleTypes));
        reMap.put("permis",new HashSet(permiSignList));*/
        return reMap;
    }
}
