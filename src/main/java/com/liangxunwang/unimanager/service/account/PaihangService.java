package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("paihangService")
public class PaihangService implements ListService,DeleteService,ExecuteService,UpdateService,SaveService {
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getIs_del())) {
            map.put("is_del", query.getIs_del());
        }
        if (!StringUtil.isNullOrEmpty(query.getKeyWords())) {
            map.put("keyWords", query.getKeyWords());
        }
        List<PaihangObjVO> lists = paihangObjDao.lists(map);
        for (PaihangObjVO record : lists){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getEnd_time())){
                record.setEnd_time(DateUtil.getDate(record.getEnd_time(), "yyyy-MM-dd"));
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
        }
        long count = paihangObjDao.count(map);
        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_paihang_id = (String) object;
        paihangObjDao.deleteById(mm_paihang_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_paihang_id())){
            map.put("mm_paihang_id",query.getMm_paihang_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords",query.getKeyWords());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del",query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getCloud_caoping_id())){
            map.put("cloud_caoping_id",query.getCloud_caoping_id());
        }
        PaihangObjVO paihangObj = paihangObjDao.findById(map);
        if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
            paihangObj.setEnd_time(DateUtil.getDate(paihangObj.getEnd_time(), "yyyy-MM-dd"));
        }
        return paihangObj;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof PaihangObj){
            PaihangObj paihangObj = (PaihangObj) object;
            if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
                paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
            }
            paihangObjDao.updateTop(paihangObj);
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        PaihangObj paihangObj = (PaihangObj) object;
        //先查询是否存在该商品了
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(paihangObj.getCloud_caoping_id())){
            map.put("cloud_caoping_id", paihangObj.getCloud_caoping_id());
        }
        PaihangObjVO paihangObjVO = paihangObjDao.findByGoodsId(map);
        if(paihangObjVO != null && !StringUtil.isNullOrEmpty(paihangObjVO.getMm_paihang_id())){
            //该商品已经添加
            throw new ServiceException("Has_exist");
        }
        paihangObj.setMm_paihang_id(UUIDFactory.random());
        if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
            paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
        }
        paihangObjDao.save(paihangObj);
        return 200;
    }
}
