package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/8/17.
 * 生成定向卡订单
 */
@Controller
public class AppOrderDxkController extends ControllerConstants {

    @Autowired
    @Qualifier("orderDxkService")
    private SaveService orderDxkServiceSave;

    /**
     *  生成定向卡订单
     * @return
     */
    @RequestMapping(value = "/appSaveDxkOrder", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveDxkOrder(Order order){
        if(StringUtil.isNullOrEmpty(order.getEmp_id())){
            return toJSONString(new ErrorTip(1, "会员ID为空，请稍后重试！"));
        }
        if(StringUtil.isNullOrEmpty(order.getSeller_emp_id())){
            return toJSONString(new ErrorTip(1, "卖家会员ID为空，请稍后重试！"));
        }
        try {
            orderDxkServiceSave.save(order);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            if (e.getMessage().equals("has_exist")){
                return toJSONString(new ErrorTip(1, "已经存在了！"));
            }
            return toJSONString(new ErrorTip(1, "请稍后重试！"));
        }
    }

}
