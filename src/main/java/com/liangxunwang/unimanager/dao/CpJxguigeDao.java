package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CpJxguige;
import com.liangxunwang.unimanager.model.Cpguige;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpJxguigeDao")
public interface CpJxguigeDao {

    /**
     * 查询ad
     */
    List<CpJxguige> lists(Map<String, Object> map);

    //保存
    void save(CpJxguige cpJxguige);

    //删除
    void delete(String cloud_jixie_guige_id);

    /**
     * 根据ID查找
     * @param cloud_jixie_guige_id
     * @return
     */
    public CpJxguige findById(String cloud_jixie_guige_id);

    /**
     * 更新
     * @param cpJxguige
     */
    public void update(CpJxguige cpJxguige);
}
