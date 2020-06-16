package com.zero.dreamland.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
class DreamlandRedisApplicationTests {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void get() {
        //template.opsForValue().set("test", "我是地瓜");
        System.out.println(redisTemplate.opsForValue().get("test"));
    }

    @Test
    public void contextLoads() {

        redisTemplate.opsForValue().append("date", "123");
        System.out.println(redisTemplate.opsForValue().get("date"));
    }


}
