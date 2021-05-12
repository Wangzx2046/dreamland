package com.zero.dreamland.mqtt.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zero.dreamland.influxdb.biz.TimeSeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;
import java.util.HashMap;


@Slf4j
public class MqttMessageHandler implements MessageHandler {

    @Resource
    TimeSeriesRepository timeSeriesRepository;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //1.解析MQTT消息
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        String msg = message.getPayload().toString();
       /* System.out.println("qw:" + Thread.currentThread().getName() + "-----");
        System.out.println("\n--------------------START-------------------\n" +
                "接收到订阅消息:\ntopic:" + topic + "\nmessage:" + msg +
                "\n---------------------END--------------------");*/
        String[] arr = topic.split("/");
        JSONObject json = JSON.parseObject(msg);
        JSONObject reported = json.getJSONObject("reported");
        JSONObject metrics = reported.getJSONObject("metrics");

        HashMap<String, Object> hashMap = JSON.parseObject(metrics.toString(), HashMap.class);

        if (!"YFSGW0D0J00S8O88D0F3".equals(arr[1])) {
            timeSeriesRepository.save(arr[1], hashMap);
        }

    }
}
