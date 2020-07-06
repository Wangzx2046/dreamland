package com.zero.dreamland.quartz.rest;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.quartz.entity.SysQuartzLog;
import com.zero.dreamland.quartz.service.ISysQuartzLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Wang.zx
 * @Description ：定时任务日志
 * @since : 2020-07-02
 */
@Api(tags = "定时任务日志")
@Slf4j
@RestController
@RequestMapping("/sys/sys-joblogs")
public class SysQuartzLogController {

    @Resource
    private ISysQuartzLogService sysQuartzLogService;


    @ApiOperation(value = "定时任务日志-查看", notes = "列表查看定时任务日志的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> all(SysQuartzLog sysQuartzLog, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysQuartzLog> list = sysQuartzLogService.list(sysQuartzLog);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }


}
