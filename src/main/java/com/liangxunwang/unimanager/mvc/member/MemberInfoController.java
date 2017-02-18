package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.PayAmountObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhl on 2015/2/8.
 */
@Controller
public class MemberInfoController extends ControllerConstants {
    @Autowired
    @Qualifier("memberInfoService")
    private ExecuteService listMemberInfoService;

    @Autowired
    @Qualifier("memberInfoService")
    private FindService findMemberService;

    @Autowired
    @Qualifier("memberInfoService")
    private UpdateService updateMemberService;

    @Autowired
    @Qualifier("memberInfoService")
    private ListService memberInfoListService;

    @RequestMapping(value = "/listMemberInfo", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getMemberInfo(String phoneStr){
        try {
            List<Member> list = (List<Member>) listMemberInfoService.execute(phoneStr);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping(value = "/getMemberInfoById", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getMemberInfoById(String empId){
        try {
            MemberVO memberVO = (MemberVO) findMemberService.findById(empId);
            DataTip tip = new DataTip();
            tip.setData(memberVO);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping("/updatePushId")
    @ResponseBody
    public String updatePushId(@RequestParam String id, @RequestParam String pushId, @RequestParam String type, @RequestParam String channelId){
        if (StringUtil.isNullOrEmpty(id) || StringUtil.isNullOrEmpty(pushId) || StringUtil.isNullOrEmpty(type)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        Object[] params = new Object[]{id, pushId, type,channelId};
        try {
            updateMemberService.update(params);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );

        }
    }

    @RequestMapping(value = "/searchMember", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String searchMember(MemberQuery query, Page page){
        try {
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            Object[] params = new Object[]{query, page.getPage()};
            List<Member> list = (List<Member>) memberInfoListService.list(params);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping(value = "/listInviteMemberInfo", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getInviteMemberInfo(String hxUserNames){
        try {
            Object[] params = new Object[]{hxUserNames};
            List<Member> list = (List<Member>) listMemberInfoService.execute(params);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        } catch (Exception e) {
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


    @Autowired
    @Qualifier("appEmpService")
    private ExecuteService appEmpService;


    @RequestMapping(value = "/getMemberByMobile", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getMemberByMobile(String mm_emp_mobile) throws Exception {
        try {
            //查看该会员信息
            MemberVO empVO = (MemberVO) appEmpService.execute(mm_emp_mobile);
            if(empVO != null){
                //说明该手机号已经注册了
                DataTip tip = new DataTip();
                tip.setData(empVO);
                return toJSONString(tip);
            }else {
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }



    @Autowired
    @Qualifier("memberInfoService")
    private FindService memberInfoService;

    @Autowired
    @Qualifier("appOrderSumService")
    private ExecuteService appOrderSumService;

    @Autowired
    @Qualifier("appCpobjService")
    private FindService appCpobjService;

    @Autowired
    @Qualifier("appOrderGoodsSumService")
    private ExecuteService appOrderGoodsSumService;

    @Autowired
    @Qualifier("dianpuFavourService")
    private ExecuteService dianpuFavourService;

    //个人中心页获得加入的天数和消费多少元 收入多少元
    @RequestMapping(value = "/getProfileMemberInfo", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getProfileMemberInfo(String emp_id) throws Exception {
        try {
            //查看该会员信息
            MemberVO empVO = (MemberVO) memberInfoService.findById(emp_id);
            String dateline = empVO.getDateline();//注册时间

            String reg_date = DateUtil.getDate(dateline, "yyyy-MM-dd");
            String current_date = DateUtil.getCurrentDateTime2();//获得当前日期

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long to = df.parse(current_date).getTime();
            long from = df.parse(reg_date).getTime();
            long dayNumbers = (to - from) / (1000 * 60 * 60 * 24);//注册的天数

            String[] arras = {empVO.getEmpId(),""};
            String[] arras2 = {"",empVO.getEmpId()};
            String orderNumOne = (String) appOrderSumService.execute(arras);
            String orderNumTwo = (String) appOrderSumService.execute(arras2);

            //数量--发布的
            String cpNumber = (String) appCpobjService.findById(empVO.getEmpId());

            String goodsCountOne = (String) appOrderGoodsSumService.execute(arras);//买的商品数量
            String goodsCountTwo = (String) appOrderGoodsSumService.execute(arras2);//卖的商品数量

//            Company company = (Company) appCompanyService.execute(empVO.getEmpId());
            String fensiNumber = (String) dianpuFavourService.execute(empVO.getEmpId());

            PayAmountObj payAmountObj = new PayAmountObj();
            payAmountObj.setRuzhuNumber(String.valueOf(dayNumbers));
            payAmountObj.setShouruAmount(orderNumTwo);
            payAmountObj.setZhichuAmount(orderNumOne);
            payAmountObj.setCpNumber(cpNumber);
            payAmountObj.setGoodsCountOne(goodsCountOne);
            payAmountObj.setGoodsCountTwo(goodsCountTwo);
//            if(company != null){
//                payAmountObj.setZhimingdu(company.getPaihang_num()==null?"0":company.getPaihang_num());
//            }else {
//                payAmountObj.setZhimingdu("0");
//            }
            payAmountObj.setFensiNumber(fensiNumber);
            DataTip tip = new DataTip();
            tip.setData(payAmountObj);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

}
