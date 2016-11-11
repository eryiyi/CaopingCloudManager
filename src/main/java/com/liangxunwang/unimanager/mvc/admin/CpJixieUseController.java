package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CpJixieuse;
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
@RequestMapping("/cpJixieUseController")
public class CpJixieUseController extends ControllerConstants {

    @Autowired
    @Qualifier("cpJixieUseService")
    private ListService levelService;

    @Autowired
    @Qualifier("cpJixieUseService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("cpJixieUseService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("cpJixieUseService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("cpJixieUseService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<CpJixieuse> list = (List<CpJixieuse>) levelService.list("");
        map.put("list", list);
        return "/cpjxuse/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/cpjxuse/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(HttpSession session,CpJixieuse cpguige){
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
    public String delete(HttpSession session,String cloud_jixie_use_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(cloud_jixie_use_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_jixie_use_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CpJixieuse cpuse = (CpJixieuse) levelServiceSaveExe.execute(cloud_jixie_use_id);
        map.put("cpuse", cpuse);
        return "/cpjxuse/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,CpJixieuse cpuse){
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
