package com.example.otherTest;


import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;

public class test02 {
    public static void main(String[] args) {
        //{"year":2020,"month":6,"day":7,"time":2,"minute":46,"second":24}
        LocalDateTime now = LocalDateTime.now();

        JSONObject jb = new JSONObject();
        jb.put("year", now.getYear());
        jb.put("month", now.getMonth().getValue());
        jb.put("day", now.getDayOfMonth());
        jb.put("time", now.getHour());
        jb.put("minute", now.getMinute());
        jb.put("second", now.getSecond());
        System.out.println(jb);
    }
}
