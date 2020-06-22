package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysDept;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
     *
     * @param deptId
     * @param deptList
     * @return
     */
    List<String> getDeptChildren(String deptId, List<SysDept> deptList);

    List<SysDept> list(SysDept sysDept);

    boolean save(SysDept sysDept);

    List<SysDept> getSuperior(SysDept deptDto, List<SysDept> depts);

    Object buildTree(List<SysDept> sysDepts);

    List<SysDept> queryAll(SysDept sysDept, boolean isQuery) throws Exception;

    ;

    boolean updateById(SysDept sysDept);

    /**
     * 验证是否被角色或用户关联
     *
     * @param deptDtos /
     */
    void verification(Set<SysDept> deptDtos);

    void download(SysDept sysDept, HttpServletResponse response) throws IOException;
}
