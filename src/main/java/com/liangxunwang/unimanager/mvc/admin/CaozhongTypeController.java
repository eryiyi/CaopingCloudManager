package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CaozhongType;
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
@RequestMapping("/caozhongTypeController")
public class CaozhongTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("caozhongTypeService")
    private ListService levelService;

    @Autowired
    @Qualifier("caozhongTypeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("caozhongTypeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("caozhongTypeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("caozhongTypeService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        List<CaozhongType> list = (List<CaozhongType>) levelService.list("");
        map.put("list", list);
        return "/caozhongtype/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/caozhongtype/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(HttpSession session,CaozhongType cptype){
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
    public String delete(HttpSession session,String cloud_caozhong_type_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(cloud_caozhong_type_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_caozhong_type_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CaozhongType cptype = (CaozhongType) levelServiceSaveExe.execute(cloud_caozhong_type_id);
        map.put("cptype", cptype);
        return "/caozhongtype/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,CaozhongType cpuse){
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
