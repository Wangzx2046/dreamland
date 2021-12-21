package com.example.classdemo;

import com.example.classdemo.thread.AsyncTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@SpringBootTest
class ClassDemoApplicationTests {


    @Autowired
    AsyncTest asyncTest;
    @Autowired
    ThreadPoolTaskExecutor poolExecutor; //会去匹配 @Bean("poolExecutor") 这个线程池

    @Test
    void contextLoads() throws InterruptedException {
        asyncTest.hello("async注解创建");
        asyncTest.hello1("自动找2");
        //一定要休眠 不然主线程关闭了，子线程还没有启动
        poolExecutor.submit(new Thread(() -> {
            log.info("threadPoolTaskExecutor 创建线程");
        }));
        Thread.sleep(1000);
    }


}
