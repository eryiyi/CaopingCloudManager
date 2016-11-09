package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.model.FavourObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 * 头条新闻点赞
 */
@Repository("favourDao")
public interface FavourDao {

    /**
     * 查询
     */
    List<FavourObj> lists(Map<String, Object> map);

    List<FavourObj> isExist(Map<String, Object> map);

    //保存
    void save(FavourObj favourObj);

    //删除
    void deleteById(String favour_id);

    /**
     * 根据ID查找
     */
    public FavourObj findById(String favour_id);

}
