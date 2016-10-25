package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/30.
 */
@Service("appManagerInfoService")
public class AppManagerInfoService implements  FindService,ListService {

    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;
    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String emp_id = (String) object;
            ManagerInfoVo managerInfoVo =  managerInfoDao.getEmpMsg(emp_id);
            if(managerInfoVo!= null){
                if(!StringUtil.isNullOrEmpty(managerInfoVo.getEmp_cover())){
                    if (managerInfoVo.getEmp_cover().startsWith("upload")) {
                        managerInfoVo.setEmp_cover(Constants.URL + managerInfoVo.getEmp_cover());
                    }else {
                        managerInfoVo.setEmp_cover(Constants.QINIU_URL + managerInfoVo.getEmp_cover());
                    }
                }
                if(!StringUtil.isNullOrEmpty(managerInfoVo.getCompany_pic())){
                    if (managerInfoVo.getCompany_pic().startsWith("upload")) {
                        managerInfoVo.setCompany_pic(Constants.URL + managerInfoVo.getCompany_pic());
                    }else {
                        managerInfoVo.setCompany_pic(Constants.QINIU_URL + managerInfoVo.getCompany_pic());
                    }
                }

            }
            return managerInfoVo;
        }
        return null;
    }


    @Override
    public Object list(Object object) throws ServiceException {
        ManagerInfoVo managerInfoVo = (ManagerInfoVo) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(managerInfoVo.getLat_company())){
            map.put("lat_company", managerInfoVo.getLat_company());
        }
        if(!StringUtil.isNullOrEmpty(managerInfoVo.getLng_company())){
            map.put("lng_company", managerInfoVo.getLng_company());
        }
        List<ManagerInfoVo>  lists = managerInfoDao.listsLocation(map);
        for(ManagerInfoVo managerInfoVo1:lists){
            if(!StringUtil.isNullOrEmpty(managerInfoVo1.getCompany_pic())){
                if (managerInfoVo1.getCompany_pic().startsWith("upload")) {
                    managerInfoVo1.setCompany_pic(Constants.URL + managerInfoVo1.getCompany_pic());
                }else {
                    managerInfoVo1.setCompany_pic(Constants.QINIU_URL + managerInfoVo1.getCompany_pic());
                }
            }

           if(!StringUtil.isNullOrEmpty(managerInfoVo1.getEmp_cover())){
               if (managerInfoVo1.getEmp_cover().startsWith("upload")) {
                   managerInfoVo1.setEmp_cover(Constants.URL + managerInfoVo1.getEmp_cover());
               }else {
                   managerInfoVo1.setEmp_cover(Constants.QINIU_URL + managerInfoVo1.getEmp_cover());
               }
           }

        }
        return lists;
    }
}
