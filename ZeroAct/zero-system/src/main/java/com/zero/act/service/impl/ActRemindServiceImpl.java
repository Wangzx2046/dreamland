package com.zero.act.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.act.domain.ActRemind;
import com.zero.act.mapper.ActRemindMapper;
import com.zero.act.service.IActRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动打卡记录Service业务层处理
 * 
 * @author wzx
 * @date 2021-08-27
 */
@Service
public class ActRemindServiceImpl extends ServiceImpl<ActRemindMapper, ActRemind> implements IActRemindService {
    @Autowired
    private ActRemindMapper actRemindMapper;


}
