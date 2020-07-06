package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.returnMsg.Result;
import com.zero.dreamland.common.returnMsg.ResultUtil;
import com.zero.dreamland.common.exception.BadRequestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Set;


import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
*
* @Description ：${table.comment!}
*
* @author : ${author}
* @since : ${date}
*/
@Api(tags = "${table.comment!}")
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
 @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
 class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
 <#if superControllerClass??>
  public class ${table.controllerName} extends ${superControllerClass} {
 <#else>
  public class ${table.controllerName} {
 </#if>

  @Resource
  private ${table.serviceName} ${table.servicePropertyName};


  @ApiOperation(value = "${table.comment!}-查看", notes = "列表查看${table.comment!}的记录")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  public ResponseEntity<Object> all( ${entity} ${table.entityPropertyName}, Pageable pageable) {

   PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
   List<${entity}> list =  ${table.servicePropertyName}.list(${table.entityPropertyName});
   PageInfo pageInfo = new PageInfo<>(list);

    return new ResponseEntity<>(pageInfo, HttpStatus.OK);
   }





  @ApiOperation(value = "${table.comment!}-新增", notes = "新增一条${table.comment!}的记录")
 // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody  ${entity} ${table.entityPropertyName}, BindingResult bindingResult) {
   if (bindingResult.hasErrors()) {
   throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
   }
   ${table.servicePropertyName}.save(${table.entityPropertyName});
   return new ResponseEntity<>(HttpStatus.OK);
   }


  @ApiOperation(value = "${table.comment!}-编辑", notes = "编辑一条${table.comment!}的记录")
  //@PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody  ${entity} ${table.entityPropertyName}, BindingResult bindingResult) {
   if (bindingResult.hasErrors()) {
    throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
   }
   ${table.servicePropertyName}.updateById(${table.entityPropertyName});
   return new ResponseEntity<>(HttpStatus.OK);
  }




 @ApiOperation(value = "${table.comment!}-删除", notes = "删除一条${table.comment!}的记录")
 //@PreAuthorize("hasRole('ROLE_ADMIN')")
 @DeleteMapping
 public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
   ${table.servicePropertyName}.removeByIds(ids);
   return new ResponseEntity<>(HttpStatus.OK);
  }












<#--   @ApiOperation(value = "${table.comment!}-查看", notes = "列表查看${table.comment!}的记录")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping(value = {"/maps"})
   public Result maps(Model model, HttpServletRequest request,  ${entity} ${table.entityPropertyName},
   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
   IPage<HashMap<String, Object>> pageMap = ${table.servicePropertyName}.page(new Page(pageNum, pageSize), null);
   return ResultUtil.success(pageMap);
   }-->









 }
</#if>
