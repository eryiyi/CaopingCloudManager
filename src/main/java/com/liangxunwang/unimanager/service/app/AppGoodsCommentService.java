package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appGoodsCommentService")
public class AppGoodsCommentService implements FindService {
    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        String goods_id = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("goods_id", goods_id);
        List<GoodsComment> list = goodsCommentDao.listAllComment(map);
        Double doub = 0.0;
        List<GoodsComment> listGoods = new ArrayList<GoodsComment>();
        if(list != null){
            for(GoodsComment goodsComment:list){
                if(goodsComment.getStarNumber() == 5){
                    listGoods.add(goodsComment);
                }
            }
            if(list.size() == 0){
                doub = 0.0;
            }else {
                doub = (Double.valueOf(listGoods.size()) / list.size());
            }
            return new Object[] {list.size(), doub*100};
        }else {
            return new Object[] {0, doub};
        }
    }
}
