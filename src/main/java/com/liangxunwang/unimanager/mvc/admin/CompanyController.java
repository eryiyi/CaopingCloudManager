package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.model.LxCardObj;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.CompanyQuery;
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
@RequestMapping("/companyController")
public class CompanyController extends ControllerConstants {

    @Autowired
    @Qualifier("companyService")
    private ListService companyService;

    @Autowired
    @Qualifier("appCompanyService")
    private ExecuteService appCompanyService;


    @Autowired
    @Qualifier("appCompanyService")
    private UpdateService appCompanyServiceUpdate;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CompanyQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) companyService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/company/list";
    }


    @RequestMapping("/toCheck")
    public String toCheck(String emp_id, ModelMap map){
        try {
            Company company = (Company) appCompanyService.execute(emp_id);
            map.put("info", company);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/company/check";
    }



    /**
     * 更新审核
     */
    @RequestMapping(value = "/updateCheck", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateCheck(HttpSession session,Company company){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            appCompanyServiceUpdate.update(company);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }
}
