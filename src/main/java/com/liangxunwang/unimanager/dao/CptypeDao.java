package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Cptype;
import com.liangxunwang.unimanager.model.Cpuse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cptypeDao")
public interface CptypeDao {

    /**
     * 查询
     */
    List<Cptype> lists(Map<String, Object> map);

    //保存
    void save(Cptype cpuse);

    //删除
    void delete(String cloud_caoping_type_id);

    /**
     * 根据ID查找
     * @param cloud_caoping_type_id
     * @return
     */
    public Cptype findById(String cloud_caoping_type_id);

    /**
     * 更新
     * @param cptype
     */
    public void update(Cptype cptype);
}
