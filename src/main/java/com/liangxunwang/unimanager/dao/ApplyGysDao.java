package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ApplyGys;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("applyGysDao")
public interface ApplyGysDao {

    /**
     * 查询
     */
    List<ApplyGys> lists(Map<String, Object> map);

    //保存
    void save(ApplyGys adObj);

    //删除
    void delete(String mm_ad_id);

    /**
     * 根据ID查找
     * @param apply_gys_id
     * @return
     */
    public ApplyGys findById(String apply_gys_id);
    //
    public ApplyGys findByEmpId(String emp_id);

    /**
     * 更新
     * @param adObj
     */
    public void update(ApplyGys adObj);

    long count(Map<String, Object> map);

}
