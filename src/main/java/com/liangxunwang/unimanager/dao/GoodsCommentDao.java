package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GoodsComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Repository("goodsCommentDao")
public interface GoodsCommentDao {
    public void save(GoodsComment comment);

    public List<GoodsComment> list(Map<String,Object> map);

    List<GoodsComment> listAllComment(Map<String,Object> map);
}
