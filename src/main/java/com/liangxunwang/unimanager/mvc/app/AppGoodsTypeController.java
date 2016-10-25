package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 * 商品分类
 */
@Controller
public class AppGoodsTypeController extends ControllerConstants {
    @Autowired
    @Qualifier("goodsTypeService")
    private ListService listGoodsTypeService;


    /**
     * 获得所有商品分类
     * @return
     */
    @RequestMapping(value = "/appGetGoodsType", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetGoodsType(GoodsTypeThreeQuery query){
        try {
            List<GoodsType> list = (List<GoodsType>) listGoodsTypeService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
}
