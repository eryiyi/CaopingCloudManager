package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CpJixie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpJixieDao")
public interface CpJixieDao {

    /**
     * 查询
     */
    List<CpJixie> lists(Map<String, Object> map);

    //保存
    void save(CpJixie cpJixie);

    //删除
    void delete(String cloud_jixie_id);

    /**
     * 根据ID查找
     * @param cloud_jixie_id
     * @return
     */
    public CpJixie findById(String cloud_jixie_id);

    /**
     * 更新
     * @param cpJixie
     */
    public void update(CpJixie cpJixie);
}
