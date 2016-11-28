package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CompanyDao;
import com.liangxunwang.unimanager.mvc.vo.CompanySort;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 * 获取店铺排序
 */
@Service("appCompanySortService")
public class AppCompanySortService implements ExecuteService{
    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

    @Override
    public Object execute(Object object) {
        String emp_id = (String) object;
        CompanySort companySort = companyDao.findSort(emp_id);
        return companySort;
    }
}
