package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.TransportDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.Transport;
import com.liangxunwang.unimanager.query.TransportQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
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
@Service("appTransportService")
public class AppTransportService implements ListService,SaveService,ExecuteService {
    @Autowired
    @Qualifier("transportDao")
    private TransportDao transportDao;
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object list(Object object) throws ServiceException {
        TransportQuery query = (TransportQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
//        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", "0");
//        }
        if(!StringUtil.isNullOrEmpty(query.getLat())){
            map.put("lat", query.getLat());
        }
        if(!StringUtil.isNullOrEmpty(query.getLng())){
            map.put("lng", query.getLng());
        }

        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_type())){
            map.put("is_type", query.getIs_type());
        }
        if(!StringUtil.isNullOrEmpty(query.getAreaidStart())){
            map.put("areaidStart", query.getAreaidStart());
        }
        if(!StringUtil.isNullOrEmpty(query.getAreaidEnd())){
            map.put("areaidEnd", query.getAreaidEnd());
        }
        List<Transport> lists = transportDao.lists(map);
        for (Transport record : lists){
            if(!StringUtil.isNullOrEmpty(record.getCar_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getCar_pic()!=null){
                    pics = record.getCar_pic().split(",");
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
                record.setCar_pic(buffer.toString());
            }
            record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
        }
//        long count = memberDao.count(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Transport adObj = (Transport) object;
        //查询用户member对象
        Member member = memberDao.findById(adObj.getEmp_id());
        if(member != null){
            if(!"1".equals(member.getIs_gys())){
                //说明不是供应商
                throw new ServiceException("not_has_power");
            }
        }
        adObj.setTransport_id(UUIDFactory.random());
        adObj.setDateline(System.currentTimeMillis() + "");
        transportDao.save(adObj);
        return null;
    }


    @Override
    public Object execute(Object object) throws ServiceException {
        return transportDao.findById((String) object);
    }


}
