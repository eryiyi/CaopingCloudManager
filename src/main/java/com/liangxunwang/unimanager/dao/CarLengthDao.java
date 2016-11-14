package com.liangxunwang.unimanager.dao;


import com.liangxunwang.unimanager.model.CarLength;
import com.liangxunwang.unimanager.model.CarType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("carLengthDao")
public interface CarLengthDao {

    /**
     * 查询
     */
    List<CarLength> lists(Map<String, Object> map);

    //保存
    void save(CarLength cpuse);

    //删除
    void delete(String car_length_id);

    /**
     * 根据ID查找
     * @param car_length_id
     * @return
     */
    public CarLength findById(String car_length_id);

    /**
     * 更新
     * @param cptype
     */
    public void update(CarLength cptype);
}
