package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CaozhongTypeDao;
import com.liangxunwang.unimanager.model.CaozhongType;
import com.liangxunwang.unimanager.service.*;
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
@Service("caozhongTypeService")
public class CaozhongTypeService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("caozhongTypeDao")
    private CaozhongTypeDao caozhongTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CaozhongType> lists = caozhongTypeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CaozhongType adObj = (CaozhongType) object;
        adObj.setCloud_caozhong_type_id(UUIDFactory.random());
        caozhongTypeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caozhong_type_id = (String) object;
        caozhongTypeDao.delete(cloud_caozhong_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return caozhongTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CaozhongType adObj = (CaozhongType) object;
        caozhongTypeDao.update(adObj);
        return null;
    }
}
