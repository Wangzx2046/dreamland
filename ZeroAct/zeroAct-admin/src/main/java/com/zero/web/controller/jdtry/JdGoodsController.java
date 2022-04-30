package com.zero.web.controller.jdtry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.common.core.controller.BaseController;
import com.zero.common.core.domain.AjaxResult;
import com.zero.common.core.page.TableDataInfo;
import com.zero.common.utils.poi.ExcelUtil;
import com.zero.jdTry.domain.JdGoods;
import com.zero.jdTry.service.IJdGoodsService;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 试用商品Controller
 *
 * @author zero
 * @date 2022-04-29
 */
@Slf4j
@RestController
@RequestMapping("/system/jdGoods")
public class JdGoodsController extends BaseController {
    @Autowired
    private IJdGoodsService jdGoodsService;

    /**
     * 查询试用商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(JdGoods jdGoods) {
        startPage();
        List<JdGoods> list = jdGoodsService.list(new QueryWrapper<>(jdGoods).orderByDesc("create_time"));
        return getDataTable(list);
    }

    /**
     * 导入试用商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:upload')")
    @PostMapping("/upload")
    public AjaxResult importData(@RequestParam MultipartFile file, @RequestParam boolean updateSupport) throws Exception {
        ExcelUtil<JdGoods> util = new ExcelUtil<JdGoods>(JdGoods. class);
        List<JdGoods> list = util.importExcel(file.getInputStream());

        //todo批量插入逻辑
        jdGoodsService.saveBatch(list);


        return AjaxResult.success("导入成功");
    }

    /**
     * 导出试用商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, JdGoods jdGoods) throws IOException {
        List<JdGoods> list = jdGoodsService.list(new QueryWrapper<>(jdGoods).orderByDesc("create_time"));
        ExcelUtil<JdGoods> util = new ExcelUtil<JdGoods>(JdGoods. class);
        util.exportExcel(response, list, "试用商品数据");
    }

    /**
     * 获取试用商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(jdGoodsService.getById(id));
    }

    /**
     * 新增试用商品
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:add')")
    @PostMapping
    public AjaxResult add(@RequestBody JdGoods jdGoods) {
        return toAjax(jdGoodsService.save(jdGoods));
    }

    /**
     * 修改试用商品
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody JdGoods jdGoods) {
        return toAjax(jdGoodsService.updateById(jdGoods));
    }

    /**
     * 删除试用商品
     */
    @PreAuthorize("@ss.hasPermi('system:jdGoods:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(jdGoodsService.removeByIds(Arrays.asList(ids)));
    }
}