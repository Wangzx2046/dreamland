package com.zero.business.service.impl;

import java.util.List;
import com.zero.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zero.business.mapper.CompanyMapper;
import com.zero.business.domain.Company;
import com.zero.business.service.ICompanyService;
import com.zero.common.core.text.Convert;

/**
 * 公司信息Service业务层处理
 * 
 * @author zero
 * @date 2021-03-03
 */
@Service
public class CompanyServiceImpl implements ICompanyService 
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 查询公司信息
     * 
     * @param companyId 公司信息ID
     * @return 公司信息
     */
    @Override
    public Company selectCompanyById(Long companyId)
    {
        return companyMapper.selectCompanyById(companyId);
    }

    /**
     * 查询公司信息列表
     * 
     * @param company 公司信息
     * @return 公司信息
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增公司信息
     * 
     * @param company 公司信息
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        company.setCreateTime(DateUtils.getNowDate());
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改公司信息
     * 
     * @param company 公司信息
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        company.setUpdateTime(DateUtils.getNowDate());
        return companyMapper.updateCompany(company);
    }

    /**
     * 删除公司信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCompanyByIds(String ids)
    {
        return companyMapper.deleteCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公司信息信息
     * 
     * @param companyId 公司信息ID
     * @return 结果
     */
    @Override
    public int deleteCompanyById(Long companyId)
    {
        return companyMapper.deleteCompanyById(companyId);
    }
}
