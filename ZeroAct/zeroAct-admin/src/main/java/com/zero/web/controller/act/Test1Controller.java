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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 活动信息Controller
 *
 * @author wzx
 * @date 2021-08-27
 */
@RestController
@RequestMapping("/openapi")
public class Test1Controller extends BaseController {
    @Autowired
    private IActInfoService actInfoService;

    /**
     * 查询活动信息列表
     */
    @PostMapping("/api/XwsqZzs/getSellerInvoice")
    public Object list(Map map) {
        System.out.println(map);
        return null;
    }

}