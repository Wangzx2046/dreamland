package com.example.zeromq.demos.web;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "your_topic_name", consumerGroup = "your_consumer_group_name")
public class MyConsumer implements RocketMQListener<Message> {

    @Override
    public void onMessage(Message message) {
        // 处理消息的逻辑
        System.out.println("Received message: " + message);
        throw new RuntimeException("故意爆粗");
    }
}
