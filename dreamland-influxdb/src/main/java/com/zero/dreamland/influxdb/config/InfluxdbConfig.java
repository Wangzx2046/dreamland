package com.zero.dreamland.influxdb.config;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Configuration
@ConditionalOnProperty(prefix = "spring.influx", name = "enabled", havingValue = "true")
public class InfluxdbConfig {
 
    @Value("${spring.influx.url:''}")
    private String influxDbUrl;
    @Value("${spring.influx.token:''}")
    private String token;
 
    @Bean
    public InfluxDBClient influxDBClient() {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(influxDbUrl, token.toCharArray());
        influxDBClient.setLogLevel(LogLevel.BASIC);
        return influxDBClient;
    }
}