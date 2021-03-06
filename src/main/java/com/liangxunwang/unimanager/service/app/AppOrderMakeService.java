package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.CpObj;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.model.ShoppingTrade;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/14.
 */
@Service("appOrderMakeService")
public class AppOrderMakeService implements SaveService,UpdateService,ListService,FindService {
//    private static Logger logger = LogManager.getLogger(AppOrderMakeService.class);
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    @Autowired
    @Qualifier("cpObjDao")
    private CpObjDao cpObjDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("relateDao")
    private RelateDao relateDao;

    //保存订单
    @Override
    public Object save(Object object) throws ServiceException {
        List<Order> lists = (List<Order>) object;
//        //先判断商品剩余数量，是否大于要购买的数量
//        for(Order order:lists){
////            order.getGoods_count();//订单数量
//            if(!StringUtil.isNullOrEmpty(order.getGoods_count())){
//                //根据商品ID查询商品数量
//                CpObj vo = cpObjDao.findById(order.getCloud_caoping_id());
//                if(vo != null){
//                    if(!StringUtil.isNullOrEmpty(vo.getCount())){
//                        if(Integer.parseInt(vo.getCount()) < Integer.parseInt(order.getGoods_count())){
//                            throw new ServiceException("outOfNum");//超出数量限制
//                        }
//                    }
//                }
//            }
//        }
        Double doublePrices = 0.0;
        if(lists!=null && lists.size() > 0){
            for(Order order:lists){
                doublePrices += order.getPayable_amount();
            }
        }
        doublePrices = Double.parseDouble(String.format("%.2f",doublePrices));
        //商品总额，用于插入订单金额
        String out_trade_no = UUIDFactory.random();//订单总金额的id
        ShoppingTrade shoppingTrade = new ShoppingTrade();
        shoppingTrade.setOut_trade_no(out_trade_no);
        shoppingTrade.setPay_status("0");
        shoppingTrade.setDateline(System.currentTimeMillis() + "");
        shoppingTrade.setTrade_prices(String.valueOf(doublePrices));

        //保存总订单--和支付宝一致
        appOrderMakeSaveDao.saveTrade(shoppingTrade);
        int is_dxk_order = 0;
        //构造订单列表
        if(lists!=null && lists.size() > 0){
            for(Order order:lists){
                if("0".equals(order.getIs_dxk_order())){
                    is_dxk_order = 0;//普通订单
                }
                if("2".equals(order.getIs_dxk_order())){
                    is_dxk_order = 2;//充值定向卡的
                }
                order.setOrder_no(UUIDFactory.random());
                order.setCreate_time(System.currentTimeMillis() + "");
                order.setStatus("1");//1生成订单
                order.setPay_status("0");//0未支付
                order.setOut_trade_no(out_trade_no);
                order.setIsAccount("0");
                //自动签收时间--accept_time
//                order.setAccept_time(DateUtil.beforLongDate(System.currentTimeMillis(),7));
            }
        }
        //保存订单
        for(Order order:lists){
            appOrderMakeSaveDao.saveList(order);
        }

        for(Order order:lists){
            if(!StringUtil.isNullOrEmpty(order.getCloud_caoping_id())){
                CpObj cpObj = cpObjDao.findById(order.getCloud_caoping_id());
                if(cpObj != null){
                    cpObj.setSale_count(order.getGoods_count());
                    cpObjDao.updateCountById(cpObj);
                }
            }
        }
        String notify_url = "";
        if(is_dxk_order == 0){
            notify_url =  Constants.ZFB_NOTIFY_URL;
        }else{
            notify_url = Constants.ZFB_NOTIFY_URL;
        }
        String orderInfo = StringUtil.getOrderInfo(out_trade_no, "caopingyun", "caopingyun_pay", String.valueOf(doublePrices), notify_url);
        // 对订单做RSA 签名
        String sign = StringUtil.sign(orderInfo);

        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
            return new OrderInfoAndSign(orderInfo, sign, out_trade_no);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("ISWRONG");
        }
    }

    //更新订单状态
    @Override
    public Object update(Object object) {
        if (object instanceof Order){
            //跟新主订单和分订单状态
            Order order = (Order) object;
            List<Order> orders = appOrderMakeSaveDao.findOrderByTradeNo(order.getOut_trade_no());
            if(orders != null && orders.size() > 0){
                Order order1 = orders.get(0);
                if(order1 != null){
                    if(!"2".equals(order1.getStatus())){
                        appOrderMakeSaveDao.updateTradeById(order.getOut_trade_no());
                        order.setPay_time(System.currentTimeMillis() + "");
                        appOrderMakeSaveDao.updateOrderById(order);
                    }
                }
            }

            //根据交易号查询订单列表
//            List<Order> orders = appOrderMakeSaveDao.findOrderByTradeNo(order.getOut_trade_no());
//            if(orders != null){
//                for(Order ord:orders){
//                    //发通知给卖家：买家已付款
//                    Member member =  memberDao.findById(ord.getSeller_emp_id());
//                    if(member != null){
//                        //保存相关信息
//                        Relate relate1 = new Relate();
//                        relate1.setId(UUIDFactory.random());
//                        relate1.setEmpId(ord.getEmp_id());
//                        relate1.setEmpTwoId(ord.getSeller_emp_id());
//                        relate1.setOrderId(ord.getOrder_no());
//                        relate1.setTypeId("2");
//                        relate1.setDateline(System.currentTimeMillis()+"");
//                        relate1.setCont(member.getEmpName()+"生成订单" + ",订单号："+ord.getOrder_no());
//                        relateDao.save(relate1);
//
//                        String pushId =member.getPushId();
//                        String type = member.getDeviceType();
////                        pushMsg(pushId, type, member.getEmpName() + "生成订单"+ord.getPayable_amount()+",订单号："+ord.getOrder_no());
//                    }
//
//                }
//            }
        }else {
            //支付单一订单成功，更新分订单状态
            String order_no = (String) object;
            String pay_time =  System.currentTimeMillis() + "";
            appOrderMakeSaveDao.updateOrderBySingle(order_no,pay_time);
            //根据订单号查询订单详情
//            OrderVo order = appOrderMakeSaveDao.findOrderByOrderNo(order_no);
            //发通知给卖家：买家已付款
//            Member member =  memberDao.findById(order.getSeller_emp_id());
//            if(member != null){
//                //保存相关信息
//                Relate relate1 = new Relate();
//                relate1.setId(UUIDFactory.random());
//                relate1.setEmpId(order.getEmp_id());
//                relate1.setEmpTwoId(order.getSeller_emp_id());
//                relate1.setOrderId(order.getOrder_no());
//                relate1.setTypeId("2");
//                relate1.setDateline(System.currentTimeMillis()+"");
//                relate1.setCont(member.getEmpName()+"生成订单"+order.getPayable_amount()+",订单号："+order_no);
//                relateDao.save(relate1);
//
//                String pushId =member.getPushId();
//                String type = member.getDeviceType();
////                pushMsg(pushId, type, member.getEmpName() + "生成订单" + order.getPayable_amount()+",订单号："+order_no);
//            }

        }
        return null;
    }

    //查询订单列表
    @Override
    public Object list(Object object) throws ServiceException {
        OrdersQuery query = (OrdersQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
            map.put("emp_id", query.getEmpId());
        }
        if (!StringUtil.isNullOrEmpty(query.getSeller_emp_id())) {
            map.put("seller_emp_id", query.getSeller_emp_id());
        }
        if (!StringUtil.isNullOrEmpty(query.getStatus())) {
            map.put("status", query.getStatus());
        }
        if("0".equals(query.getEmptype())){
            //会员查询自己的订单
//            private String completion_time;//订单完成时间

            List<OrdersVO> list = appOrderMakeSaveDao.listOrders(map);
            for (OrdersVO record : list){
                if(!StringUtil.isNullOrEmpty(record.getCreate_time())){
                    record.setCreate_time(RelativeDateFormat.format(Long.parseLong(record.getCreate_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getPay_time())){
                    record.setPay_time(RelativeDateFormat.format(Long.parseLong(record.getPay_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getAccept_time())){
                    record.setAccept_time(RelativeDateFormat.format(Long.parseLong(record.getAccept_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getSend_time())){
                    record.setSend_time(RelativeDateFormat.format(Long.parseLong(record.getSend_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getCompletion_time())){
                    record.setCompletion_time(RelativeDateFormat.format(Long.parseLong(record.getCompletion_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                    if (record.getEmp_cover().startsWith("upload")) {
                        record.setEmp_cover(Constants.URL + record.getEmp_cover());
                    }else {
                        record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                    }
                }
                if(!StringUtil.isNullOrEmpty(record.getEmp_cover_seller())){
                    if (record.getEmp_cover_seller().startsWith("upload")) {
                        record.setEmp_cover_seller(Constants.URL + record.getEmp_cover_seller());
                    }else {
                        record.setEmp_cover_seller(Constants.QINIU_URL + record.getEmp_cover_seller());
                    }
                }
                if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_pic())){
                    //处理图片URL链接
                    StringBuffer buffer = new StringBuffer();
                    String[] pics = new String[]{};
                    if(record!=null && record.getCloud_caoping_pic()!=null){
                        pics = record.getCloud_caoping_pic().split(",");
                    }
                    for (int i=0; i<pics.length; i++){
                        if (pics[i].startsWith("upload")) {
                            buffer.append(Constants.URL + pics[i]);
                            if (i < pics.length - 1) {
                                buffer.append(",");
                            }
                        }else {
                            buffer.append(Constants.QINIU_URL + pics[i]);
                            if (i < pics.length - 1) {
                                buffer.append(",");
                            }
                        }
                    }
                    record.setCloud_caoping_pic(buffer.toString());
                }

            }
            return list;
        }
        if("2".equals(query.getEmptype())){
            //商家查询自己管理的订单
            List<OrdersVO> list = appOrderMakeSaveDao.listOrdersSj(map);
            for (OrdersVO record : list){
                if(!StringUtil.isNullOrEmpty(record.getCreate_time())){
                    record.setCreate_time(RelativeDateFormat.format(Long.parseLong(record.getCreate_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getPay_time())){
                    record.setPay_time(RelativeDateFormat.format(Long.parseLong(record.getPay_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getAccept_time())){
                    record.setAccept_time(RelativeDateFormat.format(Long.parseLong(record.getAccept_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getSend_time())){
                    record.setSend_time(RelativeDateFormat.format(Long.parseLong(record.getSend_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getCompletion_time())){
                    record.setCompletion_time(RelativeDateFormat.format(Long.parseLong(record.getCompletion_time())));
                }
                if(!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                    if (record.getEmp_cover().startsWith("upload")) {
                        record.setEmp_cover(Constants.URL + record.getEmp_cover());
                    }else {
                        record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                    }
                }

                if(!StringUtil.isNullOrEmpty(record.getEmp_cover_seller())){
                    if (record.getEmp_cover_seller().startsWith("upload")) {
                        record.setEmp_cover_seller(Constants.URL + record.getEmp_cover_seller());
                    }else {
                        record.setEmp_cover_seller(Constants.QINIU_URL + record.getEmp_cover_seller());
                    }
                }
                if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_pic())){
                    //处理图片URL链接
                    StringBuffer buffer = new StringBuffer();
                    String[] pics = new String[]{};
                    if(record!=null && record.getCloud_caoping_pic()!=null){
                        pics = record.getCloud_caoping_pic().split(",");
                    }
                    for (int i=0; i<pics.length; i++){
                        if (pics[i].startsWith("upload")) {
                            buffer.append(Constants.URL + pics[i]);
                            if (i < pics.length - 1) {
                                buffer.append(",");
                            }
                        }else {
                            buffer.append(Constants.QINIU_URL + pics[i]);
                            if (i < pics.length - 1) {
                                buffer.append(",");
                            }
                        }
                    }
                    record.setCloud_caoping_pic(buffer.toString());
                }

            }
            return list;
        }
        return null;
    }


    private void pushMsg(final String pushId, final String type, final String content){
        CommonUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                ChannelKeyPair pair = null;
                if (type.equals("3")) {
                    pair = new ChannelKeyPair(Constants.API_KEY, Constants.SECRET_KEY);
                } else {
                    pair = new ChannelKeyPair(Constants.IOS_API_KEY, Constants.IOS_SECRET_KEY);
                }

                // 2. 创建BaiduChannelClient对象实例
                BaiduChannelClient channelClient = new BaiduChannelClient(pair);
                // 3. 若要了解交互细节，请注册YunLogHandler类
                channelClient.setChannelLogHandler(new YunLogHandler() {
                    @Override
                    public void onHandle(YunLogEvent event) {
//                        System.out.println(event.getMessage());
                    }
                });
                try {
                    // 4. 创建请求类对象
                    // 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
                    PushUnicastMessageRequest request = new PushUnicastMessageRequest();
                    request.setDeviceType(Integer.parseInt(type));
                    if (type.equals("4")) {//如果是苹果手机端要设置这个
                        request.setDeployStatus(Constants.IOS_TYPE);
                    }
//            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android  4:ios 5:wp
//            request.setChannelId(Long.parseLong(pushId));
//            request.setChannelId(5100663888284228047L);
                    request.setUserId(pushId);

                    request.setMessageType(1);
                    request.setMessage("{\"title\":\"提醒\",\"description\":\"" + content + "\",\"custom_content\": {\"type\":\"2\"}}");

//                    logger.info("开始调用百度推送接口");
                    // 5. 调用pushMessage接口
                    PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

//                    logger.info("推送成功----"+response.getSuccessAmount());
                    // 6. 认证推送成功
//                    System.out.println("push amount : " + response.getSuccessAmount());

                } catch (ChannelClientException e) {
                    // 处理客户端错误异常
                    e.printStackTrace();
//                    logger.info("处理客户端异常"+e.getMessage());
                } catch (ChannelServerException e) {
                    // 处理服务端错误异常
//                    System.out.println(String.format(
//                            "request_id: %d, error_code: %d, error_message: %s",
//                            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
                }
            }
        });

    }

    @Override
    public Object findById(Object object) throws ServiceException {
        String order_no = (String) object;
        OrdersVO record = appOrderMakeSaveDao.findOrderByOrderNo(order_no);

        if(!StringUtil.isNullOrEmpty(record.getCreate_time())){
            record.setCreate_time(RelativeDateFormat.format(Long.parseLong(record.getCreate_time())));
        }
        if(!StringUtil.isNullOrEmpty(record.getPay_time())){
            record.setPay_time(RelativeDateFormat.format(Long.parseLong(record.getPay_time())));
        }
        if(!StringUtil.isNullOrEmpty(record.getAccept_time())){
            record.setAccept_time(RelativeDateFormat.format(Long.parseLong(record.getAccept_time())));
        }
        if(!StringUtil.isNullOrEmpty(record.getSend_time())){
            record.setSend_time(RelativeDateFormat.format(Long.parseLong(record.getSend_time())));
        }
        if(!StringUtil.isNullOrEmpty(record.getCompletion_time())){
            record.setCompletion_time(RelativeDateFormat.format(Long.parseLong(record.getCompletion_time())));
        }

        if(!StringUtil.isNullOrEmpty(record.getEmp_cover())){
            if (record.getEmp_cover().startsWith("upload")) {
                record.setEmp_cover(Constants.URL + record.getEmp_cover());
            }else {
                record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
            }
        }

        if(!StringUtil.isNullOrEmpty(record.getEmp_cover_seller())){
            if (record.getEmp_cover_seller().startsWith("upload")) {
                record.setEmp_cover_seller(Constants.URL + record.getEmp_cover_seller());
            }else {
                record.setEmp_cover_seller(Constants.QINIU_URL + record.getEmp_cover_seller());
            }
        }

        if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_pic())){
            //处理图片URL链接
            StringBuffer buffer = new StringBuffer();
            String[] pics = new String[]{};
            if(record!=null && record.getCloud_caoping_pic()!=null){
                pics = record.getCloud_caoping_pic().split(",");
            }
            for (int i=0; i<pics.length; i++){
                if (pics[i].startsWith("upload")) {
                    buffer.append(Constants.URL + pics[i]);
                    if (i < pics.length - 1) {
                        buffer.append(",");
                    }
                }else {
                    buffer.append(Constants.QINIU_URL + pics[i]);
                    if (i < pics.length - 1) {
                        buffer.append(",");
                    }
                }
            }
            record.setCloud_caoping_pic(buffer.toString());
        }
        return record;
    }
}
