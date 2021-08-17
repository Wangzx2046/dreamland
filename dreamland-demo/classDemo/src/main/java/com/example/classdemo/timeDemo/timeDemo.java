package com.example.classdemo.timeDemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class timeDemo {
    public static void main(String[] args) {

        //获取当天7点
        LocalDateTime time1= LocalDate.now().atTime(7,0,0);
        System.out.println(time1);

        //LocalDateTime判空
        LocalDate s=null;
        System.out.println(Objects.isNull(s));
        System.out.println(Objects.isNull(time1));
    }
}
