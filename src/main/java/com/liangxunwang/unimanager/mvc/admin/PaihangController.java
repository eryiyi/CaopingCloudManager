package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CpObj;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
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

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/paihang")
public class PaihangController extends ControllerConstants {
    @Autowired
    @Qualifier("paihangService")
    private ListService recordService;

    @Autowired
    @Qualifier("paihangService")
    private DeleteService recordServiceDele;

    @Autowired
    @Qualifier("paihangService")
    private ExecuteService recordServiceExer;

    @Autowired
    @Qualifier("paihangService")
    private UpdateService recordServiceUpdate;

    @Autowired
    @Qualifier("paihangService")
    private SaveService paihangServiceSave;

    @Autowired
    @Qualifier("cpobjService")
    private ExecuteService cpobjServiceExe;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, PaihangQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) recordService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "paihang/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_paihang_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        recordServiceDele.delete(mm_paihang_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("toDetail")
    public String add(ModelMap map, PaihangQuery query) throws Exception {
        PaihangObjVO recordVO = (PaihangObjVO) recordServiceExer.execute(query);
        map.put("recordVO", recordVO);
        return "/paihang/detail";
    }

    //更改数据
    @RequestMapping(value = "/update", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateEmp( HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            recordServiceUpdate.update(paihangObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    @RequestMapping("toTuijian")
    public String toTuijian(ModelMap map, String cloud_caoping_id) throws Exception {
        CpObj cpObj = (CpObj) cpobjServiceExe.execute(cloud_caoping_id);
        map.put("cpObj", cpObj);
        return "/paihang/addpaihang";
    }

    /**
     * 添加排行榜
     */
    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(paihangObj.getCloud_caoping_id())){
            return toJSONString(new ErrorTip(1, "请检查产品ID是否存在！"));
        }
        try {
            paihangServiceSave.save(paihangObj);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("Has_exist")){
                //该商品已经添加到推荐
                return toJSONString(new ErrorTip(1, "该商品已经添加到推荐，不能重复添加！"));
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(new ErrorTip(1, "保存失败，请稍后重试！"));
            }
        }
        return toJSONString(SUCCESS);
    }

    //-------------------每天凌晨执行，查询是否有过期的推荐产品--------------------------
    public String update(){
        updatePaihangVip();
        return null;
    }

    @Autowired
    @Qualifier("paihangUpdateVipService")
    private UpdateService paihangUpdateVipService;

    @RequestMapping(value = "/updatePaihangVip", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updatePaihangVip(){
        try {
            paihangUpdateVipService.update("");
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }
    //-------------------------------------------------
}
