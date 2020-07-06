/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zero.dreamland.quartz.rest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.quartz.entity.SysQuartzJob;
import com.zero.dreamland.quartz.service.ISysQuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/sys-jobs")
@Api(tags = "系统:定时任务管理")
public class SysQuartzJobController {

    @Autowired
    private ISysQuartzJobService quartzJobService;

    //@Log("查询定时任务")
    @ApiOperation("查询定时任务")
    @GetMapping
    //  @PreAuthorize("@el.check('timing:list')")
    public ResponseEntity<Object> query(SysQuartzJob criteria) throws Exception {

        PageHelper.startPage(1, 99999);
        List<SysQuartzJob> list = quartzJobService.list(criteria);
        PageInfo pageInfo = new PageInfo<>(list);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "定时任务-新增", notes = "新增一条定时任务的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody SysQuartzJob sysQuartzJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        quartzJobService.save(sysQuartzJob);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ApiOperation(value = "定时任务-编辑", notes = "编辑一条定时任务的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody SysQuartzJob sysQuartzJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        quartzJobService.updateById(sysQuartzJob);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@Log("删除定时任务")
    @ApiOperation("删除定时任务")
    @DeleteMapping
    // @PreAuthorize("@el.check('timing:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<String> ids) {
        quartzJobService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@Log("更改定时任务状态")
    @ApiOperation("更改定时任务状态")
    @PutMapping(value = "/{id}")
    // @PreAuthorize("@el.check('timing:edit')")
    public ResponseEntity<Object> update(@PathVariable String id) {
        quartzJobService.updateIsPause(quartzJobService.getById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Log("执行定时任务")
    @ApiOperation("执行定时任务")
    @PutMapping(value = "/exec/{id}")
    // @PreAuthorize("@el.check('timing:edit')")
    public ResponseEntity<Object> execution(@PathVariable String id) {
        quartzJobService.execution(quartzJobService.getById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

/*    //@Log("导出任务数据")
    @ApiOperation("导出任务数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('timing:list')")
    public void download(HttpServletResponse response, SysQuartzJob criteria) throws IOException {
        quartzJobService.download(quartzJobService.queryAll(criteria), response);
    }

    //@Log("导出日志数据")
    @ApiOperation("导出日志数据")
    @GetMapping(value = "/logs/download")
    @PreAuthorize("@el.check('timing:list')")
    public void downloadLog(HttpServletResponse response, SysQuartzJob criteria) throws IOException {
        quartzJobService.downloadLog(quartzJobService.queryAllLog(criteria), response);
    }

    @ApiOperation("查询任务执行日志")
    @GetMapping(value = "/logs")
    @PreAuthorize("@el.check('timing:list')")
    public ResponseEntity<Object> queryJobLog(SysQuartzJob criteria, Pageable pageable) {
        return new ResponseEntity<>(quartzJobService.queryAllLog(criteria, pageable), HttpStatus.OK);
    }



  */


}
