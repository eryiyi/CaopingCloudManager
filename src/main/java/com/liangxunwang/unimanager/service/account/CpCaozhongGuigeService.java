package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpCaozhongguigeDao;
import com.liangxunwang.unimanager.model.CpCaozhongguige;
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
@Service("cpCaozhongGuigeService")
public class CpCaozhongGuigeService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpCaozhongguigeDao")
    private CpCaozhongguigeDao cpCaozhongguigeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CpCaozhongguige> lists = cpCaozhongguigeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CpCaozhongguige adObj = (CpCaozhongguige) object;
        adObj.setCloud_caozhong_guige_id(UUIDFactory.random());
        cpCaozhongguigeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caozhong_guige_id = (String) object;
        cpCaozhongguigeDao.delete(cloud_caozhong_guige_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpCaozhongguigeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CpCaozhongguige adObj = (CpCaozhongguige) object;
        cpCaozhongguigeDao.update(adObj);
        return null;
    }
}
