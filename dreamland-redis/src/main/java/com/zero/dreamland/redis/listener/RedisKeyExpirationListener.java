package com.zero.dreamland.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author wzx
 * @Description: 监听所有db的过期事件__keyevent@*__:expired"
 */
@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        String prefix = expiredKey.substring(0, expiredKey.lastIndexOf(":")+1);
        String orderCode = expiredKey.substring(prefix.length());//这个不一定是订单号，姑且认为是订单号。如果不是订单好，前缀也不会进switch处理

        switch (prefix) {
            case "xxx1":
                log.info("注解前缀：xxx1" + orderCode);
                break;
            case "xxx2":
                log.info("注解前缀：xxx2" + orderCode);
                break;
            default:
                System.out.println("无需处理的key:" + expiredKey);

        }


    }
}