package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.ApplyGysQuery;
import com.liangxunwang.unimanager.query.CpObjQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController extends ControllerConstants {

    @Autowired
    @Qualifier("cpobjService")
    private ListService cpobjServiceList;

    @Autowired
    @Qualifier("cpobjService")
    private ExecuteService cpobjServiceExe;


    @Autowired
    @Qualifier("cpobjService")
    private UpdateService cpobjServiceUpdate;

    @Autowired
    @Qualifier("cpguigeService")
    private ListService cpguigeService;


    @Autowired
    @Qualifier("cptypeService")
    private ListService cptypeService;

    @Autowired
    @Qualifier("appCpuseService")
    private ListService appCpuseService;

    @Autowired
    @Qualifier("cpCaozhongGuigeService")
    private ListService cpCaozhongGuigeService;


    @Autowired
    @Qualifier("caozhongTypeService")
    private ListService caozhongTypeService;

    @Autowired
    @Qualifier("cpJixieguigeService")
    private ListService cpJixieguigeService;


    @Autowired
    @Qualifier("cpJixieUseService")
    private ListService cpJixieUseService;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CpObjQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        query.setIs_time("1");
        Object[] results = (Object[])  cpobjServiceList.list(query);

        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/product/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String cloud_caoping_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CpObj cpObj = (CpObj) cpobjServiceExe.execute(cloud_caoping_id);
        map.put("goods", cpObj);
        //查询7个属性
        List<Cpguige> listCpGg = (List<Cpguige>) cpguigeService.list("");
        List<Cptype> listCpType = (List<Cptype>) cptypeService.list("");
        List<Cpuse> listCpUse = (List<Cpuse>) appCpuseService.list("");
        List<CpCaozhongguige> listCzGg = (List<CpCaozhongguige>) cpCaozhongGuigeService.list("");
        List<CaozhongType> listCzType = (List<CaozhongType>) caozhongTypeService.list("");
        List<CpJxguige> listJxGg = (List<CpJxguige>) cpJixieguigeService.list("");
        List<CpJixieuse> listJxUse = (List<CpJixieuse>) cpJixieUseService.list("");
        map.put("listCpGg", listCpGg);
        map.put("listCpType", listCpType);
        map.put("listCpUse", listCpUse);
        map.put("listCzGg", listCzGg);
        map.put("listCzType", listCzType);
        map.put("listJxGg", listJxGg);
        map.put("listJxUse", listJxUse);

        List<String> listpic = new ArrayList<String>();
        if(cpObj != null){
            if(!StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_pic())){
                String[] arras = cpObj.getCloud_caoping_pic().split(",");
                if(arras != null){
                    for(String str:arras){
                        listpic.add(str);
                    }
                }
            }
        }
        map.put("listpic", listpic);
        return "/product/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/edit", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String update(HttpSession session,CpObj cpObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(cpObj.getCloud_caoping_id())){
            return toJSONString(new ErrorTip(1, "请确认产品ID是否存在！"));
        }
        try {
            cpobjServiceUpdate.update(cpObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

}
