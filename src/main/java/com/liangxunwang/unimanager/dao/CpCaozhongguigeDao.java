package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CpCaozhongguige;
import com.liangxunwang.unimanager.model.CpJxguige;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cpCaozhongguigeDao")
public interface CpCaozhongguigeDao {

    /**
     * 查询ad
     */
    List<CpCaozhongguige> lists(Map<String, Object> map);

    //保存
    void save(CpCaozhongguige cpJxguige);

    //删除
    void delete(String cloud_caozhong_guige_id);

    /**
     * 根据ID查找
     * @param cloud_caozhong_guige_id
     * @return
     */
    public CpCaozhongguige findById(String cloud_caozhong_guige_id);

    /**
     * 更新
     * @param cpJxguige
     */
    public void update(CpCaozhongguige cpJxguige);
}
