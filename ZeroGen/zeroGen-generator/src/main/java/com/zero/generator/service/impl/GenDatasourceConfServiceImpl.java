package com.zero.generator.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.zero.common.core.text.Convert;
import com.zero.generator.domain.GenDatasourceConf;
import com.zero.generator.mapper.GenDatasourceConfMapper;
import com.zero.generator.service.IGenDatasourceConfService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据源Service业务层处理
 *
 * @author auto
 * @date 2021-07-21
 */
@Service
public class GenDatasourceConfServiceImpl implements IGenDatasourceConfService {

    @Autowired
    private GenDatasourceConfMapper genDatasourceConfMapper;

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    /**
     * 查询数据源
     *
     * @param id 数据源ID
     * @return 数据源
     */
    @Override
    public GenDatasourceConf selectGenDatasourceConfById(Long id) {
        return genDatasourceConfMapper.selectGenDatasourceConfById(id);
    }

    /**
     * 查询数据源列表
     *
     * @param genDatasourceConf 数据源
     * @return 数据源
     */
    @Override
    public List<GenDatasourceConf> selectGenDatasourceConfList(GenDatasourceConf genDatasourceConf) {
        return genDatasourceConfMapper.selectGenDatasourceConfList(genDatasourceConf);
    }

    /**
     * 新增数据源
     *
     * @param genDatasourceConf 数据源
     * @return 结果
     */
    @Override
    public int insertGenDatasourceConf(GenDatasourceConf genDatasourceConf) throws Exception {

        genDatasourceConf.setPassword(stringEncryptor.encrypt(genDatasourceConf.getPassword()));
        System.out.println(stringEncryptor.decrypt(genDatasourceConf.getPassword()));
        genDatasourceConfMapper.insertGenDatasourceConf(genDatasourceConf);
        dynamicRoutingDataSource.afterPropertiesSet();
        return 1;
    }

    /**
     * 修改数据源
     *
     * @param genDatasourceConf 数据源
     * @return 结果
     */
    @Override
    public int updateGenDatasourceConf(GenDatasourceConf genDatasourceConf) throws Exception {
        genDatasourceConf.setPassword(stringEncryptor.encrypt(genDatasourceConf.getPassword()));
        System.out.println(stringEncryptor.decrypt(genDatasourceConf.getPassword()));

        genDatasourceConfMapper.updateGenDatasourceConf(genDatasourceConf);
        dynamicRoutingDataSource.afterPropertiesSet();
        return 1;

    }

    /**
     * 删除数据源对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenDatasourceConfByIds(String ids) {
        return genDatasourceConfMapper.deleteGenDatasourceConfByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除数据源信息
     *
     * @param id 数据源ID
     * @return 结果
     */
    @Override
    public int deleteGenDatasourceConfById(Long id) {
        return genDatasourceConfMapper.deleteGenDatasourceConfById(id);
    }
}
