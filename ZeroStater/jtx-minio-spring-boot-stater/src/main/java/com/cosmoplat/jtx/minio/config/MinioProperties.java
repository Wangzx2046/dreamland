package com.cosmoplat.jtx.minio.config;

import com.cosmoplat.jtx.common.constant.MinioConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author admin
 */
@Data
@Component
@ConfigurationProperties(prefix = MinioConstant.PREFIX)
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String defaultBucketName ="default";
}
