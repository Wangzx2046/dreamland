package com.example.classdemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncTest {

    @Async("taskPoolExecutor")
    public void hello(String name){
    	//这里使用logger 方便查看执行的线程是什么
        log.info("异步线程启动 started."+name);
    }


    @Async()
    public void hello1(String name){
        //这里使用logger 方便查看执行的线程是什么
        log.info("异步线程启动 started."+name+"我是自动找的第二个");
    }
}
