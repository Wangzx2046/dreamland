package com.zero.dreamland.mqtt.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class MyMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        String msg = message.getPayload().toString();

        System.out.println("\n--------------------START-------------------\n" +
                "接收到订阅消息:\ntopic:" + topic + "\nmessage:" + msg +
                "\n---------------------END--------------------");
    }
}

