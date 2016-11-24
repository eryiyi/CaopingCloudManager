package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("indexService")
public class IndexService implements ListService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("cpObjDao")
    private CpObjDao cpObjDao;

    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;


    @Override
    public Object list(Object object) throws ServiceException {
        //----------------会员统计-------------------
        //总共会员数量
        long memberCount = memberDao.memberCount();
        //今天注册的会员
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("dateline", DateUtil.getDateAndTimeThree());
        long memberCountNoDay = memberDao.countDay(map1);
        //入驻的商家
        Map<String, Object> map2 = new HashMap<String, Object>();
        long countSj = companyDao.count(map2);
        //----------------商品统计----------------
        //全部产品
        Map<String, Object> map3 = new HashMap<String, Object>();
        long countGoodsAll = cpObjDao.count(map3);
        //草坪
        map3.put("is_type","0");
        long countGoods1 = cpObjDao.count(map3);
        //草种
        map3.put("is_type","1");
        long countGoods2 = cpObjDao.count(map3);
        //机械
        map3.put("is_type","2");
        long countGoods3 = cpObjDao.count(map3);

        //--------------订单统计------------------------
        //全部订单
        Map<String, Object> map4 = new HashMap<String, Object>();
        long countOrderAll = orderDao.count(map4);
        //已完成订单
        map4.put("status", "5");
        long countOrderDone = orderDao.count(map4);
        //今日订单统计
        map4.put("dateline",  DateUtil.getDateAndTimeThree());
        long countOrderDay = orderDao.countDay(map4);

        List<Long> list = new ArrayList<Long>();
        list.add(memberCount);
        list.add(memberCountNoDay);
        list.add(countSj);

        list.add(countGoodsAll);
        list.add(countGoods1);
        list.add(countGoods2);

        list.add(countOrderAll);
        list.add(countOrderDone);
        list.add(countOrderDay);

        list.add(countGoods3);
        return list;
    }
}
