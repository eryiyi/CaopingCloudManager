package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.Rate;
import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.query.SettlementQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成定向卡订单
 */
@Service("orderDxkService")
public class OrderDxkService implements  SaveService {

    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Order order = (Order) object;
        order.setOrder_no(UUIDFactory.random());
        order.setCreate_time(System.currentTimeMillis()+"");
        order.setPay_time(System.currentTimeMillis()+"");
        order.setStatus("2");
        order.setPay_status("1");
        order.setIs_dxk_order("1");
        appOrderMakeDao.saveDxkOrder(order);
        return null;
    }
}
