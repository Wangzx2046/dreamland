package com.zero.dreamland.common.password;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    private final static String salt = "V1ASD5D8A48ASD1Q1W";

    @Override
    public String encode(CharSequence charSequence) {

        return DigestUtils.md5Hex(charSequence + salt).toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }


    public static void main(String[] args) {
        System.out.println(new MyPasswordEncoder().encode("123"));
    }
}