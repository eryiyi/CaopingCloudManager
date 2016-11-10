package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Cpuse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpuseDao")
public interface CpuseDao {

    /**
     * 查询ad
     */
    List<Cpuse> lists(Map<String, Object> map);

    //保存
    void save(Cpuse cpuse);

    //删除
    void delete(String cloud_caoping_use_id);

    /**
     * 根据ID查找
     * @param cloud_caoping_use_id
     * @return
     */
    public Cpuse findById(String cloud_caoping_use_id);

    /**
     * 更新
     * @param cpguige
     */
    public void update(Cpuse cpguige);
}
