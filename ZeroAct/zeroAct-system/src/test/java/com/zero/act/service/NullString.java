package com.zero.act.service;

public class NullString {
    public static void main(String[] args) {
        //错误1 空指针异常
        String s = "null";
        System.out.println(s.substring(0, 10));

        //错误2 空指针异常
        String s1 = null;
        System.out.println(s1.substring(0, 10));
    }
}
