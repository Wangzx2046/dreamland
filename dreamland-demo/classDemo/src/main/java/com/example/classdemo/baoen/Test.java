package com.example.classdemo.baoen;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws InterruptedException {


        Map lastpch = new HashMap<>();
        lastpch.put("timestamp", Instant.now().minus(30, ChronoUnit.SECONDS).toEpochMilli() / 1000);
        lastpch.put("batch", 101);
        String status = "0";
        int output = 0;
        Integer batch = 101;
        long bt=   new Date().getTime()-new Date(Long.parseLong(String.valueOf( lastpch.get("timestamp")))* 1000).getTime();

        if (!lastpch.isEmpty()) {

            if (batch.equals(lastpch.get("batch")) && output != 0) {//批次号相同，且产量不为0，认为在工作
                status = "3";
            } else if (
              bt< 30000) {
                status = "2";
                System.out.println("间隔1：" + Instant.ofEpochSecond(Long.parseLong(String.valueOf(lastpch.get("timestamp")))));
                System.out.println("间隔2：" +  Instant.now());
                System.out.println("间隔：" + Duration.between(Instant.ofEpochSecond(Long.parseLong(String.valueOf(lastpch.get("timestamp")))), Instant.now()).toMillis());
                System.out.println( Duration.between(Instant.ofEpochSecond(Long.parseLong(String.valueOf(lastpch.get("timestamp")))*1000), Instant.now()).toMillis()< 30l);
            }
        }

        System.out.println("status： " + status);
    }

}
