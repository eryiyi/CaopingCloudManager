package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.dao.CompanyDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/17.
 * 公司查询
 */
@Service("appCompanyService")
public class AppCompanyService implements ExecuteService ,SaveService,UpdateService{
    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

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
