package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.ApplyGysDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.ApplyGys;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.ApplyGysQuery;
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
@Service("applyGysService")
public class ApplyGysService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("applyGysDao")
    private ApplyGysDao applyGysDao;

    @Override
    public Object list(Object object) throws ServiceException {
        ApplyGysQuery query = (ApplyGysQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_check())){
            map.put("is_check", query.getIs_check());
        }
        List<ApplyGys> lists = applyGysDao.lists(map);

//        if(lists != null){
//            for(ApplyGys adObj:lists){
//                if(!StringUtil.isNullOrEmpty(adObj.getMm_ad_pic())){
//                    if(adObj.getMm_ad_pic().startsWith("upload")){
//                        adObj.setMm_ad_pic(Constants.URL + adObj.getMm_ad_pic());
//                    }else {
//                        adObj.setMm_ad_pic(Constants.QINIU_URL + adObj.getMm_ad_pic());
//                    }
//                }
//            }
//        }
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        ApplyGys adObj = (ApplyGys) object;
        if(StringUtil.isNullOrEmpty(adObj.getEmp_id())){
            throw new ServiceException("no_emp");
        }
        //根据emp_id查询是否申请过了
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("emp_id", adObj.getEmp_id());
        List<ApplyGys> lists = applyGysDao.lists(map);
        if(lists != null && lists.size()>0){
            throw new ServiceException("has_exist");
        }
        adObj.setApply_gys_id(UUIDFactory.random());
        adObj.setDateline_apply(System.currentTimeMillis() + "");
        adObj.setIs_check("0");
        applyGysDao.save(adObj);
        return 200;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String apply_gys_id = (String) object;
        applyGysDao.delete(apply_gys_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return applyGysDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        ApplyGys adObj = (ApplyGys) object;
        applyGysDao.update(adObj);
        return null;
    }
}
