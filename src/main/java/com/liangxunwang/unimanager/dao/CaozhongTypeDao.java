package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CaozhongType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("caozhongTypeDao")
public interface CaozhongTypeDao {

    /**
     * 查询
     */
    List<CaozhongType> lists(Map<String, Object> map);

    //保存
    void save(CaozhongType cpuse);

    //删除
    void delete(String cloud_caozhong_type_id);

    /**
     * 根据ID查找
     * @param cloud_caozhong_type_id
     * @return
     */
    public CaozhongType findById(String cloud_caozhong_type_id);

    /**
     * 更新
     * @param cptype
     */
    public void update(CaozhongType cptype);
}
