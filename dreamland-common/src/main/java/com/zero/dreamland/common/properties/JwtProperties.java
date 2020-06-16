package com.zero.dreamland.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt及redis参数配置
 * @author Zheng Jie
 * @date 2019年11月28日
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {



    /** 必须使用最少88位的Base64对该令牌进行编码 */
    private String base64Secret;

    /** 令牌过期时间 此处单位/秒 */
    private Long expire;




}
