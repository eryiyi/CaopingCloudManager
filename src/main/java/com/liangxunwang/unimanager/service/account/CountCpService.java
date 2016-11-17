package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpObjDao;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 * 草原数量
 */
@Service("countCpService")
public class CountCpService implements ExecuteService {

    @Autowired
    @Qualifier("cpObjDao")
    private CpObjDao cpObjDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        String emp_id = (String) object;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("emp_id" , emp_id);
        return String.valueOf(cpObjDao.count(map));
    }

}
