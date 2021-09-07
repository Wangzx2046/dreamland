package com.zero.web.controller.act;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.act.domain.ActRemind;
import com.zero.act.service.IActRemindService;
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
 * 活动打卡记录Controller
 *
 * @author wzx
 * @date 2021-08-27
 */
@RestController
@RequestMapping("/act/remind")
public class ActRemindController extends BaseController {
    @Autowired
    private IActRemindService actRemindService;

    /**
     * 查询活动打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('act:remind:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActRemind actRemind) {
        startPage();
        List<ActRemind> list = actRemindService.list(new QueryWrapper<>(actRemind).orderByDesc("create_time"));
        return getDataTable(list);
    }

    /**
     * 导入活动打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('act:remind:upload')")
    @Log(title = "活动打卡记录", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult importData(@RequestParam MultipartFile file, @RequestParam boolean updateSupport) throws Exception {
        ExcelUtil<ActRemind> util = new ExcelUtil<ActRemind>(ActRemind.class);
        List<ActRemind> list = util.importExcel(file.getInputStream());

        //todo批量插入逻辑
        actRemindService.saveBatch(list);


        return AjaxResult.success("导入成功");
    }

    /**
     * 导出活动打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('act:remind:export')")
    @Log(title = "活动打卡记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActRemind actRemind) throws IOException {
        List<ActRemind> list = actRemindService.list(new QueryWrapper<>(actRemind).orderByDesc("create_time"));
        ExcelUtil<ActRemind> util = new ExcelUtil<ActRemind>(ActRemind.class);
        util.exportExcel(response, list, "活动打卡记录数据");
    }

    /**
     * 获取活动打卡记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('act:remind:query')")
    @GetMapping(value = "/{remindId}")
    public AjaxResult getInfo(@PathVariable("remindId") Long remindId) {
        return AjaxResult.success(actRemindService.getById(remindId));
    }

    /**
     * 新增活动打卡记录
     */
    @PreAuthorize("@ss.hasPermi('act:remind:add')")
    @Log(title = "活动打卡记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActRemind actRemind) {
        return toAjax(actRemindService.save(actRemind));
    }

    /**
     * 修改活动打卡记录
     */
    @PreAuthorize("@ss.hasPermi('act:remind:edit')")
    @Log(title = "活动打卡记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActRemind actRemind) {
        return toAjax(actRemindService.updateById(actRemind));
    }

    /**
     * 删除活动打卡记录
     */
    @PreAuthorize("@ss.hasPermi('act:remind:remove')")
    @Log(title = "活动打卡记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{remindIds}")
    public AjaxResult remove(@PathVariable Long[] remindIds) {
        return toAjax(actRemindService.removeByIds(Arrays.asList(remindIds)));
    }
}
