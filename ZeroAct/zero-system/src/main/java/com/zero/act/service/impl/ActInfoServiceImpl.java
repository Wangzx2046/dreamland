package com.zero.act.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.act.domain.ActInfo;
import com.zero.act.mapper.ActInfoMapper;
import com.zero.act.service.IActInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动信息Service业务层处理
 * 
 * @author wzx
 * @date 2021-08-27
 */
@Service
public class ActInfoServiceImpl extends ServiceImpl<ActInfoMapper, ActInfo> implements IActInfoService {
    @Autowired
    private ActInfoMapper actInfoMapper;


}
