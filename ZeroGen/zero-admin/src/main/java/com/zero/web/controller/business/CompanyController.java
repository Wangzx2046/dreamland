package com.zero.web.controller.business;

import java.util.List;

import com.zero.business.domain.Company;
import com.zero.business.service.ICompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zero.common.annotation.Log;
import com.zero.common.enums.BusinessType;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.utils.poi.ExcelUtil;
import com.zero.common.core.page.TableDataInfo;

/**
 * 公司信息Controller
 * 
 * @author zero
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/business/company")
public class CompanyController extends BaseController
{
    private String prefix = "business/company";

    @Autowired
    private ICompanyService companyService;

    @RequiresPermissions("business:company:view")
    @GetMapping()
    public String company()
    {
        return prefix + "/company";
    }

    /**
     * 查询公司信息列表
     */
    @RequiresPermissions("business:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Company company)
    {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * 导出公司信息列表
     */
    @RequiresPermissions("business:company:export")
    @Log(title = "公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Company company)
    {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 新增公司信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公司信息
     */
    @RequiresPermissions("business:company:add")
    @Log(title = "公司信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改公司信息
     */
    @GetMapping("/edit/{companyId}")
    public String edit(@PathVariable("companyId") Long companyId, ModelMap mmap)
    {
        Company company = companyService.selectCompanyById(companyId);
        mmap.put("company", company);
        return prefix + "/edit";
    }

    /**
     * 修改保存公司信息
     */
    @RequiresPermissions("business:company:edit")
    @Log(title = "公司信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Company company)
    {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除公司信息
     */
    @RequiresPermissions("business:company:remove")
    @Log(title = "公司信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(companyService.deleteCompanyByIds(ids));
    }
}
