package com.cosmoplat.jtx.minio.config;

import com.cosmoplat.jtx.common.constant.MinioConstant;
import com.cosmoplat.jtx.minio.service.MinioService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author kaiye
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnClass(MinioService.class)
@ConditionalOnProperty(prefix = MinioConstant.PREFIX, value = "enabled", matchIfMissing = true)
public class MinioConfig {
  private final MinioProperties properties;

  public MinioConfig(MinioProperties properties) {
    this.properties = properties;
  }

  @Bean
  public MinioClient minioClient(MinioProperties properties){
    MinioClient.Builder builder = MinioClient.builder();
    builder.endpoint(properties.getEndpoint());
    if (StringUtils.hasLength(properties.getAccessKey()) && StringUtils.hasLength(properties.getSecretKey())) {
      builder.credentials(properties.getAccessKey(),properties.getSecretKey());
    }
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean(MinioService.class)
  public MinioService minioService(MinioProperties prop, MinioClient minioClient) {
    return new MinioService(prop, minioClient);
  }

//
//  @Bean
//  @ConditionalOnMissingBean(MinioProperties.class)
//  public MinioProperties minioProperties() {
//    return new MinioProperties();
//  }

}

