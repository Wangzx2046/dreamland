package com.zero.dreamland.api.tool;


import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.tool.entity.ToolEmailConfig;
import com.zero.dreamland.biz.tool.service.IToolEmailConfigService;
import com.zero.dreamland.common.email.domain.EmailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : Wang.zx
 * @Description ：邮箱配置
 * @since : 2020-07-03
 */
@Api(tags = "邮箱配置")
@Slf4j
@RestController
@RequestMapping("/tool/email-config")
public class ToolEmailConfigController extends BaseController {

    @Resource
    private IToolEmailConfigService toolEmailConfigService;


/*    @ApiOperation(value = "邮箱配置-查看", notes = "列表查看邮箱配置的记录")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> all(ToolEmailConfig toolEmailConfig, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<ToolEmailConfig> list = toolEmailConfigService.list(toolEmailConfig);
        PageInfo pageInfo = new PageInfo<>(list);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }*/


/*    @ApiOperation(value = "邮箱配置-新增", notes = "新增一条邮箱配置的记录")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody ToolEmailConfig toolEmailConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        toolEmailConfigService.save(toolEmailConfig);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "邮箱配置-编辑", notes = "编辑一条邮箱配置的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody ToolEmailConfig toolEmailConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
        }
        toolEmailConfigService.updateById(toolEmailConfig);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


    @ApiOperation(value = "邮箱配置-删除", notes = "删除一条邮箱配置的记录")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
        toolEmailConfigService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Object> queryConfig() {
        ToolEmailConfig emailConfig = toolEmailConfigService.getOne(null);
        emailConfig = null != emailConfig ? emailConfig : new ToolEmailConfig();
        return new ResponseEntity<>(emailConfig, HttpStatus.OK);
    }

    //@Log("配置邮件")
    @PutMapping
    @ApiOperation("配置邮件")
    public ResponseEntity<Object> updateConfig(@Validated @RequestBody ToolEmailConfig emailConfig) throws Exception {
        toolEmailConfigService.config(emailConfig, toolEmailConfigService.getOne(null));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @Log("发送邮件")
    @PostMapping
    @ApiOperation("发送邮件")
    public ResponseEntity<Object> sendEmail(@Validated @RequestBody EmailVo emailVo) throws MessagingException {
        toolEmailConfigService.send(emailVo, toolEmailConfigService.getOne(null));

        Map<String,Object> map=new HashMap<>();
        map.put("username","迟书宏");
        toolEmailConfigService.sendTemplateMail("443082604@qq.com","您好",map,"codeEmail");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
