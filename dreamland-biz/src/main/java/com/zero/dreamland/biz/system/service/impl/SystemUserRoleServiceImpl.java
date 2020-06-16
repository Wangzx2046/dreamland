package com.zero.dreamland.biz.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemUserRoleDao;
import com.zero.dreamland.biz.system.entity.SystemUserRole;
import com.zero.dreamland.biz.system.service.SystemUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description ：系统用户角色关系表
 * Author wzx
 * Date  2019-07-31
 */
@Slf4j
@Service("SystemUserRoleService")
@Transactional
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleDao, SystemUserRole> implements SystemUserRoleService {


}
