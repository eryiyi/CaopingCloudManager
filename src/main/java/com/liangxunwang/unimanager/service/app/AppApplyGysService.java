package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.ApplyGysDao;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appApplyGysService")
public class AppApplyGysService implements ExecuteService {
    @Autowired
    @Qualifier("applyGysDao")
    private ApplyGysDao applyGysDao;


    @Override
    public Object execute(Object object) {
        return applyGysDao.findByEmpId((String) object);
    }


}
