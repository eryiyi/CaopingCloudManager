package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/5.
 */
@Controller
public class GoodsCommentController extends ControllerConstants {

    @Autowired
    @Qualifier("goodsCommentService")
    private SaveService goodsCommentSaveService;

    @Autowired
    @Qualifier("goodsCommentService")
    private ListService goodsCommentListService;

    @RequestMapping("/saveGoodsComment")
    @ResponseBody
    public String saveGoodsComment(GoodsComment comment){
        if(StringUtil.isNullOrEmpty(comment.getEmpId())){
            return toJSONString(new ErrorTip(1, "评论失败，请检查用户ID是否存在！"));
        }
        if(StringUtil.isNullOrEmpty(comment.getGoodsId())){
            return toJSONString(new ErrorTip(1, "评论失败，请检查产品ID是否存在！"));
        }
        if(StringUtil.isNullOrEmpty(comment.getGoodsEmpId())){
            return toJSONString(new ErrorTip(1, "评论失败，请检查产品所有者ID是否存在！"));
        }
        try {
            goodsCommentSaveService.save(comment);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "评论失败，请稍后重试！"));
        }
    }

    @RequestMapping(value = "/listGoodsComment", produces = "text/plain; charset=UTF-8;")
    @ResponseBody
    public String listGoodsComment(GoodsCommentQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            List<GoodsComment> list = (List<GoodsComment>) goodsCommentListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

}
