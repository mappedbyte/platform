package com.francis.platform.common.constans;

/**
 * @author Francis
 */
public class AppConstants {

    public static String JWT_USER_INFO_KEY = "user";
    public static String AUTHORITY_HEADER = "Authorization";
    public static String AUTHORITY_HEADER_CONTENT_PREFIX = "Bearer ";
    public static String REFRESH_TOKEN = "refreshToken";
    public static String LOGIN_URI = "login";
    public static String BLACKLIST = "blackList";
    public static String RESPONSE_CAPTCHA_KEY = "captcha";
    public static String UUID = "uuid";
    public static String REDIS_CAPTCHA_KEY = "captcha:";


    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }

}
