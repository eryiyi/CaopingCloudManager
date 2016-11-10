package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.dao.CompanyDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
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
            }
        }
        return company;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Company company = (Company) object;
        company.setCompany_id(UUIDFactory.random());
        company.setDateline(System.currentTimeMillis() + "");
        company.setIs_check("1");
        companyDao.save(company);
        return 200;
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
        }else {
            //更新内容
            companyDao.update(company);
        }
        return 200;
    }
}
