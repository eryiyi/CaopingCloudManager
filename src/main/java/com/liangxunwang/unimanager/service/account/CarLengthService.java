package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CarLengthDao;

import com.liangxunwang.unimanager.model.CarLength;
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
 * Created by zhl on 2015/3/3.
 */
@Service("carLengthService")
public class CarLengthService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("carLengthDao")
    private CarLengthDao carLengthDao;

    @Override
    public Object list(Object object) throws ServiceException {
        String is_use = (String) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(is_use)){
            map.put("is_use" ,is_use );
        }
        List<CarLength> lists = carLengthDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CarLength adObj = (CarLength) object;
        adObj.setCar_length_id(UUIDFactory.random());
        carLengthDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String car_type_id = (String) object;
        carLengthDao.delete(car_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return carLengthDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CarLength adObj = (CarLength) object;
        carLengthDao.update(adObj);
        return null;
    }
}
