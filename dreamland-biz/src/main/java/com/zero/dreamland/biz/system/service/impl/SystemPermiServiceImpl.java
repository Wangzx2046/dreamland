package com.zero.dreamland.biz.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SystemPermiDao;
import com.zero.dreamland.biz.system.entity.SystemPermi;
import com.zero.dreamland.biz.system.service.ISystemPermiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description ：系统权限表
 * Author wzx
 * Date  2019-07-31
 */
@Slf4j
@Service("SystemPermiService")
@Transactional
public class SystemPermiServiceImpl  extends ServiceImpl<SystemPermiDao, SystemPermi> implements ISystemPermiService {
    @Resource
    private  SystemPermiDao systemPermiDao;

    public boolean save(SystemPermi systemPermi){
        boolean flag=false;

        systemPermiDao.insert(systemPermi);
        return  flag;
    }


    public boolean update(SystemPermi systemPermi){
        boolean flag=false;

        //if()
        systemPermiDao.updateById(systemPermi);
        return  flag;
    }

}
