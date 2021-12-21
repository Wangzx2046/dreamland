package com.example.classdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ClassDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassDemoApplication.class, args);
    }

}
