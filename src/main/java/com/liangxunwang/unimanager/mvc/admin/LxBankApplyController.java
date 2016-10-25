package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.mvc.vo.lxBankApplyVo;
import com.liangxunwang.unimanager.query.LxBankApplyQuery;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
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
@RequestMapping("/lxBankApplyController")
public class LxBankApplyController extends ControllerConstants {

    @Autowired
    @Qualifier("lxBankApplyService")
    private ListService lxBankApplyServiceList;

    @Autowired
    @Qualifier("lxBankApplyService")
    private ExecuteService lxBankApplyServiceExe;

    @Autowired
    @Qualifier("lxBankApplyService")
    private UpdateService lxBankApplyServiceEUpdate;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LxBankApplyQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        Object[] result = (Object[]) lxBankApplyServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/lxBankApply/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(HttpSession session,ModelMap map, String lx_bank_apply_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        lxBankApplyVo BankApplyVo = (lxBankApplyVo) lxBankApplyServiceExe.execute(lx_bank_apply_id);
        map.put("lxBankApplyVo", BankApplyVo);
        return "/lxBankApply/detail";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,lxBankApply lxBankApply){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            lxBankApplyServiceEUpdate.update(lxBankApply);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
