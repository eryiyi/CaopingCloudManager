package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.CpObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpObjDao")
public interface CpObjDao {

    /**
     * 查询
     */
    List<CpObj> lists(Map<String, Object> map);

    //保存
    void save(CpObj cpObj);

    //删除
    void delete(String cloud_caoping_id);

    /**
     * 根据ID查找
     * @param cloud_caoping_id
     * @return
     */
    public CpObj findById(String cloud_caoping_id);

    /**
     * 更新
     * @param cpObj
     */
    public void update(CpObj cpObj);
}
