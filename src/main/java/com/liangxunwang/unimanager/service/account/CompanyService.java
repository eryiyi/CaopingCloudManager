package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CompanyDao;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.query.CompanyQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 * 后台查询企业列表
 */
@Service("companyService")
public class CompanyService implements ListService {
    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CompanyQuery query = (CompanyQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_check())){
            map.put("is_check", query.getIs_check());
        }
        if(!StringUtil.isNullOrEmpty(query.getProvinceid())){
            map.put("provinceid", query.getProvinceid());
        }
        if(!StringUtil.isNullOrEmpty(query.getCityid())){
            map.put("cityid", query.getCityid());
        }
        if(!StringUtil.isNullOrEmpty(query.getAreaid())){
            map.put("areaid", query.getAreaid());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }
        List<Company> lists = companyDao.lists(map);
        if(lists != null){
            for(Company company : lists){
                if(!StringUtil.isNullOrEmpty(company.getDateline())){
                    company.setDateline(RelativeDateFormat.format(Long.parseLong(company.getDateline())));
                }
            }
        }
        long count = companyDao.count(map);
        return new Object[]{lists, count};
    }

}
