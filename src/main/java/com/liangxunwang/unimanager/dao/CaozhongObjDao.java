package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CaozhongObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("caozhongObjDao")
public interface CaozhongObjDao {

    /**
     * 查询
     */
    List<CaozhongObj> lists(Map<String, Object> map);

    //保存
    void save(CaozhongObj cpObj);

    //删除
    void delete(String cloud_caozhong_id);

    /**
     * 根据ID查找
     * @param cloud_caozhong_id
     * @return
     */
    public CaozhongObj findById(String cloud_caozhong_id);

    /**
     * 更新
     * @param cpObj
     */
    public void update(CaozhongObj cpObj);
}
