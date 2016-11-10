package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Cpguige;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpguigeDao")
public interface CpguigeDao {

    /**
     * 查询ad
     */
    List<Cpguige> lists(Map<String, Object> map);

    //保存
    void save(Cpguige adObj);

    //删除
    void delete(String cloud_caoping_guige_id);

    /**
     * 根据ID查找
     * @param cloud_caoping_guige_id
     * @return
     */
    public Cpguige findById(String cloud_caoping_guige_id);

    /**
     * 更新
     * @param cpguige
     */
    public void update(Cpguige cpguige);
}
