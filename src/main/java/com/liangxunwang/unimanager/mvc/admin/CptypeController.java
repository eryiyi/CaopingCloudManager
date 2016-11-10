package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Cptype;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cptypeController")
public class CptypeController extends ControllerConstants {

    @Autowired
    @Qualifier("cptypeService")
    private ListService levelService;

    @Autowired
    @Qualifier("cptypeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("cptypeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("cptypeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("cptypeService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<Cptype> list = (List<Cptype>) levelService.list("");
        map.put("list", list);
        return "/cptype/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/cptype/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(HttpSession session,Cptype cptype){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSave.save(cptype);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String cloud_caoping_type_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(cloud_caoping_type_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_caoping_type_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        Cptype cptype = (Cptype) levelServiceSaveExe.execute(cloud_caoping_type_id);
        map.put("cptype", cptype);
        return "/cptype/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,Cptype cpuse){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(cpuse);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }



}
