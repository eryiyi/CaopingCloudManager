package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Comment;
import com.liangxunwang.unimanager.model.FavourObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.NewsCommentQuery;
import com.liangxunwang.unimanager.query.NewsFavourQuery;
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
 * app获取头条赞
 */
@Controller
public class AppFavourNewsController extends ControllerConstants {

    @Autowired
    @Qualifier("appFavourService")
    private ListService appNewsObjServiceList;

    @Autowired
    @Qualifier("appFavourService")
    private DeleteService appNewsObjServiceDelete;

    @Autowired
    @Qualifier("appFavourService")
    private SaveService appNewsObjServiceSave;

    @Autowired
    @Qualifier("appFavourService")
    private FindService appNewsObjServiceFind;

    /**
     * app获取头条赞列表
     * @return
     */
    @RequestMapping(value = "/appGetNewsFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetNewsFavour(NewsFavourQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<FavourObj> list = (List<FavourObj>) appNewsObjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * app保存头条信息赞
     * @return
     */
    @RequestMapping(value = "/appSaveNewsFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveNewsComment(FavourObj newsObj){
        if(StringUtil.isNullOrEmpty(newsObj.getEmp_id())){
            return toJSONString(new ErrorTip(2, "用户ID为空"));//用户ID为空
        }
        if(StringUtil.isNullOrEmpty(newsObj.getMm_msg_id())){
            return toJSONString(new ErrorTip(3, "请检查信息是否存在"));//头条信息标题为空
        }
        try {
            appNewsObjServiceSave.save(newsObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISFAVOUR")){//已经收藏过了
                return toJSONString(new ErrorTip(2, "已经赞过了！"));
            }else{
                return toJSONString(new ErrorTip(1, "操作失败！请稍后重试"));
            }
        }
    }


    /**
     * app删除头条赞
     * @return
     */
    @RequestMapping(value = "/appDeleteNewsFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appDeleteNewsFavour(String favour_id){
        if(StringUtil.isNullOrEmpty(favour_id)){
            return toJSONString(new ErrorTip(2, "删除失败，信息不存在！"));//头条信息ID为空
        }
        try {
            appNewsObjServiceDelete.delete(favour_id);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "删除失败，请稍后重试！"));
        }
    }

}
