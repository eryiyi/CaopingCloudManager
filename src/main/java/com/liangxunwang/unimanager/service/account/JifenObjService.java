package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.JifenObjDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.JifenObj;
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
@Service("jifenObjService")
public class JifenObjService implements ExecuteService, UpdateService {

    @Autowired
    @Qualifier("jifenObjDao")
    private JifenObjDao jifenObjDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        return jifenObjDao.list();
    }

    @Override
    public Object update(Object object) {
        JifenObj adObj = (JifenObj) object;
        jifenObjDao.update(adObj);
        return null;
    }
}
