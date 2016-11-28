package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.NewsObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("newsObjDao")
public interface NewsObjDao {

    /**
     * 查询
     */
    List<NewsObj> lists(Map<String, Object> map);

    //保存
    void save(NewsObj newsObj);

    //删除
    void deleteById(String mm_msg_id);

    /**
     * 根据ID查找
     */
    public NewsObj findById(String mm_msg_id);

    /**
     * 更新
     */
    public void update(NewsObj newsObj);

    Long count(Map<String, Object> map);
}
