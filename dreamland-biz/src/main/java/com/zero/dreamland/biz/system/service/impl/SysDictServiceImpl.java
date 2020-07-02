package com.zero.dreamland.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.system.dao.SysDictDao;
import com.zero.dreamland.biz.system.entity.SysDict;
import com.zero.dreamland.biz.system.entity.SysDictDetail;
import com.zero.dreamland.biz.system.service.ISysDictDetailService;
import com.zero.dreamland.biz.system.service.ISysDictService;
import com.zero.dreamland.biz.system.vo.SysDictDetailVo;
import com.zero.dreamland.common.utils.FileUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
@Service

public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements ISysDictService {

    @Resource
    private SysDictDao sysDictDao;
    @Resource
    private ISysDictDetailService iSysDictDetailService;

    @Override
    public List<SysDict> list(SysDict sysDict) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysDict.getBlurry())) {
            queryWrapper.eq("name", sysDict.getBlurry())
                    .or().eq("description", sysDict.getBlurry());
        }
        return sysDictDao.selectList(queryWrapper);
    }

    @Override
    public void download(SysDict sysdict, HttpServletResponse response) throws IOException {
        List<SysDict> dictList = this.list(sysdict);

        List<Map<String, Object>> list = new ArrayList<>();
        for (SysDict dictDTO : dictList) {
            SysDictDetailVo sysDictDetail = new SysDictDetailVo();
            sysDictDetail.setDictId(dictDTO.getId());
            List<SysDictDetail> sysDictDetailList = iSysDictDetailService.list(sysDictDetail);

            if (sysDictDetailList.size() > 0) {
                for (SysDictDetail dictDetail : sysDictDetailList) {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("字典名称", dictDTO.getName());
                    map.put("字典描述", dictDTO.getDescription());
                    map.put("字典标签", dictDetail.getLabel());
                    map.put("字典值", dictDetail.getValue());
                    map.put("创建日期", dictDetail.getCreateDate());
                    list.add(map);
                }
            } else {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("字典名称", dictDTO.getName());
                map.put("字典描述", dictDTO.getDescription());
                map.put("字典标签", null);
                map.put("字典值", null);
                map.put("创建日期", dictDTO.getCreateDate());
                list.add(map);
            }
        }
        FileUtil.downloadExcel(list, response);
    }

}
