package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CpJxguige;
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
@RequestMapping("/cpJixieguigeController")
public class CpJixieguigeController extends ControllerConstants {

    @Autowired
    @Qualifier("cpJixieguigeService")
    private ListService levelService;

    @Autowired
    @Qualifier("cpJixieguigeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("cpJixieguigeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("cpJixieguigeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("cpJixieguigeService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<CpJxguige> list = (List<CpJxguige>) levelService.list("");
        map.put("list", list);
        return "/cpjxguige/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/cpjxguige/add";
    }


    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(HttpSession session,CpJxguige cpguige){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSave.save(cpguige);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String cloud_jixie_guige_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(cloud_jixie_guige_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_jixie_guige_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CpJxguige cpguige = (CpJxguige) levelServiceSaveExe.execute(cloud_jixie_guige_id);
        map.put("cpguige", cpguige);
        return "/cpjxguige/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/edit", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String edit(HttpSession session,CpJxguige cpJxguige){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(cpJxguige);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }



}
