package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.model.NewsObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 * 头条新闻评论
 */
@Repository("commentDao")
public interface CommentDao {

    /**
     * 查询
     */
    List<Comment> lists(Map<String, Object> map);

    //保存
    void save(Comment comment);

    //删除
    void deleteById(String comment_id);

    /**
     * 根据ID查找
     */
    public Comment findById(String comment_id);

}
