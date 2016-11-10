package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CptypeDao;
import com.liangxunwang.unimanager.dao.CpuseDao;
import com.liangxunwang.unimanager.model.Cptype;
import com.liangxunwang.unimanager.model.Cpuse;
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
@Service("cptypeService")
public class CptypeService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cptypeDao")
    private CptypeDao cptypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cptype> lists = cptypeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Cptype adObj = (Cptype) object;
        adObj.setCloud_caoping_type_id(UUIDFactory.random());
        cptypeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caoping_type_id = (String) object;
        cptypeDao.delete(cloud_caoping_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cptypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        Cptype adObj = (Cptype) object;
        cptypeDao.update(adObj);
        return null;
    }
}
