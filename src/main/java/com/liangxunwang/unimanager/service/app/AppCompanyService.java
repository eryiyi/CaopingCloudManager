package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.dao.CompanyDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.query.CompanyQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 * 公司查询
 */
@Service("appCompanyService")
public class AppCompanyService implements ExecuteService ,SaveService,UpdateService,ListService{
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
        if(!StringUtil.isNullOrEmpty(query.getIs_gys())){
            map.put("is_gys", query.getIs_gys());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_paihang())){
            map.put("is_paihang", query.getIs_paihang());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_time())){
            map.put("is_time", query.getIs_time());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_count())){
            map.put("is_count", query.getIs_count());
        }
        List<Company> lists = companyDao.lists(map);
        if(lists != null){
            for(Company company : lists){
                if (!StringUtil.isNullOrEmpty(company.getCompany_pic())){
                    if (company.getCompany_pic().startsWith("upload")){
                        company.setCompany_pic(Constants.URL + company.getCompany_pic());
                    }else {
                        company.setCompany_pic(Constants.QINIU_URL + company.getCompany_pic());
                    }
                }
                if(!StringUtil.isNullOrEmpty(company.getEmp_cover())){
                    if (company.getEmp_cover().startsWith("upload")){
                        company.setEmp_cover(Constants.URL + company.getEmp_cover());
                    }else {
                        company.setEmp_cover(Constants.QINIU_URL + company.getEmp_cover());
                    }
                }
                if(!StringUtil.isNullOrEmpty(company.getDateline())){
                    company.setDateline(RelativeDateFormat.format(Long.parseLong(company.getDateline())));
                }

            }
        }
        long count = companyDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object execute(Object object){
        String emp_id = (String) object;
        Company company = companyDao.findByEmpId(emp_id);
        if(company != null){
            if (!StringUtil.isNullOrEmpty(company.getCompany_pic())){
                if (company.getCompany_pic().startsWith("upload")){
                    company.setCompany_pic(Constants.URL + company.getCompany_pic());
                }else {
                    company.setCompany_pic(Constants.QINIU_URL + company.getCompany_pic());
                }
            }
            if(!StringUtil.isNullOrEmpty(company.getEmp_cover())){
                if (company.getEmp_cover().startsWith("upload")){
                    company.setEmp_cover(Constants.URL + company.getEmp_cover());
                }else {
                    company.setEmp_cover(Constants.QINIU_URL + company.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(company.getDateline())){
                company.setDateline(RelativeDateFormat.format(Long.parseLong(company.getDateline())));
            }
        }
        return company;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Company company = (Company) object;
        company.setDateline(System.currentTimeMillis() + "");
        company.setIs_check("1");
        companyDao.save(company);
        return company;
    }

    @Override
    public Object update(Object object) {
        Company company = (Company) object;
        if(!StringUtil.isNullOrEmpty(company.getLat_company()) && !StringUtil.isNullOrEmpty(company.getLng_company())){
            //更新位置
            companyDao.updateLocation(company);
        }else if(!StringUtil.isNullOrEmpty(company.getIs_check())){
            //更新审核
            companyDao.updateCheck(company);
        }else if(!StringUtil.isNullOrEmpty(company.getCompany_pic())){
            //更新图片
            companyDao.updatePic(company);
        }else if(!StringUtil.isNullOrEmpty(company.getIs_paihang())){
            //名企排行
            if(!StringUtil.isNullOrEmpty(company.getEnd_time())){
                company.setEnd_time(DateUtil.getMs(company.getEnd_time(), "yyyy-MM-dd") + "");
            }
            companyDao.updateMq(company);
        }
        else {
            //更新内容
            companyDao.update(company);
        }
        return 200;
    }


}
