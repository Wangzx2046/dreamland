package com.zero.act.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.zero.act.domain.ActInfo;
import org.apache.poi.ss.formula.functions.T;

/**
 * 活动信息Service接口
 *
 * @author wzx
 * @date 2021-08-27
 */
public interface IActInfoService extends IService<ActInfo> {

    boolean save(ActInfo entity);
}
