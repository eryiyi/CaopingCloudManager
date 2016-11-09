package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 * 商城分类
 */
@Controller
public class GoodsTypeController extends ControllerConstants{

    @Autowired
    @Qualifier("goodsTypeService")
    private SaveService saveGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private ListService listGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private FindService findGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private UpdateService updateGoodsTypeService;


    @RequestMapping("/toAddGoodsType")
    public String toAddType(){
        return "/goodsType/addType";
    }

    @RequestMapping("/addGoodsType")
    @ResponseBody
    public String addType(GoodsType type){
        if (StringUtil.isNullOrEmpty(type.getTypeName())){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );//名称不能为空
        }

        if (StringUtil.isNullOrEmpty(type.getTypeCover())){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );//图片不能为空
        }

        try {
            saveGoodsTypeService.save(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


    @RequestMapping("/listType")
    public String listType(ModelMap map, GoodsTypeThreeQuery query, HttpSession session){
        List<GoodsType> list = (List<GoodsType>) listGoodsTypeService.list(query);
        map.put("list", list);
        return "/goodsType/listType";
    }

    @RequestMapping("/toUpdateType")
    public String toUpdateType(ModelMap map, String typeId){
        GoodsType type = (GoodsType) findGoodsTypeService.findById(typeId);
        map.put("type", type);
        return "/goodsType/updateType";
    }

    /**
     * 更新分类
     * @param type
     * @return
     */
    @RequestMapping("/updateGoodsType")
    @ResponseBody
    public String updateGoodsType(GoodsType type){
        try {
            updateGoodsTypeService.update(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

}
