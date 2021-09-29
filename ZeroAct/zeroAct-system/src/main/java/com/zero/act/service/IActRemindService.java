package com.zero.act.service;

import java.util.List;

import com.zero.act.domain.ActRemind;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 活动打卡记录Service接口
 * 
 * @author wzx
 * @date 2021-08-27
 */
public interface IActRemindService extends IService<ActRemind>{

    List<ActRemind> listByUserId(Long userId,String signFlag);

    int sign(Long userId, long recordId);
}
