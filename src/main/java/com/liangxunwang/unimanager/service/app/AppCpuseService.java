package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CpuseDao;
import com.liangxunwang.unimanager.model.Cpuse;
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
@Service("appCpuseService")
public class AppCpuseService implements ExecuteService ,ListService{
    @Autowired
    @Qualifier("cpuseDao")
    private CpuseDao cpuseDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cpuse> lists = cpuseDao.lists(map);
        if(lists != null){
            for(Cpuse company:lists){
                if (!StringUtil.isNullOrEmpty(company.getCloud_caoping_use_pic())){
                    if (company.getCloud_caoping_use_pic().startsWith("upload")){
                        company.setCloud_caoping_use_pic(Constants.URL + company.getCloud_caoping_use_pic());
                    }else {
                        company.setCloud_caoping_use_pic(Constants.QINIU_URL + company.getCloud_caoping_use_pic());
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        Cpuse company= cpuseDao.findById((String) object);
        if (!StringUtil.isNullOrEmpty(company.getCloud_caoping_use_pic())){
            if (company.getCloud_caoping_use_pic().startsWith("upload")){
                company.setCloud_caoping_use_pic(Constants.URL + company.getCloud_caoping_use_pic());
            }else {
                company.setCloud_caoping_use_pic(Constants.QINIU_URL + company.getCloud_caoping_use_pic());
            }
        }
        return company;
    }


}
