package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CpJixieuse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpJixieUseDao")
public interface CpJixieUseDao {

    /**
     * 查询ad
     */
    List<CpJixieuse> lists(Map<String, Object> map);

    //保存
    void save(CpJixieuse cpuse);

    //删除
    void delete(String cloud_jixie_use_id);

    /**
     * 根据ID查找
     * @param cloud_jixie_use_id
     */
    public CpJixieuse findById(String cloud_jixie_use_id);

    /**
     * 更新
     * @param cpguige
     */
    public void update(CpJixieuse cpguige);
}
