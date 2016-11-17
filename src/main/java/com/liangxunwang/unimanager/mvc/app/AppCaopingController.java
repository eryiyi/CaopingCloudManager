package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.query.NewsQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
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
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppCaopingController extends ControllerConstants {

    @Autowired
    @Qualifier("cpguigeService")
    private ListService cpguigeService;

    @Autowired
    @Qualifier("cptypeService")
    private ListService cptypeService;

    @Autowired
    @Qualifier("appCpuseService")
    private ListService appCpuseService;


    @Autowired
    @Qualifier("appCpobjService")
    private ListService appCpobjServiceList;

    @Autowired
    @Qualifier("appCpobjService")
    private SaveService appCpobjService;

    @Autowired
    @Qualifier("appCpobjService")
    private ExecuteService appCpobjServiceExe;


    /**
     * 获得草坪规格
     * @return
     */
    @RequestMapping(value = "/appGetCpGuige", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpGuige(){
        try {
            List<Cpguige> list = (List<Cpguige>) cpguigeService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 获得草坪属性
     * @return
     */
    @RequestMapping(value = "/appGetCpType", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpType(){
        try {
            List<Cptype> list = (List<Cptype>) cptypeService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


    /**
     * 获得草坪用途
     * @return
     */
    @RequestMapping(value = "/appGetCpUse", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpUse(){
        try {
            List<Cpuse> list = (List<Cpuse>) appCpuseService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


    /**
     * 查询草坪列表
     * @return
     */
    @RequestMapping(value = "/appGetCpLists", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpLists(CpObjQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            Object[] results= ( Object[] ) appCpobjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 保存草坪对象
     * @return
     */
    @RequestMapping(value = "/appSaveCpLists", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveCpLists(CpObj cpObj){
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_title())){
            return toJSONString(new ErrorTip(2, "标题不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_content())){
            return toJSONString(new ErrorTip(3, "内容不能为空！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_prices())){
            return toJSONString(new ErrorTip(4, "价格不能为空！"));
        }

        if(cpObj.getCloud_caoping_title().length() > 100){
            return toJSONString(new ErrorTip(8, "标题超出字段限制，100字以内！"));
        }
        if(cpObj.getCloud_caoping_content().length() > 1000){
            return toJSONString(new ErrorTip(9, "内讧超出字段限制，1000字以内！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getEmp_id())){
            return toJSONString(new ErrorTip(10, "请检查用户ID是否存在！"));
        }
        try {
            appCpobjService.save(cpObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("not_has_power")){
                return toJSONString(new ErrorTip(11, "你没有权限发布信息，请先开通供应商权限！"));
            }else{
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
            }
        }
    }


    @Autowired
    @Qualifier("appCompanyService")
    private ExecuteService appCompanyService;
    @Autowired
    @Qualifier("countCpService")
    private ExecuteService countCpServiceExe;


    @RequestMapping(value = "/toDetailCp", produces = "text/plain;charset=UTF-8")
    public String toDetailMsg(String id,ModelMap map, HttpSession session) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        CpObj cpObj = (CpObj) appCpobjServiceExe.execute(id);
        if(!StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_pic())){
            String[] voPic = cpObj.getCloud_caoping_pic().split(",");
            map.put("voPic", voPic);
        }
        //产品详情
        map.put("vo", cpObj);
        //公司详情
        Company company = (Company) appCompanyService.execute(cpObj.getEmp_id());
        map.put("company", company);
        //草原规模：发布多少条产品信息
        String cyNum = (String) countCpServiceExe.execute(cpObj.getEmp_id());
        map.put("cyNum", cyNum);
        return "/product/viewGoods";
    }



    /**
     * 获得草坪详情
     * @return
     */
    @RequestMapping(value = "/appGetCpDetail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpDetail(String cloud_caoping_id){
        try {
            CpObj cpObj = (CpObj) appCpobjServiceExe.execute(cloud_caoping_id);
            DataTip tip = new DataTip();
            tip.setData(cpObj);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }
}
