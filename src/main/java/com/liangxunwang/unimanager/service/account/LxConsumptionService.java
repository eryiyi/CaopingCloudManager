package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.LxConsumptionDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.LxConsumption;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
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
 * Created by zhl on 2015/3/3.
 */
@Service("lxConsumptionService")
public class LxConsumptionService implements ListService,SaveService,ExecuteService {
    @Autowired
    @Qualifier("lxConsumptionDao")
    private LxConsumptionDao lxConsumptionDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LxConsumptionQuery query = (LxConsumptionQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getPhoneNumber())) {
            map.put("phoneNumber", query.getPhoneNumber());
        }
        if (!StringUtil.isNullOrEmpty(query.getKeyWords())) {
            map.put("keyWords", query.getKeyWords());
        }
        if (!StringUtil.isNullOrEmpty(query.getEmp_id())) {
            map.put("emp_id", query.getEmp_id());
        }
        List<LxConsumption> lists = lxConsumptionDao.lists(map);
        long count = lxConsumptionDao.count(map);
        if(lists != null){
            for(int i=0;i<lists.size();i++){
                LxConsumption vo = lists.get(i);
                if(!StringUtil.isNullOrEmpty(vo.getDateline())){
                    vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
                }
            }
        }
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        LxConsumption adObj = (LxConsumption) object;
        adObj.setLx_consumption_id(UUIDFactory.random());
        adObj.setDateline(System.currentTimeMillis()+"");
        lxConsumptionDao.save(adObj);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return lxConsumptionDao.findById((String) object);
    }

}
