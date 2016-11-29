package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.model.StatisticalObj;
import com.liangxunwang.unimanager.mvc.vo.CompanySort;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appStatisticalService")
public class AppStatisticalService implements ExecuteService {
    @Autowired
    @Qualifier("cpObjDao")
    private CpObjDao cpObjDao;

    @Autowired
    @Qualifier("transportDao")
    private TransportDao transportDao;

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("dianpuFavourDao")
    private DianpuFavourDao dianpuFavourDao;

    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

    @Autowired
    @Qualifier("newsObjDao")
    private NewsObjDao newsObjDao;

    @Override
    public Object execute(Object object){
        String emp_id = (String) object;
        //发了多少草坪数据 机械数据 草种数据
        //发了多少物流数据
        //发了多少新闻
        //已完成多少订单
        //有多少人关注你
        //你的公司在名企排行中排第几
        StatisticalObj statisticalObj = new StatisticalObj();

        //草坪 草种 机械
        Map<String, Object> mapcp = new HashMap<String, Object>();
        mapcp.put("emp_id", emp_id);
        mapcp.put("cloud_is_del", "0");
        mapcp.put("is_type", "0");//0草坪 1草种 2机械
        Long lcp = cpObjDao.count(mapcp);
        if(lcp != null){
            statisticalObj.setCpNumber(String.valueOf(lcp));
        }else {
            statisticalObj.setCpNumber("0");
        }
        mapcp.put("is_type", "1");//0草坪 1草种 2机械
        Long lcz = cpObjDao.count(mapcp);
        if(lcz != null){
            statisticalObj.setCzNumber(String.valueOf(lcz));
        }else {
            statisticalObj.setCzNumber("0");
        }
        mapcp.put("is_type", "2");//0草坪 1草种 2机械
        Long ljx = cpObjDao.count(mapcp);
        if(ljx != null){
            statisticalObj.setJxNumber(String.valueOf(ljx));
        }else {
            statisticalObj.setJxNumber("0");
        }
        //物流
        Map<String, Object> mapWl = new HashMap<String, Object>();
        mapWl.put("emp_id", emp_id);
        mapWl.put("is_del", "0");
        Long lwl = transportDao.count(mapWl);
        if(lwl != null){
            statisticalObj.setWlNumber(String.valueOf(lwl));
        }else {
            statisticalObj.setWlNumber("0");
        }
        //新闻
        Map<String, Object> mapNews = new HashMap<String, Object>();
        mapNews.put("emp_id", emp_id);
        mapNews.put("is_del", "0" );
        Long lnews = newsObjDao.count(mapNews);
        if(lnews != null){
            statisticalObj.setNewsNumber(String.valueOf(lnews));
        }else {
            statisticalObj.setNewsNumber("0");
        }
        //订单数量
        Map<String, Object> mapOrder = new HashMap<String, Object>();
        mapOrder.put("emp_id", emp_id);
        mapOrder.put("status", "5");
        Long lOrder =orderDao.count(mapOrder);
        if(lOrder != null){
            statisticalObj.setOrderNumber(String.valueOf(lOrder));
        }else {
            statisticalObj.setOrderNumber("0");
        }
        //店铺关注
        Map<String, Object> mapGz = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(emp_id)){
            mapGz.put("emp_id", emp_id);
        }
        Long lGz = dianpuFavourDao.count(mapGz);
        if(lGz != null){
            statisticalObj.setFavourNumber(String.valueOf(lGz));
        }else {
            statisticalObj.setFavourNumber("0");
        }
        //店铺排序
        CompanySort companySort = companyDao.findSort(emp_id);
        if(companySort != null){
            statisticalObj.setMqNumber(companySort.getSort()==null?"0":companySort.getSort());
        }else {
            statisticalObj.setMqNumber("0");
        }
        return statisticalObj;
    }
}
