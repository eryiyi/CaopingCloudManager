package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Cpguige;
import com.liangxunwang.unimanager.model.Cpuse;
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
@RequestMapping("/cpuseController")
public class CpuseController extends ControllerConstants {

    @Autowired
    @Qualifier("cpuseService")
    private ListService levelService;

    @Autowired
    @Qualifier("cpuseService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("cpuseService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("cpuseService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("cpuseService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<Cpuse> list = (List<Cpuse>) levelService.list("");
        map.put("list", list);
        return "/cpuse/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/cpuse/add";
    }


    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(HttpSession session,Cpuse cpguige){
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
    public String toEdit(HttpSession session,ModelMap map, String cloud_caoping_use_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        Cpuse cpuse = (Cpuse) levelServiceSaveExe.execute(cloud_caoping_use_id);
        map.put("cpuse", cpuse);
        return "/cpuse/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/edit", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String edit(HttpSession session,Cpuse cpuse){
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
