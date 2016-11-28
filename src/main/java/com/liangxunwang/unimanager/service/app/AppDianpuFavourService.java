package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.DianpuFavourDao;
import com.liangxunwang.unimanager.dao.FavourDao;
import com.liangxunwang.unimanager.model.FavourObj;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.query.NewsFavourQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 * 获取关注店铺数量
 */
@Service("appDianpuFavourService")
public class AppDianpuFavourService implements ExecuteService{
    @Autowired
    @Qualifier("dianpuFavourDao")
    private DianpuFavourDao dianpuFavourDao;


    @Override
    public Object execute(Object object) throws ServiceException, Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        DianpuFavourQuery query = (DianpuFavourQuery) object;
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id_favour())){
            map.put("emp_id_favour", query.getEmp_id_favour());
        }
        Long l = dianpuFavourDao.count(map);
        return String.valueOf(l);
    }
}
