package com.example.classdemo.timeDemo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class timeDemo {
    public static void main(String[] args) {
        LocalDateTime time1= LocalDate.now().atTime(7,0,0);

        System.out.println(time1);
    }
}
