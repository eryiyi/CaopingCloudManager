package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.CpJixie;
import com.liangxunwang.unimanager.model.CpJixieuse;
import com.liangxunwang.unimanager.model.CpJxguige;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.CpJixieQuery;
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
public class AppCaopingJixieController extends ControllerConstants {

    @Autowired
    @Qualifier("cpJixieguigeService")
    private ListService cpJixieguigeService;

    @Autowired
    @Qualifier("cpJixieUseService")
    private ListService cpJixieUseService;

    @Autowired
    @Qualifier("cpJixieService")
    private ListService cpobjServiceList;

    @Autowired
    @Qualifier("cpJixieService")
    private SaveService cpobjServiceSave;

    @Autowired
    @Qualifier("cpJixieService")
    private ExecuteService cpobjServiceExe;

    /**
     * 获得机械规格
     * @return
     */
    @RequestMapping(value = "/appGetJixieGuige", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetJixieGuige(){
        try {
            List<CpJxguige> list = (List<CpJxguige>) cpJixieguigeService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


    /**
     * 获得机械用途
     * @return
     */
    @RequestMapping(value = "/appGetJixieUse", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpUse(){
        try {
            List<CpJixieuse> list = (List<CpJixieuse>) cpJixieUseService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


    /**
     * 查询机械列表
     * @return
     */
    @RequestMapping(value = "/appGetJixie", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetJixie(CpJixieQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<CpJixie> list = (List<CpJixie>) cpobjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 保存机械对象
     * @return
     */
    @RequestMapping(value = "/appSaveJixie", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveJixie(CpJixie cpObj){
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_jixie_title())){
            return toJSONString(new ErrorTip(2, "标题不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_jixie_content())){
            return toJSONString(new ErrorTip(3, "内容不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_jixie_prices())){
            return toJSONString(new ErrorTip(4, "价格不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_jixie_guige_id())){
            return toJSONString(new ErrorTip(5, "请选择规格！"));
        }

        if(StringUtil.isNullOrEmpty(cpObj.getCloud_jixie_use_id())){
            return toJSONString(new ErrorTip(7, "请选择用途！"));
        }
        if(cpObj.getCloud_jixie_title().length() > 100){
            return toJSONString(new ErrorTip(8, "标题超出字段限制，100字以内！"));
        }
        if(cpObj.getCloud_jixie_content().length() > 1000){
            return toJSONString(new ErrorTip(9, "内讧超出字段限制，1000字以内！"));
        }
        try {
            cpobjServiceSave.save(cpObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("not_has_power")){
                return toJSONString(new ErrorTip(11, "你没有权限发布草坪信息，请先开通供应商权限！"));
            }else{
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
            }
        }
    }
}
