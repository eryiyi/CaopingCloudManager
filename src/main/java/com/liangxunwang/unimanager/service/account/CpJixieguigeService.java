package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpJxguigeDao;
import com.liangxunwang.unimanager.dao.CpguigeDao;
import com.liangxunwang.unimanager.model.CpJxguige;
import com.liangxunwang.unimanager.model.Cpguige;
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
@Service("cpJixieguigeService")
public class CpJixieguigeService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpJxguigeDao")
    private CpJxguigeDao cpJxguigeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CpJxguige> lists = cpJxguigeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CpJxguige adObj = (CpJxguige) object;
        adObj.setCloud_jixie_guige_id(UUIDFactory.random());
        cpJxguigeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_jixie_guige_id = (String) object;
        cpJxguigeDao.delete(cloud_jixie_guige_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpJxguigeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CpJxguige adObj = (CpJxguige) object;
        cpJxguigeDao.update(adObj);
        return null;
    }
}
