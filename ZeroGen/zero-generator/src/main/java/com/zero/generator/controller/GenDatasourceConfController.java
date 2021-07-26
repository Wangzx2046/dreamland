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
import com.zero.generator.domain.GenDatasourceConf;
import com.zero.generator.service.IGenDatasourceConfService;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.utils.poi.ExcelUtil;
import com.zero.common.core.page.TableDataInfo;

/**
 * 数据源Controller
 * 
 * @author auto
 * @date 2021-07-21
 */
@Controller
@RequestMapping("/dataSource/conf")
public class GenDatasourceConfController extends BaseController
{
    private String prefix = "dataSource/conf";

    @Autowired
    private IGenDatasourceConfService genDatasourceConfService;

    @RequiresPermissions("dataSource:conf:view")
    @GetMapping()
    public String conf()
    {
        return prefix + "/conf";
    }

    /**
     * 查询数据源列表
     */
    @RequiresPermissions("dataSource:conf:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GenDatasourceConf genDatasourceConf)
    {
        startPage();
        List<GenDatasourceConf> list = genDatasourceConfService.selectGenDatasourceConfList(genDatasourceConf);
        return getDataTable(list);
    }

    @RequiresPermissions("dataSource:conf:list")
    @PostMapping("/all")
    @ResponseBody
    public TableDataInfo all(GenDatasourceConf genDatasourceConf)
    {
        List<GenDatasourceConf> list = genDatasourceConfService.selectGenDatasourceConfList(genDatasourceConf);
        return getDataTable(list);
    }


    /**
     * 导出数据源列表
     */
    @RequiresPermissions("dataSource:conf:export")
    @Log(title = "数据源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GenDatasourceConf genDatasourceConf)
    {
        List<GenDatasourceConf> list = genDatasourceConfService.selectGenDatasourceConfList(genDatasourceConf);
        ExcelUtil<GenDatasourceConf> util = new ExcelUtil<GenDatasourceConf>(GenDatasourceConf.class);
        return util.exportExcel(list, "数据源数据");
    }

    /**
     * 新增数据源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存数据源
     */
    @RequiresPermissions("dataSource:conf:add")
    @Log(title = "数据源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GenDatasourceConf genDatasourceConf)
    {
        return toAjax(genDatasourceConfService.insertGenDatasourceConf(genDatasourceConf));
    }

    /**
     * 修改数据源
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GenDatasourceConf genDatasourceConf = genDatasourceConfService.selectGenDatasourceConfById(id);
        mmap.put("genDatasourceConf", genDatasourceConf);
        return prefix + "/edit";
    }

    /**
     * 修改保存数据源
     */
    @RequiresPermissions("dataSource:conf:edit")
    @Log(title = "数据源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GenDatasourceConf genDatasourceConf)
    {
        return toAjax(genDatasourceConfService.updateGenDatasourceConf(genDatasourceConf));
    }

    /**
     * 删除数据源
     */
    @RequiresPermissions("dataSource:conf:remove")
    @Log(title = "数据源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(genDatasourceConfService.deleteGenDatasourceConfByIds(ids));
    }
}
