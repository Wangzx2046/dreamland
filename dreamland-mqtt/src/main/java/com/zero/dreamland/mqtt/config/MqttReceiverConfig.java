package com.zero.dreamland.mqtt.config;


import com.zero.dreamland.mqtt.handle.MqttMessageHandler;
import com.zero.dreamland.mqtt.handle.MqttMessageHandler2;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author wzx
 * @create 2021-01-28 13:28
 */

/**
 * MQTT配置，消费者
 */
@Configuration
@ConfigurationProperties("spring.mqtt")
@ConditionalOnProperty(prefix = "spring.mqtt", name = "enabled", havingValue = "true")
public class MqttReceiverConfig {
    /**
     * 订阅的bean名称
     */
    public static final String CHANNEL_NAME_IN = "mqttInboundChannel";
    public static final String CHANNEL_NAME_IN_2 = "mqttInboundChannel_2";



    // 客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
    private static final byte[] WILL_DATA;

    static {
        WILL_DATA = "offline".getBytes();
    }

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.hostUrl}")
    private String hostUrl;

    @Value("${spring.mqtt.receiver.client-id}")
    private String clientId;

    @Value("${spring.mqtt.receiver.default-topic}")
    private String defaultTopic;

    /**
     * MQTT连接器选项
     */
    @Bean
    public MqttConnectOptions getReceiverMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置连接的用户名
        if (!"".equals(username.trim())) {
            options.setUserName(username);
        }
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置连接的地址
        options.setServerURIs(hostUrl.split(","));
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
        // 但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        options.setAutomaticReconnect(true);
        return options;
    }

    /**
     * MQTT客户端
     */
    @Bean
    public MqttPahoClientFactory receiverMqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getReceiverMqttConnectOptions());
        return factory;
    }

    /**
     * MQTT信息通道（消费者）
     *
     *  mqtt消息入站通道，订阅消息后消息进入的通道。
     *  可创建多个入站通道，对应多个不同的消息生产者。
     */
    @Bean(name = CHANNEL_NAME_IN)
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }




    /**
     * MQTT消息订阅绑定（消费者）
     *
     * 对于当前应用来讲，接收的mqtt消息的生产者。将生产者绑定到mqtt入站通道，即通过入站通道进入的消息为生产者生产的消息。
     * 可创建多个消息生产者，对应多个不同的消息入站通道，同时生产者监听不同的topic
     *
     * @return
     */
    @Bean
    public MessageProducer inbound() {
        // 可以同时消费（订阅）多个Topic
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        clientId, receiverMqttClientFactory(),
                        defaultTopic.split(","));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    /**
     * MQTT消息处理器（消费者）
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handler() { return new MqttMessageHandler(); }



    @Bean(name = CHANNEL_NAME_IN_2)
    public MessageChannel mqttInboundChannel2() {return new DirectChannel();}

    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN_2)
    public MessageHandler handler2() { return new MqttMessageHandler2();}

    @Bean
    public MessageProducer inbound2() {
        // 可以同时消费（订阅）多个Topic
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        clientId+"123", receiverMqttClientFactory(),
                        defaultTopic.split(","));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInboundChannel2());
        return adapter;
    }

}
