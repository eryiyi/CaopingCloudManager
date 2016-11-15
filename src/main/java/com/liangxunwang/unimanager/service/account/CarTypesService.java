package com.liangxunwang.unimanager.service.account;
import com.liangxunwang.unimanager.dao.CarTypeDao;
import com.liangxunwang.unimanager.model.CarType;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2016/11/15.
 */
public class CarTypesService  implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("carTypeDao")
    private CarTypeDao carTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        String is_use = (String) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(is_use)){
            map.put("is_use" ,is_use );
        }
        List<CarType> lists = carTypeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CarType adObj = (CarType) object;
        adObj.setCar_type_id(UUIDFactory.random());
        carTypeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String car_type_id = (String) object;
        carTypeDao.delete(car_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return carTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CarType adObj = (CarType) object;
        carTypeDao.update(adObj);
        return null;
    }
}
