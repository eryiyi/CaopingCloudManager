package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CommentDao;
import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.query.NewsCommentQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 * app获取头条评论
 */
@Service("appCommentService")
public class AppCommentService implements ListService ,SaveService,DeleteService,FindService{
    @Autowired
    @Qualifier("commentDao")
    private CommentDao commentDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NewsCommentQuery query = (NewsCommentQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getMm_msg_id())){
            map.put("mm_msg_id", query.getMm_msg_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getComment_emp_id())){
            map.put("comment_emp_id", query.getComment_emp_id());
        }

        List<Comment> list = commentDao.lists(map);
        for (Comment record : list){
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
        Comment newsObj = (Comment) object;
        newsObj.setDateline(System.currentTimeMillis() + "");
        commentDao.save(newsObj);
        return 200;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String comment_id = (String) object;
        commentDao.deleteById(comment_id);
        return 200;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        Comment record = commentDao.findById((String) object);
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
