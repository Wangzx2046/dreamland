package com.zero.dreamland.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = SwaggerProperties.SWAGGER_PREFIX)
public class SwaggerProperties {

    public static final String SWAGGER_PREFIX = "swagger";

    private String title;

    private String description;

    private String version;

    private String basePackage;

    private String splitor;


}
