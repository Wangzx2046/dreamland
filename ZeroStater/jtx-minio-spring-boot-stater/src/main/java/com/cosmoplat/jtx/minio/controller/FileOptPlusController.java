package com.cosmoplat.jtx.minio.controller;
import com.cosmoplat.jtx.common.exception.BusiException;
import com.cosmoplat.jtx.common.result.Result;
import com.cosmoplat.jtx.minio.util.MinioUtil;
import io.minio.messages.Bucket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kaiye
 */
@Api(tags = "Minio文件操作")
@Slf4j
@RestController
@RequestMapping(value = "/FileOptPlus")
public class FileOptPlusController {

  @Resource
  private MinioUtil minioUtil;

  @ApiOperation(value = "获取全部bucket")
  @PostMapping("/getAllBuckets")
  public Result<List<String>> getAllBuckets() {
    try {
      List<Bucket> buckets = minioUtil.getAllBuckets();
      log.info("获取全部bucket 返回信息:{}", buckets);
      List<String> bucketNames = buckets.stream().map(Bucket::name).collect(Collectors.toList());

      return Result.ok(bucketNames);
    } catch (BusiException e) {
      log.error(e.getMessage());
      return Result.fail(e.getMessage(), e.getCode());
    } catch (Exception e) {
      log.error("获取全部bucket 异常", e);
      return Result.fail(e.getMessage());
    }
  }

  @ApiOperation(value = "文件上传返回url")
  @ApiImplicitParams(
          {
                  @ApiImplicitParam(name = "file", value = "文件对象", required = true,
                          dataType = "MultipartFile",dataTypeClass = MultipartFile.class)
          }
  )
  @PostMapping("/upload")
  public Result<String> upload(@RequestPart("file") MultipartFile file) {
    try {
      String objectUrl = minioUtil.upload(file);
      if (null != objectUrl) {
        return Result.ok(objectUrl);
      }
      return Result.fail();
    } catch (BusiException e) {
      log.error(e.getMessage());
      return Result.fail(e.getMessage(), e.getCode());
    } catch (Exception e) {
      log.error("文件上传返回url 异常", e);
      return Result.fail(e.getMessage());
    }
  }

  @ApiOperation(value = "图片视频预览")
  @PostMapping("/preview")
  public Result preview(@RequestParam("fileName") String fileName) {
    try {
      return Result.ok(minioUtil.preview(fileName));
    } catch (BusiException e) {
      log.error(e.getMessage());
      return Result.fail(e.getMessage(), e.getCode());
    } catch (Exception e) {
      log.error("图片视频预览 异常", e);
      return Result.fail(e.getMessage());
    }
  }

  @ApiOperation(value = "文件下载")
  @PostMapping("/download")
  public Result download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
    try {
      minioUtil.download(fileName, res);
      return Result.ok();
    } catch (BusiException e) {
      log.error(e.getMessage());
      return Result.fail(e.getMessage(), e.getCode());
    } catch (Exception e) {
      log.error("文件下载 异常", e);
      return Result.fail(e.getMessage());
    }
  }

  @ApiOperation(value = "删除文件", notes = "根据url地址删除文件")
  @PostMapping("/delete")
  public Result remove(@RequestParam("fileName") String fileName) {
    try {
      minioUtil.remove(fileName);
      return Result.ok();
    } catch (BusiException e) {
      log.error(e.getMessage());
      return Result.fail(e.getMessage(), e.getCode());
    } catch (Exception e) {
      log.error("删除文件 异常", e);
      return Result.fail(e.getMessage());
    }
  }

}

