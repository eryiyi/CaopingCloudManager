package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.RelateDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appOrderWxService")
public class AppOrderWxService implements SaveService {

    @Override
    public Object save(Object object) throws ServiceException {
        Map<String,String> map = (Map<String, String>) object;
        String order_no = map.get("order_no");
        String doublePrices = map.get("doublePrices");
        //生成sign签名
        StringBuffer xml = new StringBuffer();
        try {
            final String ip_str = "127.0.0.1";
            final String body = "meirenmeiba";
            final String trade_type = "APP";
            String  nonceStr = UUIDFactory.random();

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.WX_APP_ID));
            packageParams.add(new BasicNameValuePair("body", body));
            /**这里用的是mach_id,跟sign签名时参数名不同，一定要注意*/
            packageParams.add(new BasicNameValuePair("mch_id", Constants.WX_MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", Constants.WEIXIN_NOTIFY_URL));
            packageParams.add(new BasicNameValuePair("out_trade_no", order_no));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", ip_str));
            packageParams.add(new BasicNameValuePair("total_fee", doublePrices));
            packageParams.add(new BasicNameValuePair("trade_type", trade_type));

            String sign = genAppSign(packageParams).toUpperCase();

            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlstring =toXml(packageParams);
            return new Object[]{xmlstring, order_no};
        } catch (Exception e) {
            return null;
        }
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.WX_API_KEY);

        sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign;
    }

    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");


            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

}
