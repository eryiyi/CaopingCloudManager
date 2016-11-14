package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.query.TransportQuery;
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
public class AppCarController extends ControllerConstants {
    @Autowired
    @Qualifier("carTypeService")
    private ListService carTypeServiceList;

    @Autowired
    @Qualifier("carLengthService")
    private ListService carLengthService;

    @Autowired
    @Qualifier("appTransportService")
    private ListService appTransportServiceList;

    @Autowired
    @Qualifier("appTransportService")
    private SaveService appTransportServiceSave;


    /**
     * 获得车型
     * @return
     */
    @RequestMapping(value = "/appGetCarType", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCarType(){
        try {
            List<CarType> list = (List<CarType>) carTypeServiceList.list("0");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 获得车长
     * @return
     */
    @RequestMapping(value = "/appGetCarLength", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCarLength(){
        try {
            List<CarLength> list = (List<CarLength>) carLengthService.list("0");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 查询物流列表
     * @return
     */
    @RequestMapping(value = "/appGetTransport", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCpLists(TransportQuery query, Page page){
        //分页查询
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            List<Transport> lists= (List<Transport>) appTransportServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(lists);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 保存物流对象
     * @return
     */
    @RequestMapping(value = "/appSaveTransport", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveCpLists(Transport cpObj){
        if(StringUtil.isNullOrEmpty(cpObj.getStart_provinceid())){
            return toJSONString(new ErrorTip(1, "请选择出发省份！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getStart_cityid())){
            return toJSONString(new ErrorTip(1, "请选择出发城市！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getStart_areaid())){
            return toJSONString(new ErrorTip(1, "请选择出发县区！"));
        }

        if(StringUtil.isNullOrEmpty(cpObj.getEnd_provinceid())){
            return toJSONString(new ErrorTip(1, "请选择到达省份！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getEnd_cityid())){
            return toJSONString(new ErrorTip(1, "请选择到达城市！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getEnd_areaid())){
            return toJSONString(new ErrorTip(1, "请选择到达县区！"));
        }

        if(StringUtil.isNullOrEmpty(cpObj.getStart_time())){
            return toJSONString(new ErrorTip(1, "请选择出发时间！"));
        }

        if(StringUtil.isNullOrEmpty(cpObj.getPerson())){
            return toJSONString(new ErrorTip(1, "请输入联系人！"));
        }
        if(StringUtil.isNullOrEmpty(cpObj.getTel())){
            return toJSONString(new ErrorTip(1, "请输入联系电话！"));
        }

        if(StringUtil.isNullOrEmpty(cpObj.getEmp_id())){
            return toJSONString(new ErrorTip(10, "请检查用户ID是否存在！"));
        }
        try {
            appTransportServiceSave.save(cpObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("not_has_power")){
                return toJSONString(new ErrorTip(11, "你没有权限发布信息，请先开通权限！"));
            }else{
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
            }
        }
    }
}
