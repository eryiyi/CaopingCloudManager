package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class IndexController extends ControllerConstants {
    @Autowired
    @Qualifier("indexService")
    private ListService indexListService;

    @RequestMapping("/index")
    public String index(HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if (admin != null){
            map.put("admin", admin);
            return "/index";
        }
        return "/adminLogin";
    }

    @RequestMapping("/main")
    public String left(HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if (admin != null){
            map.put("admin", admin);
        }
        return "/index";
    }

    @RequestMapping("/mainPage")
    public String mainPage(ModelMap map){
        List<Object> list = (List<Object>) indexListService.list(null);

        return "/main";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Enumeration en = session.getAttributeNames();
        while (en.hasMoreElements()) {
            session.removeAttribute((String)en.nextElement());
        }
        return "redirect:/";
    }

}
