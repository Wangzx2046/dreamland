package com.zero.dreamland.biz.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemRoleDao;
import com.zero.dreamland.biz.system.entity.SystemRole;
import com.zero.dreamland.biz.system.service.ISystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description ：系统角色表
 * Author wzx
 * Date  2019-07-31
 */
@Slf4j
@Service("SystemRoleService")
@Transactional
public class SystemRoleServiceImpl  extends ServiceImpl<SystemRoleDao, SystemRole> implements ISystemRoleService {

    @Resource
    SystemRoleDao systemRoleDao;

    @Override
    public List<SystemRole> findByUsersId(String id) {

        return systemRoleDao.findByUsersId(id);
    }


}