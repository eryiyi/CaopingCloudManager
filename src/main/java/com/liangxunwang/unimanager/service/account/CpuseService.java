package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpuseDao;
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
@Service("cpuseService")
public class CpuseService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpuseDao")
    private CpuseDao cpuseDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cpuse> lists = cpuseDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Cpuse adObj = (Cpuse) object;
        adObj.setCloud_caoping_use_id(UUIDFactory.random());
        cpuseDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caoping_use_id = (String) object;
        cpuseDao.delete(cloud_caoping_use_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpuseDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        Cpuse adObj = (Cpuse) object;
        cpuseDao.update(adObj);
        return null;
    }
}
