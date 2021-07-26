package com.zero.generator.controller;

import java.util.List;
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
import com.zero.generator.domain.GenTemplate;
import com.zero.generator.service.IGenTemplateService;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.utils.poi.ExcelUtil;
import com.zero.common.core.page.TableDataInfo;

/**
 * 代码生成模版Controller
 * 
 * @author auto
 * @date 2021-07-23
 */
@Controller
@RequestMapping("/business/template")
public class GenTemplateController extends BaseController
{
    private String prefix = "business/template";

    @Autowired
    private IGenTemplateService genTemplateService;

    @RequiresPermissions("business:template:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/template";
    }

    /**
     * 查询代码生成模版列表
     */
    @RequiresPermissions("business:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GenTemplate genTemplate)
    {
        startPage();
        List<GenTemplate> list = genTemplateService.selectGenTemplateList(genTemplate);
        return getDataTable(list);
    }

    /**
     * 导出代码生成模版列表
     */
    @RequiresPermissions("business:template:export")
    @Log(title = "代码生成模版", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GenTemplate genTemplate)
    {
        List<GenTemplate> list = genTemplateService.selectGenTemplateList(genTemplate);
        ExcelUtil<GenTemplate> util = new ExcelUtil<GenTemplate>(GenTemplate.class);
        return util.exportExcel(list, "代码生成模版数据");
    }

    /**
     * 新增代码生成模版
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代码生成模版
     */
    @RequiresPermissions("business:template:add")
    @Log(title = "代码生成模版", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GenTemplate genTemplate)
    {
        return toAjax(genTemplateService.insertGenTemplate(genTemplate));
    }

    /**
     * 修改代码生成模版
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GenTemplate genTemplate = genTemplateService.selectGenTemplateById(id);
        mmap.put("genTemplate", genTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成模版
     */
    @RequiresPermissions("business:template:edit")
    @Log(title = "代码生成模版", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GenTemplate genTemplate)
    {
        return toAjax(genTemplateService.updateGenTemplate(genTemplate));
    }

    /**
     * 删除代码生成模版
     */
    @RequiresPermissions("business:template:remove")
    @Log(title = "代码生成模版", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(genTemplateService.deleteGenTemplateByIds(ids));
    }
}
