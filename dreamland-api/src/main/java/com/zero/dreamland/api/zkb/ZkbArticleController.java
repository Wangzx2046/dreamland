package com.zero.dreamland.api.zkb;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.dreamland.api.common.core.BaseController;
import com.zero.dreamland.biz.zkb.entity.ZkbArticle;
import com.zero.dreamland.biz.zkb.service.IZkbArticleService;
import com.zero.dreamland.common.MyValidation.AddGroup;
import com.zero.dreamland.common.MyValidation.UpdateGroup;
import com.zero.dreamland.common.exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
*
* @Description ：赚客吧-文章
*
* @author : Wang.zx
* @since : 2021-01-20
*/
@Api(tags = "赚客吧-文章")
@Slf4j
@RestController
@RequestMapping("/system/zkb-article")
  public class ZkbArticleController extends BaseController {

  @Resource
  private IZkbArticleService zkbArticleService;


  @ApiOperation(value = "赚客吧-文章-查看", notes = "列表查看赚客吧-文章的记录")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  public ResponseEntity<Object> all(ZkbArticle zkbArticle, Pageable pageable) {

   PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
   List<ZkbArticle> list =  zkbArticleService.list(zkbArticle);
   PageInfo pageInfo = new PageInfo<>(list);

    return new ResponseEntity<>(pageInfo, HttpStatus.OK);
   }





  @ApiOperation(value = "赚客吧-文章-新增", notes = "新增一条赚客吧-文章的记录")
 // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Object> add(@Validated({AddGroup.class}) @RequestBody  ZkbArticle zkbArticle, BindingResult bindingResult) {
   if (bindingResult.hasErrors()) {
   throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
   }
   zkbArticleService.save(zkbArticle);
   return new ResponseEntity<>(HttpStatus.OK);
   }


  @ApiOperation(value = "赚客吧-文章-编辑", notes = "编辑一条赚客吧-文章的记录")
  //@PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Object> edit(@Validated({UpdateGroup.class}) @RequestBody  ZkbArticle zkbArticle, BindingResult bindingResult) {
   if (bindingResult.hasErrors()) {
    throw new BadRequestException(HttpStatus.BAD_REQUEST, "bad parameter：" + bindingResult.getFieldError().getDefaultMessage());
   }
   zkbArticleService.updateById(zkbArticle);
   return new ResponseEntity<>(HttpStatus.OK);
  }




 @ApiOperation(value = "赚客吧-文章-删除", notes = "删除一条赚客吧-文章的记录")
 //@PreAuthorize("hasRole('ROLE_ADMIN')")
 @DeleteMapping
 public ResponseEntity<Object> delete(@NotBlank(message = "id should not be empty") @RequestBody Set<String> ids) {
   zkbArticleService.removeByIds(ids);
   return new ResponseEntity<>(HttpStatus.OK);
  }





















 }
