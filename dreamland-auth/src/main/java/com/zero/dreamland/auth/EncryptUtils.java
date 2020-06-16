package com.zero.dreamland.auth;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 *
 * @author Punk
 * @date 2019/04/10
 */
public class EncryptUtils {

    public static String salt = "V1ASD5D8A48ASD1Q1W";//盐

    /**
     * 校验输入密码是否正确
     *
     * @param encryptPassword
     * @param password
     * @return
     */
    public static Boolean verfifyPassword(String encryptPassword, String password) {
        String encryptStr = encryptPassword(password);
        return encryptPassword.equals(encryptStr);
    }

    /**
     * 获得加密后的密码
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return DigestUtils.md5Hex(password + salt);
    }


    public static void main(String[] args) {
        System.out.print(encryptPassword("123"));
    }


}
