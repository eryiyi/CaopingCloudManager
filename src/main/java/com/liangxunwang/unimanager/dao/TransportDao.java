package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ApplyGys;
import com.liangxunwang.unimanager.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("transportDao")
public interface TransportDao {

    /**
     * 查询
     */
    List<Transport> lists(Map<String, Object> map);

    //保存
    void save(Transport transport);


    /**
     * 根据ID查找
     * @param transport_id
     * @return
     */
    public Transport findById(String transport_id);

    /**
     * 更新 use
     */
    public void updateUse(Transport transport);

    //更新del
    public void updateDel(Transport transport);

    long count(Map<String, Object> map);

}
