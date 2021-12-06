package com.example.classdemo.otherDemo.sjms;

public class BingFa {

    public static void main(String[] args) {

        // 创建3个线程，线程里面进行加操作
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                Phone phone = Phone.getInstance();
                int reduce = phone.getReduce();
                System.out.println(Thread.currentThread().getName()+
                "线程抢到了手机，剩余手机数量"+"-----"+reduce);
            }, String.valueOf(i)).start();
        }

    }
}