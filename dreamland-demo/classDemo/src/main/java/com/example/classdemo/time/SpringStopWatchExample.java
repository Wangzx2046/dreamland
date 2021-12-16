package com.example.classdemo.time;

import org.springframework.util.StopWatch;

/**
 * 使用 StopWatch 优雅打印执行耗时
 *
 * 有时在做开发的时候需要记录每个任务执行时间，或者记录一段代码执行时间，最简单的方法就是打印当前时间与执行完时间的差值，然后这样如果执行大量测试的话就很麻烦，并且不直观，如果想对执行的时间做进一步控制，则需要在程序中很多地方修改，目前spring-framework提供了一个StopWatch类可以做类似任务执行时间控制，也就是封装了一个对开始时间，结束时间记录工具。
 */
 public class SpringStopWatchExample {

     public static void main (String[] args) throws InterruptedException {
         StopWatch sw = new StopWatch();
         sw.start();
         //long task simulation
         Thread.sleep(1000);
        sw.stop();
        System.out.println("執行耗時："+sw.getTotalTimeMillis());
   }
}