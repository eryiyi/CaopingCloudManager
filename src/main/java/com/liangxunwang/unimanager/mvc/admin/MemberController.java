package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LxAttribute;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/1/31.
 */
@Controller
public class MemberController extends ControllerConstants {
    /**
     * @see com.liangxunwang.unimanager.service.account.MemberService
     */
    @Autowired
    @Qualifier("memberService")
    private ListService memberListService;

    @Autowired
    @Qualifier("memberService")
    private FindService memberFindService;

    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdServiceFind;//根据用户id查询用户信息

    @Autowired
    @Qualifier("memberService")
    private UpdateService updateMemberService;

    @Autowired
    @Qualifier("memberService")
    private ExecuteService executeMemberService;

    @Autowired
//    @Qualifier("hXMemberService")
    @Qualifier("hxMemberService")
    private UpdateService hxMemberUpdateService;

    @Autowired
    @Qualifier("levelService")
    private ListService levelServiceList;

    @RequestMapping("/ajax/listMember")
    public String listMember(ModelMap map, MemberQuery query, Page page, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) memberListService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //查询会员等级列表
        List<Level> levels = (List<Level>) levelServiceList.list("");
        map.put("levels", levels);
        return "/member/listMember";
    }

    /**
     * 根据学习ID查询所有的会员信息
     * @param schoolId
     * @return
     */
    @RequestMapping(value = "/listMemberBySchool", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String listMemberBySchool(String schoolId){
        try {
            schoolId = "";
            List<Member> list = (List<Member>) memberListService.list(schoolId);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


    /**
     * 查找推荐好友
     * @param query
     * @param page
     * @return
     */
    @RequestMapping(value = "/searchRecommend", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String searchRecommend(MemberQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            Object[] result = (Object[]) memberListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    /**
     * 根据会员ID   更改是否为管理员
     * @param empId
     * @param flag
     * @return
     */
    @RequestMapping("/ajax/changeAdmin")
    @ResponseBody
    public String changeAdmin(@RequestParam String empId,@RequestParam String flag){
        Object[] params = new Object[]{empId, flag};
        try {
            updateMemberService.update(params);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/ajax/changeBusiness")
    @ResponseBody
    public String changeBusiness(@RequestParam String empId,@RequestParam String flag){
        Object[] params = new Object[]{empId, flag};
        try {
            executeMemberService.execute(params);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        return toJSONString(SUCCESS);
    }

    /**
     * 环信添加用户到组
     * @return
     */
    @RequestMapping("/addUserToGroup")
    @ResponseBody
    public String updateHx(String hxUserName, String coid, String empId){
        Object[] params = new Object[]{hxUserName, coid, empId};
        hxMemberUpdateService.update(params);
        return toJSONString(SUCCESS);
    }


    /**
     * 根据手机号查找会员信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findMemberByPhone", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findMemberByPhone(String phone){
        MemberVO member = (MemberVO) memberFindService.findById(phone);
        if (member == null){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        DataTip tip = new DataTip();
        tip.setData(member);
        return toJSONString(tip);
    }

    @Autowired
    @Qualifier("levelService")
    private ListService levelService;

    @Autowired
    @Qualifier("lxAttributeService")
    private ListService lxAttributeServiceList;

    @RequestMapping("/toDetailEmp")
    public String toDetailEmp(String emp_mobile, ModelMap map){
        MemberVO member = (MemberVO) memberFindService.findById(emp_mobile);
        map.put("empVO", member);
        //查找购买等级 打折用
        List<Level> listLevels = (List<Level>) levelService.list(null);
        map.put("listLevels", listLevels);
        //往上数三级
        String up_emp_id = member.getEmp_up();
        if (!StringUtil.isNullOrEmpty(up_emp_id)){
            //上级
            MemberVO memberVOUpOne = (MemberVO) memberFindByIdServiceFind.findById(up_emp_id);
            if(memberVOUpOne != null){
                map.put("memberVOUpOne", memberVOUpOne);//上级one
                if(memberVOUpOne != null){
                    if(!StringUtil.isNullOrEmpty(memberVOUpOne.getEmp_up())){
                        MemberVO memberVOUpTwo = (MemberVO) memberFindByIdServiceFind.findById(memberVOUpOne.getEmp_up());
                        if(memberVOUpTwo != null){
                            map.put("memberVOUpTwo", memberVOUpTwo);
                        }
                    }
                }
            }
        }
        //查找分销等级 返利用
        List<LxAttribute> listAttribute = (List<LxAttribute>) lxAttributeServiceList.list("");
        map.put("listAttribute", listAttribute);
        return "/member/detail";
    }


    @Autowired
    @Qualifier("memberUpdateByIdService")
    private UpdateService memberUpdateByIdService;

    /**
     * 更新用户数据
     * @return
     */
    @RequestMapping(value = "/updateEmpById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateEmpById(Member member){
        memberUpdateByIdService.update(member);
        return toJSONString(SUCCESS);
    }


}
