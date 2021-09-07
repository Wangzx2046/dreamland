package com.zero.generator.mapper;

import java.util.List;
import com.zero.generator.domain.GenTemplate;

/**
 * 代码生成模版Mapper接口
 * 
 * @author auto
 * @date 2021-07-23
 */
public interface GenTemplateMapper 
{
    /**
     * 查询代码生成模版
     * 
     * @param id 代码生成模版ID
     * @return 代码生成模版
     */
    public GenTemplate selectGenTemplateById(Long id);

    /**
     * 查询代码生成模版列表
     * 
     * @param genTemplate 代码生成模版
     * @return 代码生成模版集合
     */
    public List<GenTemplate> selectGenTemplateList(GenTemplate genTemplate);

    /**
     * 新增代码生成模版
     * 
     * @param genTemplate 代码生成模版
     * @return 结果
     */
    public int insertGenTemplate(GenTemplate genTemplate);

    /**
     * 修改代码生成模版
     * 
     * @param genTemplate 代码生成模版
     * @return 结果
     */
    public int updateGenTemplate(GenTemplate genTemplate);

    /**
     * 删除代码生成模版
     * 
     * @param id 代码生成模版ID
     * @return 结果
     */
    public int deleteGenTemplateById(Long id);

    /**
     * 批量删除代码生成模版
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTemplateByIds(String[] ids);
}
