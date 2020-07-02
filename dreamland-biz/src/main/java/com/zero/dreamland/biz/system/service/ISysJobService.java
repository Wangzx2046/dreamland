package com.zero.dreamland.biz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.system.entity.SysJob;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-06-19
 */
public interface ISysJobService extends IService<SysJob> {

    List<SysJob> list(SysJob sysJob);

    List<SysJob> findByUsersId(String currentUserId);

    boolean removeByIds(Set<String> ids);

    /**
     * 验证是否被用户关联
     *
     * @param ids /
     */
    void verification(Set<String> ids);

    /**
     * 导出数据
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<SysJob> queryAll, HttpServletResponse response) throws IOException;

}
