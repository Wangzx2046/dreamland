package com.zero.generator.service.impl;

import java.util.List;
import com.zero.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zero.generator.mapper.GenTemplateMapper;
import com.zero.generator.domain.GenTemplate;
import com.zero.generator.service.IGenTemplateService;
import com.zero.common.core.text.Convert;

/**
 * 代码生成模版Service业务层处理
 * 
 * @author auto
 * @date 2021-07-23
 */
@Service
public class GenTemplateServiceImpl implements IGenTemplateService 
{
    @Autowired
    private GenTemplateMapper genTemplateMapper;

    /**
     * 查询代码生成模版
     * 
     * @param id 代码生成模版ID
     * @return 代码生成模版
     */
    @Override
    public GenTemplate selectGenTemplateById(Long id)
    {
        return genTemplateMapper.selectGenTemplateById(id);
    }

    /**
     * 查询代码生成模版列表
     * 
     * @param genTemplate 代码生成模版
     * @return 代码生成模版
     */
    @Override
    public List<GenTemplate> selectGenTemplateList(GenTemplate genTemplate)
    {
        return genTemplateMapper.selectGenTemplateList(genTemplate);
    }

    /**
     * 新增代码生成模版
     * 
     * @param genTemplate 代码生成模版
     * @return 结果
     */
    @Override
    public int insertGenTemplate(GenTemplate genTemplate)
    {
        genTemplate.setCreateTime(DateUtils.getNowDate());
        return genTemplateMapper.insertGenTemplate(genTemplate);
    }

    /**
     * 修改代码生成模版
     * 
     * @param genTemplate 代码生成模版
     * @return 结果
     */
    @Override
    public int updateGenTemplate(GenTemplate genTemplate)
    {
        genTemplate.setUpdateTime(DateUtils.getNowDate());
        return genTemplateMapper.updateGenTemplate(genTemplate);
    }

    /**
     * 删除代码生成模版对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTemplateByIds(String ids)
    {
        return genTemplateMapper.deleteGenTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代码生成模版信息
     * 
     * @param id 代码生成模版ID
     * @return 结果
     */
    @Override
    public int deleteGenTemplateById(Long id)
    {
        return genTemplateMapper.deleteGenTemplateById(id);
    }
}
