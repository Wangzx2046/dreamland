package com.zero.dreamland.mqtt.config;

import com.zero.dreamland.mqtt.biz.MqttPushClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname MtqqEntity
 * @Description mqtt相关配置信息
 * @Date 2019/4/11 23:00
 * @Created by Jack
 */
@Component
@ConfigurationProperties("spring.mqtt")
@Setter
@Getter
public class MqttConfig {
    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接地址
     */
    private String hostUrl;
    /**
     * 客户Id
     */
    private String clientId;
    /**
     * 默认连接话题
     */
    private String defaultTopic;
    /**
     * 超时时间
     */
    private int timeout;
    /**
     * 保持连接数
     */
    private int keepalive;

    /**
     *   主题(Topic)通过’/‘分割层级，支持’+’, '#'通配符：
     *   ‘+’: 表示通配一个层级，例如a/+，匹配a/x, a/y
     *   ‘#’: 表示通配多个层级，例如a/#，匹配a/x, a/b/c/d
     *   ps：订阅者可以订阅含通配符主题，但发布者不允许向含通配符主题发布消息。
     */
    @Bean
    public MqttPushClient getMqttPushClient() {
        mqttPushClient.connect(hostUrl, clientId, username, password, timeout, keepalive);
        // 以/#结尾表示订阅所有以test开头的主题
        mqttPushClient.subscribe("test/#", 0);
        mqttPushClient.subscribe("haier/#", 0);
        return mqttPushClient;
    }
}

