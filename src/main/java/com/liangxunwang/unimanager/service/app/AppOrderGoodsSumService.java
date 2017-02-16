package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appOrderGoodsSumService")
public class AppOrderGoodsSumService implements ExecuteService {
    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Override
    public Object execute(Object object) throws ServiceException, Exception {
        String[] arras = (String[]) object;
        String emp_id = arras[0];
        String seller_emp_id = arras[1];

        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(emp_id)){
            map.put("emp_id", emp_id);
        }else {
            map.put("emp_id", "");
        }
        if(!StringUtil.isNullOrEmpty(seller_emp_id)){
            map.put("seller_emp_id", seller_emp_id);
        }else {
            map.put("seller_emp_id", "");
        }
        String goodsSum= orderDao.countGoodsSum(map);

        return goodsSum;
    }
}
