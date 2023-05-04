package com.cosmoplat.jtx.minio.util;

import com.cosmoplat.jtx.common.exception.BusiException;
import com.cosmoplat.jtx.minio.config.MinioConfig;
import com.cosmoplat.jtx.minio.config.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kaiye
 */
@Component
@Slf4j
public class MinioUtil {

  @Resource
  private MinioProperties prop;
  @Resource
  private MinioClient minioClient;



  /**
   * 查看存储bucket是否存在
   *
   * @return boolean
   */

  public Boolean bucketExists(String bucketName) {
    Boolean found;
    try {
      found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      return false;
    }
    return found;
  }



  /**
   * 创建存储bucket
   *
   * @return Boolean
   */

  public Boolean makeBucket(String bucketName) {
    try {
      minioClient.makeBucket(MakeBucketArgs.builder()
              .bucket(bucketName)
              .build());
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
    return true;
  }

  /**
   * 删除 桶
   * @param bucketName
   * @return
   */
  public Boolean removeBucket(String bucketName) {
    try {
      minioClient.removeBucket(RemoveBucketArgs.builder()
              .bucket(bucketName)
              .build());
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
    return true;
  }

  /**
   * 获取全部 bucket
   */
  public List<Bucket> getAllBuckets() {
    try {
      List<Bucket> buckets = minioClient.listBuckets();
      return buckets;
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
  }


  /**
   * 文件上传
   *
   * @param file 文件
   * @return Boolean
   */

  public String upload(MultipartFile file) {
    return this.upload(file, prop.getDefaultBucketName());
  }


  /**
   * 文件上传
   * @param file
   * @param bucketName
   * @return
   */
  public String upload(MultipartFile file, String bucketName) {
    String originalFilename = file.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      throw new RuntimeException();
    }
    String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
    String objectName = fileName;
    try {
      PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName)
              .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
      //文件名称相同会覆盖
      minioClient.putObject(objectArgs);
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }

    return prop.getEndpoint() + "/" + bucketName + "/" + objectName;

  }


  /**
   * 预览图片
   * @param fileName
   * @return
   */

  public String preview(String fileName) {
    return this.preview(prop.getDefaultBucketName(), fileName);
  }

  /**
   *  预览图片
   * @param bucketName
   * @param fileName
   * @return
   */
  public String preview(String bucketName, String fileName) {
    // 查看文件地址
    GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
            .bucket(bucketName).object(fileName)
            .method(Method.GET).build();
    try {
      String url = minioClient.getPresignedObjectUrl(build);
      return url;
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
  }

  /**
   * 文件下载
   *
   * @param fileName 文件名称
   * @param httpServletResponse      response
   * @return Boolean
   */

  public void download(String fileName, HttpServletResponse httpServletResponse) {
    this.download(prop.getDefaultBucketName(), fileName, httpServletResponse);
  }

  /**
   * 文件下载
   *
   * @param bucketName
   * @param fileName
   * @param httpServletResponse
   */


  public void download(String bucketName, String fileName, HttpServletResponse httpServletResponse) {
    GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName)
            .object(fileName).build();
    try (InputStream response = minioClient.getObject(objectArgs)) {
      byte[] buf = new byte[1024];
      int len;
      try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
        while ((len = response.read(buf)) != -1) {
          os.write(buf, 0, len);
        }
        os.flush();
        byte[] bytes = os.toByteArray();
        httpServletResponse.setCharacterEncoding("utf-8");
        // 设置强制下载不打开
        httpServletResponse.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try (ServletOutputStream stream = httpServletResponse.getOutputStream()) {
          stream.write(bytes);
          stream.flush();
        }
      }
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
  }

  /**
   * 查看文件对象
   *
   * @return 存储bucket内文件对象信息
   */

  public List<Item> listObjects() {
    return this.listObjects(prop.getDefaultBucketName());
  }

  public List<Item> listObjects(String bucketName) {
    Iterable<Result<Item>> results = minioClient.listObjects(
            ListObjectsArgs.builder().bucket(bucketName).build());
    List<Item> items = new ArrayList<>();
    try {
      for (Result<Item> result : results) {
        items.add(result.get());
      }
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
    return items;
  }


  /**
   * 删除
   *
   * @param fileName
   * @return
   */

  public boolean remove(String fileName) {
    return this.remove(prop.getDefaultBucketName(), fileName);
  }

  /**
   * 删除
   *
   * @param bucketName
   * @param fileName
   * @return
   */
  public boolean remove(String bucketName, String fileName) {
    try {
      minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
    } catch (Exception e) {
      log.error(e.getClass().getName(), e);
      throw new BusiException(e.getMessage());
    }
    return true;
  }

}
