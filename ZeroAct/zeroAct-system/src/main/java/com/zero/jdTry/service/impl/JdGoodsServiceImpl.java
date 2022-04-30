package com.zero.jdTry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.jdTry.domain.JdGoods;
import com.zero.jdTry.mapper.JdGoodsMapper;
import com.zero.jdTry.service.IJdGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试用商品Service业务层处理
 *
 * @author wzx
 * @date 2022-04-29
 */
@Service
@Transactional
public class JdGoodsServiceImpl extends ServiceImpl<JdGoodsMapper, JdGoods> implements IJdGoodsService {

}