package com.zero.dreamland.biz.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysUserDao;
import com.zero.dreamland.biz.system.entity.SysDept;
import com.zero.dreamland.biz.system.entity.SysUser;
import com.zero.dreamland.biz.system.service.DataService;
import com.zero.dreamland.biz.system.service.ISysDeptService;
import com.zero.dreamland.biz.system.service.ISysJobService;
import com.zero.dreamland.biz.system.service.ISysRoleService;
import com.zero.dreamland.biz.system.service.ISysUserService;
import com.zero.dreamland.biz.system.service.ISysUsersJobsService;
import com.zero.dreamland.biz.system.service.ISysUsersRolesService;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.exception.EntityExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashSet;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    @Resource
    private ISysDeptService iSysDeptService;
    @Resource
    private ISysUsersRolesService sysUsersRolesService;
    @Resource
    private ISysUsersJobsService iSysUsersJobsService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysJobService sysJobService;

    @Resource
    private DataService dataService;
    @Autowired
    private SysUserDao sysUserDao;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public List<SysUser> list(SysUser sysUser, String currentUsername) {
        if (!ObjectUtils.isEmpty(sysUser.getDeptId())) {
            sysUser.getDeptIds().add(sysUser.getDeptId());
            sysUser.getDeptIds().addAll(iSysDeptService.getDeptChildren(sysUser.getDeptId(),
                    iSysDeptService.list(new QueryWrapper<SysDept>().eq("pid", sysUser.getDeptId()))));
        }

        // 数据权限
        List<String> dataScopes = dataService.getDeptIds(this.getByUserName(currentUsername));
        // criteria.getDeptIds() 不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(sysUser.getDeptIds()) && !CollectionUtils.isEmpty(dataScopes)) {
            // 取交集
            sysUser.getDeptIds().retainAll(dataScopes);
            if (!CollectionUtil.isEmpty(sysUser.getDeptIds())) {
                //   return sysUserDao.selectList(new QueryWrapper<SysUser>(sysUser));
            }
        } else {
            // 否则取并集
            if (dataScopes.size() > 0) {
                sysUser.getDeptIds().addAll(dataScopes);
            }
            //  return sysUserDao.selectList(new QueryWrapper<SysUser>(sysUser));
        }
        List<SysUser> userList = sysUserDao.selectList(new QueryWrapper<SysUser>(sysUser));
        userList.stream().forEach(x -> {
            x.setDept(iSysDeptService.getById(x.getDeptId()));
            x.setRoles(new HashSet(sysRoleService.findByUsersId(x.getId())));
            x.setJobs(new HashSet(sysJobService.findByUsersId(x.getId())));
        });


        return userList;
    }

    @Override
    public SysUser getByUserName(String currentUsername) {
        return this.getOne(new QueryWrapper<SysUser>().eq("user_name", currentUsername));
    }

    @Override
    public boolean save(SysUser sysUser, String currentUserId) {
        checkLevel(sysUser, currentUserId);
        // 默认密码 123456
        sysUser.setUserPassword(passwordEncoder.encode("123456"));

        if (getByUserName(sysUser.getUserName()) != null) {
            throw new EntityExistException(SysUser.class, "username", sysUser.getUserName());
        }
        sysUserDao.insert(sysUser);
        sysUsersRolesService.insert(sysUser.getId(), sysUser.getRoles());
        iSysUsersJobsService.insert(sysUser.getId(), sysUser.getJobs());

        return  true;
    }


    @Override
    public boolean updateById(SysUser sysUser, String currentUserId) {
        checkLevel(sysUser, currentUserId);
        SysUser user = getById(sysUser.getId());
        if (null == user) {
            user = new SysUser();
        }

        SysUser user1 = getByUserName(sysUser.getUserName());
        if (user1 != null && !sysUser.getId().equals(user1.getId())) {
            throw new EntityExistException(SysUser.class, "userName", sysUser.getUserName());
        }

        user.setUserName(sysUser.getUserName());
        user.setEmail(sysUser.getEmail());
        user.setEnabled(sysUser.getEnabled());
        user.setRoles(sysUser.getRoles());
        user.setDept(sysUser.getDept());
        user.setJobs(sysUser.getJobs());
        user.setPhone(sysUser.getPhone());
        user.setNickName(sysUser.getNickName());
        user.setGender(sysUser.getGender());

        sysUsersRolesService.update(sysUser.getId(), sysUser.getRoles());
        iSysUsersJobsService.update(sysUser.getId(), sysUser.getJobs());
        return sysUserDao.updateById(user) == 1;
    }


    @Override
    public void checkLevel(SysUser resources, String currentUserId) {

        Integer currentLevel = this.getHighestLevel(currentUserId);

        Set<String> roles = new HashSet<>();
        resources.getRoles().forEach(x -> roles.add(x.getId()));

        Integer optLevel = sysRoleService.getHighestLevel(roles);
        if (currentLevel > optLevel) {
            throw new BadRequestException("角色权限不足");
        }
    }

    @Override
    public Integer getHighestLevel(String currentUserId) {
        return sysUserDao.getHighestLevel(currentUserId);
    }
}
