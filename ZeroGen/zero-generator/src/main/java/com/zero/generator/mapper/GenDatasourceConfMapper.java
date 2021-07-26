package com.zero.generator.mapper;

import java.util.List;
import com.zero.generator.domain.GenDatasourceConf;

/**
 * 数据源Mapper接口
 * 
 * @author auto
 * @date 2021-07-21
 */
public interface GenDatasourceConfMapper 
{
    /**
     * 查询数据源
     * 
     * @param id 数据源ID
     * @return 数据源
     */
    public GenDatasourceConf selectGenDatasourceConfById(Long id);

    /**
     * 查询数据源列表
     * 
     * @param genDatasourceConf 数据源
     * @return 数据源集合
     */
    public List<GenDatasourceConf> selectGenDatasourceConfList(GenDatasourceConf genDatasourceConf);

    /**
     * 新增数据源
     * 
     * @param genDatasourceConf 数据源
     * @return 结果
     */
    public int insertGenDatasourceConf(GenDatasourceConf genDatasourceConf);

    /**
     * 修改数据源
     * 
     * @param genDatasourceConf 数据源
     * @return 结果
     */
    public int updateGenDatasourceConf(GenDatasourceConf genDatasourceConf);

    /**
     * 删除数据源
     * 
     * @param id 数据源ID
     * @return 结果
     */
    public int deleteGenDatasourceConfById(Long id);

    /**
     * 批量删除数据源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenDatasourceConfByIds(String[] ids);
}
