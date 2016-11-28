package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.CompanySort;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
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

    @Autowired
    @Qualifier("appCpobjService")
    private DeleteService appCpobjServiceDelete;


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

    @Autowired
    @Qualifier("appDianpuFavourService")
    private ExecuteService appDianpuFavourServiceExe;

    @Autowired
    @Qualifier("appCompanySortService")
    private ExecuteService appCompanySortServiceExe;

    @Autowired
    @Qualifier("goodsCommentService")
    private ListService goodsCommentListService;

    @Autowired
    @Qualifier("goodsCommentService")
    private ExecuteService goodsCommentServiceExe;


    @RequestMapping(value = "/toDetailCp", produces = "text/plain;charset=UTF-8")
    public String toDetailMsg(String id,ModelMap map, HttpSession session) throws Exception {
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
        //关注人数
        DianpuFavourQuery query = new DianpuFavourQuery();
        query.setEmp_id(cpObj.getEmp_id());//被关注的人ID
        String countGz = (String) appDianpuFavourServiceExe.execute(query);
        map.put("countGz", countGz);

        CompanySort companySort = (CompanySort) appCompanySortServiceExe.execute(cpObj.getEmp_id());
        map.put("companySort", companySort);

        //查询两个评论
        GoodsCommentQuery commentQuery = new GoodsCommentQuery();
        commentQuery.setIndex(1);
        commentQuery.setSize(2);
        commentQuery.setGoodsId(cpObj.getCloud_caoping_id());
        List<GoodsComment> listComment = (List<GoodsComment>) goodsCommentListService.list(commentQuery);
        map.put("listComment", listComment);
        //查询好评几个 差评几个 一般评价几个
        List<String> listCommentCount = (List<String>) goodsCommentServiceExe.execute(cpObj.getEmp_id());

        if(listCommentCount != null){
            if(listCommentCount.size()>0){
                map.put("commentOne", listCommentCount.get(0));
            }
            if(listCommentCount.size()>1){
                map.put("commentTwo", listCommentCount.get(1));
            }
            if(listCommentCount.size()>2){
                map.put("commentThree", listCommentCount.get(2));
            }
            if(listCommentCount.size()>3){
                map.put("commentFour", listCommentCount.get(3));
            }
        }
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


    /**
     * app删除产品
     * @return
     */
    @RequestMapping(value = "/appDeleteProductById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appDeleteProductById(String cloud_caoping_id){
        if(StringUtil.isNullOrEmpty(cloud_caoping_id)){
            return toJSONString(new ErrorTip(1, "产品ID不能为空！"));
        }
        try {
            appCpobjServiceDelete.delete(cloud_caoping_id);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }




    @Autowired
    @Qualifier("appStatisticalService")
    private ExecuteService appStatisticalService;

    /**
     * app端首页获得草坪统计数据
     * @return
     */
    @RequestMapping(value = "/appGetStatistical", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetStatistical(String emp_id){
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(new ErrorTip(1, "请确认用户ID是否存在！"));
        }
        try {
            //发了多少草坪数据 机械数据 草种数据
            //发了多少物流数据
            //发了多少新闻
            //有多少订单
            //有多少人关注你
            //你的公司在名企排行中排第几
            StatisticalObj statisticalObj = (StatisticalObj) appStatisticalService.execute(emp_id);
            DataTip tip = new DataTip();
            tip.setData(statisticalObj);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }


}
