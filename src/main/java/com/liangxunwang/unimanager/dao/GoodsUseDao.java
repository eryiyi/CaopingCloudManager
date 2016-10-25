package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GoodsUse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Repository("goodsUseDao")
public interface GoodsUseDao {
    /**
     * 保存用户分类
     * @param goodsType
     */
    public void save(GoodsUse goodsType);

    public List<GoodsUse> list(Map<String, Object> map);

    /**
     * 根据ID查找分类
     * @param typeId
     * @return
     */
    public GoodsUse findById(String typeId);

    /**
     * 更新分类
     * @param type
     */
    public void update(GoodsUse type);

    /**
     * 根据ID删除分类
     * @param typeId
     */
    public void deleteById(String typeId);

}
