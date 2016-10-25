package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.JifenObj;
import com.liangxunwang.unimanager.query.CountQuery;
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
import java.util.List;
//积分操作
@Controller
public class EmpCountController extends ControllerConstants {

    @Autowired
    @Qualifier("countService")
    private ListService countServiceList;

    @RequestMapping("/listCount")
    public String list(HttpSession session, ModelMap map, CountQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) countServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/count/list";
    }


    @Autowired
    @Qualifier("jifenObjService")
    private ExecuteService jifenObjServiceFind;


    @Autowired
    @Qualifier("jifenObjService")
    private UpdateService jifenObjServiceUpdate;

    //积分规则修改
    @RequestMapping("/toEditJifenGuize")
    public String toEditJifenGuize(HttpSession session, ModelMap map) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<JifenObj> jifenObjs = (List<JifenObj>) jifenObjServiceFind.execute("");
        if(jifenObjs != null && jifenObjs.size()>0){
            map.put("adObj", jifenObjs.get(0));
        }
        return "/count/editJfGuize";
    }

    /**
     * 更新
     */
    @RequestMapping("/editJifenGuize")
    @ResponseBody
    public String editJifenGuize(HttpSession session,JifenObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            jifenObjServiceUpdate.update(adObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


}
