package com.zero.dreamland.common.properties;

/**
 * 常量
 * @author Wang926454
 * @date 2018/9/3 16:03
 */
public class Constant {



    /**
     * redis-key-前缀-security:refresh_token:
     * 在线用户 key，根据 key 查询 redis 中在线用户的数据
     */
    public final static String PREFIX_SECURITY_REFRESH_TOKEN = "security:refresh_token:";

    /**
     * redis-key-前缀-security:access_info:
     * 权限
     */
    public final static String PREFIX_SECURITY_ACCESS_INFO = "security:access_info:";

    /**
     * redis-key-前缀-verification:code_key:
     * 验证码
     */
    public final static String PREFIX_VERIFICATION_CODE_KEY = "verification:code_key:";

    /**
     * JWT-account:
     */
    public final static String ACCOUNT = "account";

    /**
     * JWT-BEARER:
     */
    public final static String TOKEN_TYPE_BEARER = "Bearer ";

    /**
     * JWT-HEADER:
     */
    public final static String TOKEN_HEADER = "Authorization";


    /**
     * JWT-currentTimeMillis:
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * PASSWORD_MAX_LEN
     */
    public static final Integer PASSWORD_MAX_LEN = 8;




}
