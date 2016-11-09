package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.NewsObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.AdQuery;
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
 * app获取头条列表,保存头条信息,删除头条信息
 */
@Controller
public class AppNewsObjController extends ControllerConstants {

    @Autowired
    @Qualifier("appNewsObjService")
    private ListService appNewsObjServiceList;

    @Autowired
    @Qualifier("appNewsObjService")
    private DeleteService appNewsObjServiceDelete;

    @Autowired
    @Qualifier("appNewsObjService")
    private SaveService appNewsObjServiceSave;

    @Autowired
    @Qualifier("appNewsObjService")
    private FindService appNewsObjServiceFind;

    /**
     * app获取头条列表
     * @return
     */
    @RequestMapping(value = "/appGetNews", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetNews(NewsQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<NewsObj> list = (List<NewsObj>) appNewsObjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * app保存头条信息
     * @return
     */
    @RequestMapping(value = "/appSaveNews", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveNews(NewsObj newsObj){
        if(StringUtil.isNullOrEmpty(newsObj.getEmp_id())){
            return toJSONString(new ErrorTip(2, "用户ID为空"));//用户ID为空
        }
        if(StringUtil.isNullOrEmpty(newsObj.getMm_msg_title())){
            return toJSONString(new ErrorTip(3, "头条信息标题为空"));//头条信息标题为空
        }
        if(StringUtil.isNullOrEmpty(newsObj.getMm_msg_content())){
            return toJSONString(new ErrorTip(4, "头条信息内容为空"));//头条信息内容为空
        }
        try {
            String mm_msg_id = UUIDFactory.random();
            newsObj.setMm_msg_id(mm_msg_id);
            appNewsObjServiceSave.save(newsObj);
            //查询保存的信息，需要返回
            NewsObj newsObj1 = (NewsObj) appNewsObjServiceFind.findById(mm_msg_id);
            DataTip tip = new DataTip();
            tip.setData(newsObj1);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "保存头条信息失败！请稍后重试"));
        }
    }


    /**
     * app删除头条信息
     * @return
     */
    @RequestMapping(value = "/appDeleteNews", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appDeleteNews(String mm_msg_id){
        if(StringUtil.isNullOrEmpty(mm_msg_id)){
            return toJSONString(new ErrorTip(2, "头条信息ID为空"));//头条信息ID为空
        }
        try {
            appNewsObjServiceDelete.delete(mm_msg_id);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "删除失败，请稍后重试！"));
        }
    }

}
