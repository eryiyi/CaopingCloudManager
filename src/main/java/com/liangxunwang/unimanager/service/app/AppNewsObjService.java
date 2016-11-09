package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.dao.NewsObjDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.model.NewsObj;
import com.liangxunwang.unimanager.mvc.vo.GoodsFavourVO;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.FavoursQuery;
import com.liangxunwang.unimanager.query.NewsQuery;
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
 * Created by Administrator on 2015/8/17.
 * app获取头条列表,保存头条信息,删除头条信息,查询单个头条信息
 */
@Service("appNewsObjService")
public class AppNewsObjService implements ListService ,SaveService,DeleteService,FindService{
    @Autowired
    @Qualifier("newsObjDao")
    private NewsObjDao newsObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NewsQuery query = (NewsQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_top())){
            map.put("is_top", query.getIs_top());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_msg_type())){
            map.put("mm_msg_type", query.getMm_msg_type());
        }
        List<NewsObj> list = newsObjDao.lists(map);
        for (NewsObj record : list){
            if (!StringUtil.isNullOrEmpty(record.getEmpCover())){
                if (record.getEmpCover().startsWith("upload")){
                    record.setEmpCover(Constants.URL + record.getEmpCover());
                }else {
                    record.setEmpCover(Constants.QINIU_URL + record.getEmpCover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getMm_msg_picurl())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getMm_msg_picurl()!=null){
                    pics = record.getMm_msg_picurl().split(",");
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
                record.setMm_msg_picurl(buffer.toString());
            }

            record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
        }

        return list;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        NewsObj newsObj = (NewsObj) object;
        newsObj.setDateline(System.currentTimeMillis()+"");
        newsObj.setIs_del("0");
        newsObj.setTop_num("0");
        newsObj.setIs_top("0");
        newsObjDao.save(newsObj);
        return 200;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_msg_id = (String) object;
        newsObjDao.deleteById(mm_msg_id);
        return 200;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        NewsObj record = newsObjDao.findById((String) object);
        if(record != null){
            if (!StringUtil.isNullOrEmpty(record.getEmpCover())){
                if (record.getEmpCover().startsWith("upload")){
                    record.setEmpCover(Constants.URL + record.getEmpCover());
                }else {
                    record.setEmpCover(Constants.QINIU_URL + record.getEmpCover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getMm_msg_picurl())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getMm_msg_picurl()!=null){
                    pics = record.getMm_msg_picurl().split(",");
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
                record.setMm_msg_picurl(buffer.toString());
            }
            record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
        }
        return record;
    }
}
