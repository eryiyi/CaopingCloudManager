package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.ApplyGys;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.ApplyGysQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/applyGysController")
public class ApplyGysController extends ControllerConstants {

    @Autowired
    @Qualifier("applyGysService")
    private ListService applyGysServiceList;

    @Autowired
    @Qualifier("applyGysService")
    private UpdateService applyGysServiceUpdate;

    @Autowired
    @Qualifier("applyGysService")
    private ExecuteService applyGysServiceExe;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, ApplyGysQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] results = (Object[])  applyGysServiceList.list(query);

        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/applyGys/list";
    }


    @RequestMapping("/toCheck")
    public String toCheck(HttpSession session,ModelMap map, String apply_gys_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        ApplyGys applyGys = (ApplyGys) applyGysServiceExe.execute(apply_gys_id);
        map.put("info", applyGys);
        return "/applyGys/check";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/check", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String check(HttpSession session,ApplyGys applyGys){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            applyGysServiceUpdate.update(applyGys);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "审核失败，请稍后重试！"));
        }
    }



}
