package com.liangxunwang.unimanager.alipay;

/**
 * Created by zhl on 2016/7/27.
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088421395960317";
    // 商户的私钥
    public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBxGvCO6MZlgQWV" +
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

    // 支付宝的公钥，无需修改该值
    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "RSA";
}
