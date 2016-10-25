package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.CountRecordQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
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
@RequestMapping("/countRecordController")
public class CountRecordController extends ControllerConstants {

    @Autowired
    @Qualifier("countRecordService")
    private ListService countRecordService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CountRecordQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        Object[] result = (Object[]) countRecordService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/countRecord/list";
    }

}
