package com.liangxunwang.unimanager.service.app;

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
@Service("appCpobjService")
public class AppCpobjService implements ListService,SaveService,ExecuteService ,DeleteService{
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
        for (CpObj record : lists){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getCloud_caoping_pic()!=null){
                    pics = record.getCloud_caoping_pic().split(",");
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
                record.setCloud_caoping_pic(buffer.toString());
            }
            if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_dateline())){
                record.setCloud_caoping_dateline(RelativeDateFormat.format(Long.parseLong(record.getCloud_caoping_dateline())));
            }

        }
        long count = cpObjDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CpObj adObj = (CpObj) object;
        //查询用户member对象
        Member member = memberDao.findById(adObj.getEmp_id());
        if(member != null){
            if(!"1".equals(member.getIs_gys())){
                //说明不是供应商
                throw new ServiceException("not_has_power");
            }
        }
        adObj.setCloud_caoping_id(UUIDFactory.random());
        adObj.setCloud_caoping_dateline(System.currentTimeMillis() + "");
        adObj.setCloud_is_del("0");
        adObj.setCloud_is_use("0");
        cpObjDao.save(adObj);
        return null;
    }


    @Override
    public Object execute(Object object) {
        CpObj record = cpObjDao.findById((String) object);
        if(record != null){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getCloud_caoping_pic()!=null){
                    pics = record.getCloud_caoping_pic().split(",");
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
                record.setCloud_caoping_pic(buffer.toString());
            }
            if(!StringUtil.isNullOrEmpty(record.getCloud_caoping_dateline())){
                record.setCloud_caoping_dateline(RelativeDateFormat.format(Long.parseLong(record.getCloud_caoping_dateline())));
            }
        }
        return record;
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String cloud_caoping_id = (String) object;
        CpObj cpObj = new CpObj();
        cpObj.setCloud_caoping_id(cloud_caoping_id);
        cpObj.setCloud_is_del("1");
        cpObjDao.updateDelete(cpObj);
        return null;
    }
}
