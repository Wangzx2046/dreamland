package com.zero.act.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.act.domain.ActRemind;
import com.zero.act.mapper.ActRemindMapper;
import com.zero.act.service.IActRemindService;
import com.zero.common.enums.SignStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

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


    @Override
    public List<ActRemind> listByUserId(Long userId,String signFlag) {
        return this.baseMapper.selectList(Wrappers.<ActRemind>lambdaQuery()
                .eq(ActRemind::getUserId, userId)
                .eq(!StringUtils.isEmpty(signFlag),ActRemind::getSignFlag, signFlag)
                .le(ActRemind::getRemindStartTime, LocalDate.now().atTime(0, 0, 0))
                .ge(ActRemind::getRemindEndTime, LocalDate.now().atTime(23, 59, 59))
                .orderByAsc(ActRemind::getActTime)
        );
    }

    @Override
    public int sign(Long userId, long recordId) {
        LambdaUpdateWrapper<ActRemind> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ActRemind::getSignFlag, SignStatus.YES.getCode())
                .eq(ActRemind::getUserId, userId)
                .eq(ActRemind::getRemindId, recordId);


        return this.baseMapper.update(new ActRemind(), wrapper);
    }
}
