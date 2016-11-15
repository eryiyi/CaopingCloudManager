package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CaozhongType;
import com.liangxunwang.unimanager.model.CarType;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/carTypeController")
public class CarTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("carTypesService")
    private ListService carTypeServiceList;

    @Autowired
    @Qualifier("carTypesService")
    private SaveService carTypeServiceSave;

    @Autowired
    @Qualifier("carTypesService")
    private ExecuteService carTypeServiceExe;

    @Autowired
    @Qualifier("carTypesService")
    private UpdateService carTypeServiceUpdate;

    @Autowired
    @Qualifier("carTypesService")
    private DeleteService carTypeServiceDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, String is_use){
        List<CarType> list = (List<CarType>) carTypeServiceList.list(is_use);
        map.put("list", list);
        return "/carType/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/carType/add";
    }

    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(HttpSession session,CarType carType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            carTypeServiceSave.save(carType);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String car_type_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        carTypeServiceDel.delete(car_type_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String car_type_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CarType cptype = (CarType) carTypeServiceExe.execute(car_type_id);
        map.put("cptype", cptype);
        return "/carType/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/edit", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String edit(HttpSession session,CarType carType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(carType.getCar_type_id())){
            return toJSONString(new ErrorTip(1, "请确认车型ID是否存在！"));
        }
        if(StringUtil.isNullOrEmpty(carType.getCar_type_name())){
            return toJSONString(new ErrorTip(1, "请输入车型！"));
        }
        if(StringUtil.isNullOrEmpty(carType.getIs_use())){
            return toJSONString(new ErrorTip(1, "请选择是否禁用！"));
        }
        try {
            carTypeServiceUpdate.update(carType);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }



}
