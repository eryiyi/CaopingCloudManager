package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CommentDao;
import com.liangxunwang.unimanager.dao.FavourDao;
import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.model.FavourObj;
import com.liangxunwang.unimanager.query.NewsCommentQuery;
import com.liangxunwang.unimanager.query.NewsFavourQuery;
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
 * app获取头条赞
 */
@Service("appFavourService")
public class AppFavourService implements ListService ,SaveService,DeleteService,FindService{
    @Autowired
    @Qualifier("favourDao")
    private FavourDao favourDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NewsFavourQuery query = (NewsFavourQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getMm_msg_id())){
            map.put("mm_msg_id", query.getMm_msg_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }

        List<FavourObj> list = favourDao.lists(map);
        for (FavourObj record : list){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getDateline())){
                record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
            }

        }

        return list;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        FavourObj newsObj = (FavourObj) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(newsObj.getMm_msg_id())){
            map.put("mm_msg_id", newsObj.getMm_msg_id());
        }
        if(!StringUtil.isNullOrEmpty(newsObj.getEmp_id())){
            map.put("emp_id", newsObj.getEmp_id());
        }

        List<FavourObj> list = favourDao.isExist(map);
        if(list != null && list.size() > 0){
            //说明存在了 不能重复添加
            throw new ServiceException("ISFAVOUR");//已经收藏过了
        }else{
            newsObj.setFavour_id(UUIDFactory.random());
            newsObj.setDateline(System.currentTimeMillis() + "");
            favourDao.save(newsObj);
        }

        return 200;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String favour_id = (String) object;
        favourDao.deleteById(favour_id);
        return 200;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        FavourObj record = favourDao.findById((String) object);
        if(record != null){
            if (!StringUtil.isNullOrEmpty(record.getEmp_cover())){
                if (record.getEmp_cover().startsWith("upload")){
                    record.setEmp_cover(Constants.URL + record.getEmp_cover());
                }else {
                    record.setEmp_cover(Constants.QINIU_URL + record.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getDateline())){
                record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
            }

        }
        return record;
    }
}
