package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2015/8/19.
 */
@Controller("orderController")
@RequestMapping("/order")
public class OrderController extends ControllerConstants {

    @Autowired
    @Qualifier("orderService")
    private ListService orderListService;

    @Autowired
    @Qualifier("orderService")
    private FindService orderFindService;

    @Autowired
    @Qualifier("orderService")
    private UpdateService orderUpdateService;

    /**
     * 后台查询我的订单列表
     * @param session
     * @param map
     * @param query
     * @param page
     * @return
     */
    @RequestMapping("list")
    public String list(HttpSession session, ModelMap map, OrdersQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) orderListService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/order/list";
    }

    /**
     * 根据订单号查询该订单的详情
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public String detail(String id, ModelMap map){
        OrdersVO vo = (OrdersVO) orderFindService.findById(id);
        map.put("vo", vo);
        return "/order/detail";
    }

    public String update(){
        orderUpdateService.update(null);
        return null;
    }


    @Autowired
    @Qualifier("orderReturnService")
    private UpdateService orderReturnServiceUpdate;
    //确认退款
    @RequestMapping("/updateOrderReturn")
    @ResponseBody
    public String updateOrderReturn(String order_no){
        //根据订单order_no查询订单详情
        OrdersVO vo = (OrdersVO) orderFindService.findById(order_no);
        orderReturnServiceUpdate.update(vo);
        return toJSONString(SUCCESS);
    }

}
