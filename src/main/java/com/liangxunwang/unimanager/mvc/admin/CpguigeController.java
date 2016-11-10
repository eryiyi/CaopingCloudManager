package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Cpguige;
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
@RequestMapping("/cpguigeController")
public class CpguigeController extends ControllerConstants {

    @Autowired
    @Qualifier("cpguigeService")
    private ListService levelService;

    @Autowired
    @Qualifier("cpguigeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("cpguigeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("cpguigeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("cpguigeService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<Cpguige> list = (List<Cpguige>) levelService.list("");
        map.put("list", list);
        return "/cpguige/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/cpguige/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(HttpSession session,Cpguige cpguige){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSave.save(cpguige);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String cloud_caoping_use_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(cloud_caoping_use_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_caoping_guige_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        Cpguige cpguige = (Cpguige) levelServiceSaveExe.execute(cloud_caoping_guige_id);
        map.put("cpguige", cpguige);
        return "/cpguige/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,Cpguige cpguige){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(cpguige);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }



}
