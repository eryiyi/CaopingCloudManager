package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.query.CzObjQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppCaozhongController extends ControllerConstants {

    @Autowired
    @Qualifier("cpCaozhongGuigeService")
    private ListService cpguigeService;

    @Autowired
    @Qualifier("caozhongTypeService")
    private ListService cptypeService;


    @Autowired
    @Qualifier("caozhongObjService")
    private ListService cpobjServiceList;

    @Autowired
    @Qualifier("caozhongObjService")
    private SaveService cpobjServiceSave;

    @Autowired
    @Qualifier("caozhongObjService")
    private ExecuteService caozhongObjService;

    /**
     * 获得草种规格
     * @return
     */
    @RequestMapping(value = "/appGetCaozhongGuige", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCaozhongGuige(){
        try {
            List<CpCaozhongguige> list = (List<CpCaozhongguige>) cpguigeService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 获得草种属性
     * @return
     */
    @RequestMapping(value = "/appGetCaozhongType", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCaozhongType(){
        try {
            List<CaozhongType> list = (List<CaozhongType>) cptypeService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


    /**
     * 查询草种列表
     * @return
     */
    @RequestMapping(value = "/appGetCaozhongLists", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCaozhongLists(CzObjQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<CpObj> list = (List<CpObj>) cpobjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 保存草种对象
     * @return
     */
    @RequestMapping(value = "/appSaveCaozhong", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveCaozhong(CaozhongObj cpObj){
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caozhong_title())){
            return toJSONString(new ErrorTip(2, "标题不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caozhong_content())){
            return toJSONString(new ErrorTip(3, "内容不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caozhong_prices())){
            return toJSONString(new ErrorTip(4, "价格不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caozhong_guige_id())){
            return toJSONString(new ErrorTip(5, "请选择规格！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caozhong_type_id())){
            return toJSONString(new ErrorTip(6, "请选择属性！"));
        }
        if(cpObj.getCloud_caozhong_title().length() > 100){
            return toJSONString(new ErrorTip(8, "标题超出字段限制，100字以内！"));
        }
        if(cpObj.getCloud_caozhong_content().length() > 1000){
            return toJSONString(new ErrorTip(9, "内讧超出字段限制，1000字以内！"));
        }
        try {
            cpobjServiceSave.save(cpObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }
}
