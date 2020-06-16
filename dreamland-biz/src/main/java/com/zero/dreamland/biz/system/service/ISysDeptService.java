package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-11
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 获取
     * @param deptId
     * @param deptList
     * @return
     */
    List<String> getDeptChildren(String deptId, List<SysDept> deptList);


}
