package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpJixieUseDao;
import com.liangxunwang.unimanager.model.CpJixieuse;
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
@Service("cpJixieUseService")
public class CpJixieUseService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpJixieUseDao")
    private CpJixieUseDao cpJixieUseDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CpJixieuse> lists = cpJixieUseDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CpJixieuse adObj = (CpJixieuse) object;
        adObj.setCloud_jixie_use_id(UUIDFactory.random());
        cpJixieUseDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_jixie_use_id = (String) object;
        cpJixieUseDao.delete(cloud_jixie_use_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpJixieUseDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CpJixieuse adObj = (CpJixieuse) object;
        cpJixieUseDao.update(adObj);
        return null;
    }
}
