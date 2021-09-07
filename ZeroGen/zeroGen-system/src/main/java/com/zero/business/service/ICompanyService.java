package com.zero.business.service;

import java.util.List;
import com.zero.business.domain.Company;

/**
 * 公司信息Service接口
 * 
 * @author zero
 * @date 2021-03-03
 */
public interface ICompanyService 
{
    /**
     * 查询公司信息
     * 
     * @param companyId 公司信息ID
     * @return 公司信息
     */
    public Company selectCompanyById(Long companyId);

    /**
     * 查询公司信息列表
     * 
     * @param company 公司信息
     * @return 公司信息集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增公司信息
     * 
     * @param company 公司信息
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改公司信息
     * 
     * @param company 公司信息
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyByIds(String ids);

    /**
     * 删除公司信息信息
     * 
     * @param companyId 公司信息ID
     * @return 结果
     */
    public int deleteCompanyById(Long companyId);
}
