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

}
