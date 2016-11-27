package com.liangxunwang.unimanager.mvc.app;

import com.google.gson.Gson;
import com.liangxunwang.unimanager.data.OrdersDATA;
import com.liangxunwang.unimanager.model.MineStore;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.model.WxPayObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/13.
 */
@Controller
public class AppOrderMakeController extends ControllerConstants {

    @Autowired
    @Qualifier("appOrderMakeService")
    private SaveService appOrderMakeService;
    @Autowired
    @Qualifier("appOrderMakeWxService")
    private SaveService appOrderMakeWxService;

    @Autowired
    @Qualifier("appOrderWxService")
    private SaveService appOrderWxServiceSave;

    @Autowired
    @Qualifier("appOrderMakeService")
    private UpdateService appOrderUpdateService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private ListService appOrderListService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private FindService appOrderFindService;

    @Autowired
    @Qualifier("appOrderService")
    private UpdateService UpateappOrderService;

    @Autowired
    @Qualifier("appOrderService")
    private SaveService SaveappOrderService;
    @Autowired
    @Qualifier("appOrderMakeLqService")
    private SaveService appOrderMakeLqService;

    @Autowired
    @Qualifier("appOrderService")
    private ExecuteService executeOrderService;

    /**
     * 订单接收---形成订单
     * @param list
     * @return
     */
    @RequestMapping("/orderSave")
    @ResponseBody
    public String orderSave(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderMakeService.save(data.getList());
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单生成失败，请稍后重试！"));
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(new ErrorTip(1, "订单生成失败，商品库存不多了！"));
            }
        }
        return null;
    }

    /**
     * 订单接收--补款--单个订单 -支付宝
     * @return
     */
    @RequestMapping("/orderSaveSingle")
    @ResponseBody
    public String orderSaveSingle(String order_no, String doublePrices){
        if(StringUtil.isNullOrEmpty(order_no)){
            return toJSONString(new ErrorTip(1, "订单支付失败，订单号不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(doublePrices)){
            return toJSONString(new ErrorTip(1, "订单支付失败，订单金额不能为空！"));
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) SaveappOrderService.save(map);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单支付失败，请稍后重试！"));
            }
        }
        return null;
    }

    /**
     * 订单接收---形成订单
     * @param list
     * @return
     */
    @RequestMapping("/orderSaveWx")
    @ResponseBody
    public String orderSaveWx(String list){
        if(StringUtil.isNullOrEmpty(list)){
            return toJSONString(new ErrorTip(1, "订单不能为空，请检查！"));
        }
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        if(data == null){
            return toJSONString(new ErrorTip(1, "订单生成失败，数据异常，请检查！"));
        }
        if(data.getList() == null){
            return toJSONString(new ErrorTip(1, "订单生成失败，数据异常，请检查！"));
        }
        try {
            Object[] strs = (Object[]) appOrderMakeWxService.save(data.getList());
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单生成失败，请稍后重试！"));
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(new ErrorTip(1, "订单生成失败，商品库存不多了！"));
            }
        }
        return null;
    }

    /**
     * 订单接收--补款--单个订单 微信
     * @return
     */
    @RequestMapping("/orderSaveSingleWx")
    @ResponseBody
    public String orderSaveSingleWx(String order_no, String doublePrices){
        if(StringUtil.isNullOrEmpty(order_no)){
            return toJSONString(new ErrorTip(1, "订单生成失败，订单号不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(doublePrices)){
            return toJSONString(new ErrorTip(1, "订单生成失败，订单金额不能为空！"));
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            Object[] strs = (Object[]) appOrderWxServiceSave.save(map);
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单生成失败，请稍后重试！"));
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(new ErrorTip(1, "订单支付失败，商品库存不多了！"));
            }
        }
        return null;
    }


    /**
     * 订单接收---形成订单--零钱支付
     * @param list
     * @return
     */
    @RequestMapping("/orderSaveLq")
    @ResponseBody
    public String orderSaveLq(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderMakeLqService.save(data.getList());
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单生成失败，请稍后重试！"));
            }else
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(new ErrorTip(1, "订单生成失败，商品库存不足！"));
            }
            else
            if (e.getMessage().equals("money_is_null")){
                return toJSONString(new ErrorTip(1, "订单生成失败，零钱余额不足！"));
            }
        }
        return null;
    }


    @Autowired
    @Qualifier("appOrderSingleLqService")
    private SaveService appOrderSingleLqServiceSave;

    /**
     * 订单接收--补款--单个订单 -零钱
     * @return
     */
    @RequestMapping("/orderSaveSingleLq")
    @ResponseBody
    public String orderSaveSingleLq(String order_no, String doublePrices){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderSingleLqServiceSave.save(map);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(new ErrorTip(1, "订单支付失败，请稍后重试！"));
            }else
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(new ErrorTip(1, "订单支付失败，商品库存不足！"));
            }
            else
            if (e.getMessage().equals("money_is_null")){
                return toJSONString(new ErrorTip(1, "订单支付失败，零钱余额不足！"));
            }
        }
        return null;
    }


    /**
     * 订单更新，支付订单成功
     * @param order
     * @return
     */
    @RequestMapping("/orderUpdate")
    @ResponseBody
    public String orderUpdate(Order order){
        appOrderUpdateService.update(order);
        return toJSONString(SUCCESS);
    }

    /**
     * 查询订单列表--会员查询
     * */
    @RequestMapping(value = "/listOrders", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listGoodsByType(OrdersQuery query, Page page){
        try {
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            List<OrdersVO> list = (List<OrdersVO>) appOrderListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "查询失败，请稍后重试！"));
        }
    }

    /**
     * 查询订单列表--商家查询自己的订单
     * */
    @RequestMapping(value = "/listOrdersMng", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listOrdersMng(OrdersQuery query, Page page){
        try {
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            List<OrdersVO> list = (List<OrdersVO>) appOrderListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "查询失败，请稍后重试！"));
        }
    }

    //取消订单  确认收货  更改订单状态
    @RequestMapping("/updateOrder")
    @ResponseBody
    public String cancleOrder(String order_no ,String status, String returnMsg, String returnOrder){
        if(StringUtil.isNullOrEmpty(order_no)){
            return toJSONString(new ErrorTip(1, "订单号不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(status)){
            return toJSONString(new ErrorTip(1, "请选择订单状态！"));
        }
        if("7".equals(status)){
            if(StringUtil.isNullOrEmpty(returnOrder)){
                return toJSONString(new ErrorTip(1, "请确认退货订单号！"));
            }
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no",order_no);
        map.put("status",status);
        if("7".equals(status)){
            map.put("returnMsg",returnMsg);
            map.put("returnOrder",returnOrder);
        }
        UpateappOrderService.update(map);
        return toJSONString(SUCCESS);
    }

    /**
     * 订单更新--更新单一订单
     * @param order_no  单一订单号  注意 不是主订单号  是order表自己的订单号
     * @return
     */
    @RequestMapping("/orderUpdateSingle")
    @ResponseBody
    public String orderUpdateSingle(String order_no){
        appOrderUpdateService.update(order_no);
        return toJSONString(SUCCESS);
    }

    /**
     * 查询订单数量
     * time_status 0 今天  1代表总的  2查询今天的收入
     * */
    @RequestMapping("/selectOrderNum")
    @ResponseBody
    public String selectOrderNum(String empId) throws Exception {
        if(StringUtil.isNullOrEmpty(empId)){
            return toJSONString(new ErrorTip(1, "会员ID不能为空！"));
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("emp_id",empId);
        map.put("time_status", "0");
        //今天的数量
        String numberDay = (String) executeOrderService.execute(map);
        map.put("time_status", "1");
        //总的数量
        String numberAll = (String) executeOrderService.execute(map);
        //查询今天的收入
        map.put("time_status", "2");
        String pricesAllDay = (String) executeOrderService.execute(map);
        //查询总的收入
        map.put("time_status", "3");
        String pricesAll = (String) executeOrderService.execute(map);

        MineStore mineStore = new MineStore();
        mineStore.setNumberAll(numberAll);
        mineStore.setNumberDay(numberDay);
        mineStore.setPricesAllDay(Float.valueOf(pricesAllDay==null?"0.0":pricesAllDay));
        mineStore.setPricesAll(Float.valueOf(pricesAll==null?"0.0":pricesAll));
        DataTip tip = new DataTip();
        tip.setData(mineStore);
        return toJSONString(tip);
    }



    //根据订单号查询订单状态
    @RequestMapping(value = "/findOrderByOrderNo", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String findOrderByOrderNo(String orderNo){
        try {
            OrdersVO order = (OrdersVO) appOrderFindService.findById(orderNo);
            DataTip tip = new DataTip();
            tip.setData(order);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "查询失败，请稍后重试！"));
        }
    }




    @Autowired
    @Qualifier("appOrderCancelService")
    private ExecuteService appOrderCancelService;
    // 卖家扫一扫订单
    @RequestMapping("/scanOrder")
    public String scanOrder(String order_no ,String status, ModelMap map1) throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no",order_no);
        map.put("status",status);
        UpateappOrderService.update(map);
        //根据订单号，查询订单详情
        OrdersVO orderVo = (OrdersVO) appOrderCancelService.execute(order_no);
        map1.put("vo", orderVo);
        return "/order/detailOrder";
    }

    @Autowired
    @Qualifier("appOrderCancelService")
    private UpdateService appOrderCancelServiceUpdate;
    /**
     * 订单更新，已经评价了
     * @return
     */
    @RequestMapping("/orderUpdateComment")
    @ResponseBody
    public String orderUpdateComment(String order_no){
        appOrderCancelServiceUpdate.update(order_no);
        return toJSONString(SUCCESS);
    }

}
