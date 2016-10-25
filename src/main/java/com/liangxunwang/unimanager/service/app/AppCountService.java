package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appCountService")
public class AppCountService implements FindService {
    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;


    @Override
    public Object findById(Object object) throws ServiceException {
        return countDao.findById((String) object);
    }
}
