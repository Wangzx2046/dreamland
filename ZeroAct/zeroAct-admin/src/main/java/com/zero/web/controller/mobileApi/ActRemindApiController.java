package com.zero.web.controller.mobileApi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.act.domain.ActRemind;
import com.zero.act.service.IActRemindService;
import com.zero.common.annotation.Log;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.core.page.TableDataInfo;
import com.zero.common.enums.BusinessType;
import com.zero.common.enums.RoleType;
import com.zero.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
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
@RequestMapping("/api/act/remind")
public class ActRemindApiController extends BaseController {
    @Autowired
    private IActRemindService actRemindService;

    /**
     * 查询活动打卡记录列表
     */
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/list")
    public TableDataInfo list(ActRemind actRemind) {

        startPage();
        List<ActRemind> list = actRemindService.listByUserId(getUserId(),actRemind.getSignFlag());
        return getDataTable(list);
    }

    /**
     * 查询活动打卡记录列表
     */
    @PreAuthorize("@ss.hasRole('common')")
    @PutMapping("/sign/{id}")
    public AjaxResult sign(@PathVariable("id") long id) {


        actRemindService.sign(getUserId(), id);
        return AjaxResult.success();
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
