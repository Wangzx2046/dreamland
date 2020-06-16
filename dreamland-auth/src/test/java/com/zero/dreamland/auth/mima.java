package com.zero.dreamland.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @ClassName: mima
 * @Description: asda*
 * @Author: wzx
 * @Date: 2020-06-10 14:28
 * @Version 1.0.0
 **/
public class mima {
    public static void main(String[] args) {
        String pwd = "123";
        String encodePwd = BCrypt.hashpw(pwd, BCrypt.gensalt()); // 加密，核心代码
        System.out.println(encodePwd);
        boolean flag = BCrypt.checkpw(pwd, encodePwd); // 验证加密是否正确
        System.out.println(flag);
    }
}
