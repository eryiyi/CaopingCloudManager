package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.model.NewsObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.NewsCommentQuery;
import com.liangxunwang.unimanager.query.NewsQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 * app获取头条评论
 */
@Controller
public class AppCommentNewsController extends ControllerConstants {

    @Autowired
    @Qualifier("appCommentService")
    private ListService appNewsObjServiceList;

    @Autowired
    @Qualifier("appCommentService")
    private DeleteService appNewsObjServiceDelete;

    @Autowired
    @Qualifier("appCommentService")
    private SaveService appNewsObjServiceSave;

    @Autowired
    @Qualifier("appCommentService")
    private FindService appNewsObjServiceFind;

    /**
     * app获取头条评论列表
     * @return
     */
    @RequestMapping(value = "/appGetNewsComment", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetNewsComment(NewsCommentQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<Comment> list = (List<Comment>) appNewsObjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * app保存头条信息评论
     * @return
     */
    @RequestMapping(value = "/appSaveNewsComment", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveNewsComment(Comment newsObj){
        if(StringUtil.isNullOrEmpty(newsObj.getComment_emp_id())){
            return toJSONString(new ErrorTip(2, "用户ID为空"));//用户ID为空
        }
        if(StringUtil.isNullOrEmpty(newsObj.getComment_content())){
            return toJSONString(new ErrorTip(3, "评论内容为空"));//头条信息标题为空
        }
        if(StringUtil.isNullOrEmpty(newsObj.getMm_msg_id())){
            return toJSONString(new ErrorTip(4, "请检查评论的信息是否存在"));//头条信息标题为空
        }
        try {
            String comment_id = UUIDFactory.random();
            newsObj.setComment_id(comment_id);
            appNewsObjServiceSave.save(newsObj);
            //查询保存的信息，需要返回
            Comment newsObj1 = (Comment) appNewsObjServiceFind.findById(comment_id);
            DataTip tip = new DataTip();
            tip.setData(newsObj1);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "操作失败！请稍后重试"));
        }
    }


    /**
     * app删除头条信息
     * @return
     */
    @RequestMapping(value = "/appDeleteNewsComment", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appDeleteNewsComment(String comment_id){
        if(StringUtil.isNullOrEmpty(comment_id)){
            return toJSONString(new ErrorTip(2, "删除失败，信息不存在！"));//头条信息ID为空
        }
        try {
            appNewsObjServiceDelete.delete(comment_id);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "删除失败，请稍后重试！"));
        }
    }

}
