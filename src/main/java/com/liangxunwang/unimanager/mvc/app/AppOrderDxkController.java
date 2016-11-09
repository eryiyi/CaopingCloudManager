package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );//会员ID为空
        }
        if(StringUtil.isNullOrEmpty(order.getSeller_emp_id())){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );//卖家会员ID为空
        }
        try {
            orderDxkServiceSave.save(order);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

}
