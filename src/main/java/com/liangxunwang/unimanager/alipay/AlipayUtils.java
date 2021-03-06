package com.liangxunwang.unimanager.alipay;


import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/* *
 *类名：AlipayFunction
 *功能：支付宝接口公用函数类
 *详细：该类是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayUtils {

	protected static final Logger log = Logger.getLogger(AlipayUtils.class);
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilterSign(Map<String, String> sArray) {
    	
    	Map<String, String> result = new HashMap<String, String>();
    	
    	if (sArray == null || sArray.size() <= 0) {
    		return result;
    	}
    	
    	for (String key : sArray.keySet()) {
    		String value = sArray.get(key);
    		if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
    			continue;
    		}
    		result.put(key, value);
    	}
    	
    	return result;
    }

    /** 
     * 把数组所有元素，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要参与字符拼接的参数组
     * @param sorts   是否需要排序 true 或者 false
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());

        Collections.sort(keys);

        
        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord,String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis()+filename+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 支付宝返回参数处理
     */
    @SuppressWarnings("deprecation")
	public static Map<String,String> handleReturnJson(String json){
    	Map<String,String> params = new HashMap<String,String>();
    	String [] jsons = json.split("&");
    	for (String request : jsons) {
    		String [] keyValue = request.split("=");
    		String keyStr = "";
    		String valueStr = "";
    		for (int i = 0; i < keyValue.length; i++) {
    			keyStr = keyValue[0];
    			valueStr = keyValue[1];
    		}
    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
    		try {
				valueStr = URLDecoder.decode(new String(valueStr.getBytes("UTF-8"), "UTF-8"));
				params.put(keyStr, valueStr);
			} catch (Exception e) {
				log.info("【支付宝返回参数处理失败】"+e);
			}
    	}
    	return params;
    }
}
