package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.ApplyGys;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
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
public class AppApplyGysController extends ControllerConstants {

    @Autowired
    @Qualifier("applyGysService")
    private SaveService applyGysServiceSave;


    @Autowired
    @Qualifier("appApplyGysService")
    private ExecuteService appApplyGysServiceExe;

    /**
     * 提交申请
     * @return
     */
    @RequestMapping(value = "/appSaveApplyGys", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveApplyGys(ApplyGys applyGys){
        if(StringUtil.isNullOrEmpty(applyGys.getEmp_id())){
            return toJSONString(new ErrorTip(2,"没有该用户，请检查ID！"));
        }
        try {
            applyGysServiceSave.save(applyGys);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("no_emp")){
                return toJSONString(new ErrorTip(2, "没有该用户，请检查ID！"));
            }else if (msg.equals("has_exist")){
                return toJSONString(new ErrorTip(3, "已经申请过了，不能重复申请！"));
            }
            else{
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
            }
        }
    }

    /**
     * 获得申请信息
     * @return
     */
    @RequestMapping(value = "/appGetApplyGysById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetApplyGysById(String emp_id){
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(new ErrorTip(2,"没有该用户，请检查ID！"));
        }
        try {
            ApplyGys applyGys = (ApplyGys) appApplyGysServiceExe.execute(emp_id);
            DataTip tip = new DataTip();
            tip.setData(applyGys);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));

        }
    }
}
