package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.query.CardEmpQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
//定向卡会员
@Controller
@RequestMapping("/cardEmpController")
public class CardEmpController extends ControllerConstants {

    @Autowired
    @Qualifier("cardEmpService")
    private ListService cardEmpServiceList;

    @Autowired
    @Qualifier("cardEmpService")
    private SaveService cardEmpServiceSave;

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpServiceExe;

    @Autowired
    @Qualifier("cardEmpService")
    private UpdateService cardEmpServiceUpdate;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CardEmpQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) cardEmpServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/cardEmp/list";
    }

//    @RequestMapping("/toDetail")
//    public String toDetail(HttpSession session,ModelMap map, String lx_consumption_id) throws Exception {
//        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
//        LxConsumption lxConsumption = (LxConsumption) levelServiceSaveExe.execute(lx_consumption_id);
//        map.put("lxConsumption", lxConsumption);
//        return "/lxConsumption/detail";
//    }

}
