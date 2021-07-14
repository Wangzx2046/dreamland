package com.zero.dreamland.mqtt.handle;

import com.zero.dreamland.influxdb.biz.TimeSeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;


@Slf4j
public class MqttMessageHandler2 implements MessageHandler {

    @Resource
    TimeSeriesRepository timeSeriesRepository;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //1.解析MQTT消息
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        String msg = message.getPayload().toString();

        log.info("我是信道2：======》" + topic);


    }
}
