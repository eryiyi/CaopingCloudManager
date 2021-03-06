package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CpObjDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.CpObj;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
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
@Service("cpobjService")
public class CpobjService implements ListService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cpObjDao")
    private CpObjDao cpObjDao;
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CpObjQuery query = (CpObjQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caoping_guige_id())){
            map.put("cloud_caoping_guige_id", query.getCloud_caoping_guige_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caoping_use_id())){
            map.put("cloud_caoping_use_id", query.getCloud_caoping_use_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caoping_type_id())){
            map.put("cloud_caoping_type_id", query.getCloud_caoping_type_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_is_use())){
            map.put("cloud_is_use", query.getCloud_is_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_is_del())){
            map.put("cloud_is_del", query.getCloud_is_del());
        }

        if(!StringUtil.isNullOrEmpty(query.getCloud_jixie_guige_id())){
            map.put("cloud_jixie_guige_id", query.getCloud_jixie_guige_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_jixie_use_id())){
            map.put("cloud_jixie_use_id", query.getCloud_jixie_use_id());
        }

        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_guige_id())){
            map.put("cloud_caozhong_guige_id", query.getCloud_caozhong_guige_id());
        }

        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_type_id())){
            map.put("cloud_caozhong_type_id", query.getCloud_caozhong_type_id());
        }

        if(!StringUtil.isNullOrEmpty(query.getLat())){
            map.put("lat", query.getLat());
        }
        if(!StringUtil.isNullOrEmpty(query.getLng())){
            map.put("lng", query.getLng());
        }if(!StringUtil.isNullOrEmpty(query.getIs_time())){
            map.put("is_time", query.getIs_time());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_count())){
            map.put("is_count", query.getIs_count());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_type())){
            map.put("is_type", query.getIs_type());
        }
        List<CpObj> lists = cpObjDao.lists(map);
        long count = cpObjDao.count(map);
        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caoping_id = (String) object;
        cpObjDao.delete(cloud_caoping_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpObjDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CpObj adObj = (CpObj) object;
        cpObjDao.update(adObj);
        return null;
    }
}
