package com.cosmoplat.jtx.minio.config;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author kaiye
 */
@Slf4j
@Configuration
public class MinioConfig {

  @Bean
  public MinioClient minioClient(MinioProperties properties){
    MinioClient.Builder builder = MinioClient.builder();
    builder.endpoint(properties.getEndpoint());
    if (StringUtils.hasLength(properties.getAccessKey()) && StringUtils.hasLength(properties.getSecretKey())) {
      builder.credentials(properties.getAccessKey(),properties.getSecretKey());
    }
    return builder.build();
  }



}

