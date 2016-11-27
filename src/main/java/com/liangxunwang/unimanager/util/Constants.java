package com.liangxunwang.unimanager.util;

/**
 * Created by zhl on 2015/1/29.
 */
public class Constants {
    //服务器地址
//        public static final String URL = "http://139.196.169.8:8080/";
        public static final String URL = "http://192.168.0.224:8080/";

    public static final String DOWNLOAD_URL = URL + "html/MeirenmeibaApp.apk";
    //注册用默认会员等级--青铜会员
    public static final String DEFAULT_LEVEL = "d42535ff62e147ae80dba7bc9d8ea0d4";

    //七牛云存储
    public static final String QINIU_URL = "http://ogcvwqv8j.bkt.clouddn.com/";
    public static final String QINIU_SPACE = "paopao-pic";
    public static final String FILE_PATH = "D://recordfile";

    //百度推送
    public static final String API_KEY = "vfwRCBwcuBjoZGCLYBUKdZ0d";
    public static final String SECRET_KEY = "r9k1po3sQCHzc30zOKkDd3nnkLBTKOim";
    public static final String IOS_API_KEY = "";
    public static final String IOS_SECRET_KEY = "";

    public static final String SAVE_ERROR = "save_error";

    public static final String HAS_ZAN = "has_zan";
    public static final String MOBILE_UP_DEFAULT = "10000000000";

    public static final String HAS_CODE = "has_code";

    public static final String NO_SEND_CODE = "no_send_code";

    public static final String SEND_SMS_ERROR = "send_sms_error";

    public static final String HAS_EXISTS = "has_exists";

    public static final String TOO_MANY_CODE = "too_many_code";

    public static final String CODE_NOT_EQUAL = "code_not_equal";

    public static final String PHONE_ERROR = "phone_error";
    public static final String HX_ERROR = "hx_error";


    public static final String SMS_MESSAGE_URL = "http://60.209.7.78:8080/smsServer/submit";


    //默认头像
    public static final String[] PHOTOURLS = {
            "upload/pic1.jpg",
            "upload/pic2.jpg",
            "upload/pic3.jpg",
            "upload/pic4.jpg",
            "upload/pic5.jpg",
            "upload/pic6.jpg",
            "upload/pic7.jpg",
            "upload/pic8.jpg",
            "upload/pic9.jpg",
            "upload/pic10.jpg",
            "upload/pic11.jpg",
            "upload/pic12.jpg",
            "upload/pic13.jpg",
            "upload/pic14.jpg",
            "upload/pic15.jpg",
            "upload/pic16.jpg",
            "upload/pic17.jpg",
            "upload/pic18.jpg",
            "upload/pic19.jpg",
            "upload/pic20.jpg",
            "upload/pic21.jpg",
            "upload/pic22.jpg",
            "upload/pic23.jpg",
            "upload/pic24.jpg",
            "upload/pic25.jpg",
            "upload/pic26.jpg",
            "upload/pic27.jpg",
            "upload/pic28.jpg",
            "upload/pic29.jpg",
            "upload/pic30.jpg",
            "upload/pic31.jpg",
            "upload/pic32.jpg",
            "upload/pic33.jpg",
            "upload/pic34.jpg",
            "upload/pic35.jpg",
            "upload/pic36.jpg",
            "upload/pic37.jpg",
            "upload/pic38.jpg",
            "upload/pic39.jpg",
            "upload/pic40.jpg",
            "upload/pic41.jpg",
            "upload/pic42.jpg",
            "upload/pic43.jpg",
            "upload/pic44.jpg",
            "upload/pic45.jpg",
            "upload/pic46.jpg",
            "upload/pic47.jpg",
            "upload/pic48.jpg",
            "upload/pic49.jpg",
            "upload/pic50.jpg",
            "upload/pic51.jpg",
            "upload/pic52.jpg",
            "upload/pic53.jpg",
            "upload/pic54.jpg",
            "upload/pic55.jpg",
            "upload/pic56.jpg",
            "upload/pic57.jpg",
            "upload/pic58.jpg",
            "upload/pic59.jpg",
            "upload/pic60.jpg",
            "upload/pic61.jpg"
    };

    public static final Long DAY_MILLISECOND = 86400000L;

    //----------------支付宝------------------
    //商户PID
    public static final String PARTNER = "2088421395960317";
    //商户收款账号
    public static final String SELLER = "sdgrwlkj@126.com";
    //商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBxGvCO6MZlgQWV" +
            "DNyK1gCMfwfCNdJAxoYeTr2Q4DobVBtf8aSr6qHF32hpZ5XJuHd9GHVWAtF8Etao" +
            "Hhfe15S4gB/mllHpO9CF3uwTl4DRnEUk2EPLCqv0Ls8UghrVZ8/N837p3KI13wLk" +
            "Uus/YN+d5Zkwfx6PXs7HcOygC1jDAgMBAAECgYBHR93VqumXyzM+MRZhfl+ENoY7" +
            "BnnPFkUymnlKCCa1/zdpKGGmQDOZlmSJFTnUJrLYN5Leq2xB0fZcYks7RkSNYcR/" +
            "/q9mCwRf/0ihFtIKybLsFaMP9DM5qHn7r0t9MtjM9UjGVP4ub9EKbC0lmZOIZ0Lg" +
            "kE3rv7UbwccWtMheGQJBAPFShcW1SIZ4PokCQsi37cDMdEQEhhveIpHKfqyCHcVH" +
            "Ts8QLnMgKiF8Ec18RGaK+ccWZvD62EbUAsF8Zyi+vH8CQQDMJX6s+erJt+1x3H/K" +
            "Nnydx+tdC32SyxLXgc8fO13sQrd0E3XHBCT/5sXL2gSqZp5RyAQz/tvTv6XT1Rxb" +
            "alG9AkEAhaDOlnYV3PBEoJVx6bd/nd0ZHHjs0g2lUtmwX1IFrjIYP6yULAfJjJUp" +
            "PoHrVmTqfwW2pwYgMMhBZma+sN89fQJBAMNIytjlaXf1fiqBZ60kWikE6V1kdIdL" +
            "w4ZsIpoZzEsJtzxOsUfj6xDwzR2oPPZUm/ZXg83JargwiI0PO8Um+3ECQDHvrfZz" +
            "gfsYbXztvdkf7rkwC1zQkKiSLm2jz8BQdWGvUnh+kZKBDmiHrKDu3w1tBMiL3miC" +
            "vbcy26Q68j62CEA=";
    //支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvFkyO6eom1IpsvCKs2Y55iFznFImLh5M8ON1n47+pYX1SV8cXZ+7gX0U0dFwORVgE27wv/fXC0r4UmOk5U8SD9JP56LZKiw148G8kLeGyJn8CoYVqCH0WM337K603kCd05v4lRP/jgjHASiMb2fK7mRE9MHZbeLwLzUilJwAIkwIDAQAB";


    //微信统一下单notify_url
    public static final String WEIXIN_NOTIFY_URL = URL + "pay/notify_url_wxpay.jsp";

    //支付宝回调页面
    public static final String ZFB_NOTIFY_URL = URL + "pay/notify_url_alipay.jsp";

    //appid
    public static final String WX_APP_ID = "wx9769250919c81901";//yum
    //商户号
    public static final String WX_MCH_ID = "1393020902";//yum
    //  API密钥，在商户平台设置
    public static final  String WX_API_KEY="7289232292675542ceb8e6d7b68015ef";//yum
//        7289232292675542ceb8e6d7b68015ef
}
