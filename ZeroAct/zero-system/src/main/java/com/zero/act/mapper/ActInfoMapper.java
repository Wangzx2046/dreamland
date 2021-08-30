package com.zero.act.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.act.domain.ActInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * 活动信息Mapper接口
 * 
 * @author wzx
 * @date 2021-08-27
 */
@Mapper
public interface ActInfoMapper extends BaseMapper<ActInfo> {

}
