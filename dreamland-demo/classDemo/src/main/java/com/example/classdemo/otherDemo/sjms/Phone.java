package com.example.classdemo.otherDemo.sjms;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Phone {

    //手机余额
    private AtomicInteger count = new AtomicInteger(3);

    private Phone(){}

    private static Phone phone;

    public static Phone getInstance(){
        if (phone == null){
            phone = new Phone();
        }
        return phone;
    }

    public int getReduce(){
        return count.decrementAndGet();
    }

}