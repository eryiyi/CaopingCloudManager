package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CaozhongObjDao;
import com.liangxunwang.unimanager.model.CaozhongObj;
import com.liangxunwang.unimanager.query.CzObjQuery;
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
@Service("caozhongObjService")
public class CaozhongObjService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("caozhongObjDao")
    private CaozhongObjDao cpObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CzObjQuery query = (CzObjQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_guige_id())){
            map.put("cloud_caozhong_guige_id", query.getCloud_caozhong_guige_id());
        }

        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_type_id())){
            map.put("cloud_caozhong_type_id", query.getCloud_caozhong_type_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_is_use())){
            map.put("cloud_caozhong_is_use", query.getCloud_caozhong_is_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caozhong_is_del())){
            map.put("cloud_caozhong_is_del", query.getCloud_caozhong_is_del());
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
        List<CaozhongObj> lists = cpObjDao.lists(map);
        for (CaozhongObj record : lists){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getCloud_caozhong_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getCloud_caozhong_pic()!=null){
                    pics = record.getCloud_caozhong_pic().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                record.setCloud_caozhong_pic(buffer.toString());
            }
            record.setCloud_caozhong_dateline(RelativeDateFormat.format(Long.parseLong(record.getCloud_caozhong_dateline())));
        }
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CaozhongObj adObj = (CaozhongObj) object;
        adObj.setCloud_caozhong_id(UUIDFactory.random());
        adObj.setCloud_caozhong_dateline(System.currentTimeMillis() + "");
        adObj.setCloud_caozhong_is_del("0");
        adObj.setCloud_caozhong_is_use("0");
        cpObjDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caozhong_id = (String) object;
        cpObjDao.delete(cloud_caozhong_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return cpObjDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CaozhongObj adObj = (CaozhongObj) object;
        cpObjDao.update(adObj);
        return null;
    }
}