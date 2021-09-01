package com.zero.act.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.act.domain.ActInfo;
import com.zero.act.domain.ActRemind;
import com.zero.act.mapper.ActInfoMapper;
import com.zero.act.service.IActInfoService;
import com.zero.act.service.IActRemindService;
import com.zero.common.enums.ActFrequencyType;
import com.zero.common.utils.DateUtilsV8;
import com.zero.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动信息Service业务层处理
 *
 * @author wzx
 * @date 2021-08-27
 */
@Service
@Transactional
public class ActInfoServiceImpl extends ServiceImpl<ActInfoMapper, ActInfo> implements IActInfoService {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IActRemindService actRemindService;

    @Override
    public boolean save(ActInfo actInfo) {
        //1.保存活动信息
        this.baseMapper.insert(actInfo);

        //获取需要提醒的用户列表
        Long[] userIds = new Long[]{100l, 101l};

        List<ActRemind> arList = new ArrayList<>();
        for (Long userid : userIds) {
            //获取活动需要签到的日期
            if (ActFrequencyType.WEEKLY.getCode().equals(String.valueOf(actInfo.getActFrequency()))) {
                //按周，值获取每周一，结束日期直接+6
                List<LocalDate> times = DateUtilsV8.getFrequencyDateList(DayOfWeek.MONDAY.getValue(), actInfo.getActStartTime(), actInfo.getActEndTime());
                times.stream().sorted().forEach(x -> {
                    for (int i = 1; i < actInfo.getCycleTimes() + 1; i++) {
                        ActRemind actRemind = new ActRemind();
                        actRemind.setActId(actInfo.getActId());
                        actRemind.setActName(actInfo.getActName() + "第" + i + "次打卡");
                        actRemind.setActLink(actInfo.getActLink());
                        actRemind.setRemindStartTime(x.atTime(0, 0, 0));
                        actRemind.setRemindEndTime(x.plusDays(6).atTime(23, 59, 59));
                        actRemind.setActTime(actInfo.getRemindTime());
                        actRemind.setUserId(userid);
                        arList.add(actRemind);
                    }
                });

            } else if (ActFrequencyType.MONTHLY.getCode().equals(String.valueOf(actInfo.getActFrequency()))) {
                //按周，值获取每月1号，结束日期直接+6
                List<LocalDate> times = DateUtilsV8.getFirstDateList(actInfo.getActFrequency(), actInfo.getActStartTime(), actInfo.getActEndTime());
                times.stream().sorted().forEach(x -> {
                    for (int i = 1; i < actInfo.getCycleTimes() + 1; i++) {
                        ActRemind actRemind = new ActRemind();
                        actRemind.setActId(actInfo.getActId());
                        actRemind.setActName(actInfo.getActName() + "第" + i + "次打卡");
                        actRemind.setActLink(actInfo.getActLink());
                        actRemind.setRemindStartTime(x.atTime(0, 0, 0));
                        actRemind.setRemindEndTime(x.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59));
                        actRemind.setActTime(actInfo.getRemindTime());
                        actRemind.setUserId(userid);
                        arList.add(actRemind);
                    }
                });

            } else if (ActFrequencyType.QUARTERLY.getCode().equals(String.valueOf(actInfo.getActFrequency()))) {
                //按周，值获取每月季度号，
                List<LocalDate> times = DateUtilsV8.getFirstDateList(actInfo.getActFrequency(), actInfo.getActStartTime(), actInfo.getActEndTime());
                times.stream().sorted().forEach(x -> {
                    for (int i = 1; i < actInfo.getCycleTimes() + 1; i++) {
                        ActRemind actRemind = new ActRemind();
                        actRemind.setActId(actInfo.getActId());
                        actRemind.setActName(actInfo.getActName() + "第" + i + "次打卡");
                        actRemind.setActLink(actInfo.getActLink());
                        actRemind.setRemindStartTime(x.atTime(0, 0, 0));
                        actRemind.setRemindEndTime(x.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59));
                        actRemind.setActTime(actInfo.getRemindTime());
                        actRemind.setUserId(userid);
                        arList.add(actRemind);
                    }
                });

            } else if (ActFrequencyType.EVERY_YEAR.getCode().equals(String.valueOf(actInfo.getActFrequency()))) {
                //按周，值获取每月年，
                List<LocalDate> times = DateUtilsV8.getFirstDateList(actInfo.getActFrequency(), actInfo.getActStartTime(), actInfo.getActEndTime());
                times.stream().sorted().forEach(x -> {
                    for (int i = 1; i < actInfo.getCycleTimes() + 1; i++) {
                        ActRemind actRemind = new ActRemind();
                        actRemind.setActId(actInfo.getActId());
                        actRemind.setActName(actInfo.getActName() + "第" + i + "次打卡");
                        actRemind.setActLink(actInfo.getActLink());
                        actRemind.setRemindStartTime(x.atTime(0, 0, 0));
                        actRemind.setRemindEndTime(x.with(TemporalAdjusters.lastDayOfYear()).atTime(23, 59, 59));
                        actRemind.setActTime(actInfo.getRemindTime());
                        actRemind.setUserId(userid);
                        arList.add(actRemind);
                    }
                });
            } else {
                List<LocalDate> times = DateUtilsV8.getFrequencyDateList(actInfo.getActFrequency(), actInfo.getActStartTime(), actInfo.getActEndTime());
                times.stream().sorted().forEach(x -> {
                    ActRemind actRemind = new ActRemind();
                    actRemind.setActId(actInfo.getActId());
                    actRemind.setActName(actInfo.getActName());
                    actRemind.setActLink(actInfo.getActLink());
                    actRemind.setRemindStartTime(x.atTime(0, 0, 0));
                    actRemind.setRemindEndTime(x.atTime(23, 59, 59));
                    actRemind.setActTime(actInfo.getRemindTime());
                    actRemind.setUserId(userid);
                    arList.add(actRemind);
                });
            }
        }

        return actRemindService.saveBatch(arList);
    }
}
