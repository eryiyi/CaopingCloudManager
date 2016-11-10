package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.CpguigeDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Cpguige;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
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
@Service("cpguigeService")
public class CpguigeService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpguigeDao")
    private CpguigeDao cpguigeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cpguige> lists = cpguigeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Cpguige adObj = (Cpguige) object;
        adObj.setCloud_caoping_guige_id(UUIDFactory.random());
        cpguigeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caoping_guige_id = (String) object;
        cpguigeDao.delete(cloud_caoping_guige_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpguigeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        Cpguige adObj = (Cpguige) object;
        cpguigeDao.update(adObj);
        return null;
    }
}
