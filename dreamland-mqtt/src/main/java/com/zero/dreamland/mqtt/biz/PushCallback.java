package com.zero.dreamland.mqtt.biz;

import com.zero.dreamland.mqtt.config.MqttConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname PushCallback
 * @Description 消费监听类
 * @Date 2019/4/11 23:31
 * @Created by Jack
 */
@Slf4j
@Component
public class PushCallback implements MqttCallback {

    @Autowired
    private MqttConfig mqttConfig;

    private static MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        log.info("【MQTT】连接断开，可以做重连");
        if (client == null || !client.isConnected()) {
            mqttConfig.getMqttPushClient();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        log.info("【MQTT】接收消息主题 : " + topic);
        log.info("【MQTT】接收消息Qos : " + mqttMessage.getQos());
        log.info("【MQTT】接收消息内容 : " + new String(mqttMessage.getPayload()));
        //todo 对接收到的消息进行业务处理
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("【MQTT】deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
