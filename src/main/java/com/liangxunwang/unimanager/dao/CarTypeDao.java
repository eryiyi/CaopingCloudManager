package com.liangxunwang.unimanager.dao;


import com.liangxunwang.unimanager.model.CarType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("carTypeDao")
public interface CarTypeDao {

    /**
     * 查询
     */
    List<CarType> lists(Map<String, Object> map);

    //保存
    void save(CarType cpuse);

    //删除
    void delete(String car_type_id);

    /**
     * 根据ID查找
     * @param car_type_id
     * @return
     */
    public CarType findById(String car_type_id);

    /**
     * 更新
     * @param cptype
     */
    public void update(CarType cptype);
}
