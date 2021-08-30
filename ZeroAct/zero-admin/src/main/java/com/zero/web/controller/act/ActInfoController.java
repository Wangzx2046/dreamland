package com.zero.web.controller.act;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.act.domain.ActInfo;
import com.zero.act.service.IActInfoService;
import com.zero.common.annotation.Log;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.core.page.TableDataInfo;
import com.zero.common.enums.BusinessType;
import com.zero.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 活动信息Controller
 *
 * @author wzx
 * @date 2021-08-27
 */
@RestController
@RequestMapping("/act/info")
public class ActInfoController extends BaseController {
    @Autowired
    private IActInfoService actInfoService;

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('act:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActInfo actInfo) {
        startPage();
        List<ActInfo> list = actInfoService.list(new QueryWrapper<>(actInfo).orderByDesc("create_time"));
        return getDataTable(list);
    }

    /**
     * 导入活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('act:info:upload')")
    @Log(title = "活动信息", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult importData(@RequestParam MultipartFile file, @RequestParam boolean updateSupport) throws Exception {
        ExcelUtil<ActInfo> util = new ExcelUtil<ActInfo>(ActInfo.class);
        List<ActInfo> list = util.importExcel(file.getInputStream());

        //todo批量插入逻辑
        actInfoService.saveBatch(list);


        return AjaxResult.success("导入成功");
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('act:info:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActInfo actInfo) throws IOException {
        List<ActInfo> list = actInfoService.list(new QueryWrapper<>(actInfo).orderByDesc("create_time"));
        ExcelUtil<ActInfo> util = new ExcelUtil<ActInfo>(ActInfo.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('act:info:query')")
    @GetMapping(value = "/{actId}")
    public AjaxResult getInfo(@PathVariable("actId") Long actId) {
        return AjaxResult.success(actInfoService.getById(actId));
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ss.hasPermi('act:info:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActInfo actInfo) {
        return toAjax(actInfoService.save(actInfo));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('act:info:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActInfo actInfo) {
        return toAjax(actInfoService.updateById(actInfo));
    }

    /**
     * 删除活动信息
     */
    @PreAuthorize("@ss.hasPermi('act:info:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{actIds}")
    public AjaxResult remove(@PathVariable Long[] actIds) {
        return toAjax(actInfoService.removeByIds(Arrays.asList(actIds)));
    }
}