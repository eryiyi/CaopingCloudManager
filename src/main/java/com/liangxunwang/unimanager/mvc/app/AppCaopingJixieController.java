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

}
