package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
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
@RequestMapping("/lxConsumptionController")
public class LxConsumptionController extends ControllerConstants {

    @Autowired
    @Qualifier("lxConsumptionService")
    private ListService lxConsumptionServiceList;

    @Autowired
    @Qualifier("lxConsumptionService")
    private SaveService lxConsumptionServiceSave;

    @Autowired
    @Qualifier("lxConsumptionService")
    private ExecuteService levelServiceSaveExe;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LxConsumptionQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) lxConsumptionServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/lxConsumption/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(HttpSession session,ModelMap map, String lx_consumption_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        LxConsumption lxConsumption = (LxConsumption) levelServiceSaveExe.execute(lx_consumption_id);
        map.put("lxConsumption", lxConsumption);
        return "/lxConsumption/detail";
    }


    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdService;

    //后台零钱充值
    @RequestMapping("/toChongzhi")
    public String toChongzhi(HttpSession session,ModelMap map, String emp_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        MemberVO memberVO = (MemberVO) memberFindByIdService.findById(emp_id);
        map.put("memberVO", memberVO);
        return "/lxConsumption/chongzhi";
    }

    @Autowired
    @Qualifier("lxCardObjService")
    private ListService lxCardObjServicelist;

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpServiceExe;

    //后台定向充值卡充值
    @RequestMapping("/toChongzhiDxk")
    public String toChongzhiDxk(HttpSession session,ModelMap map, String emp_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        //查询会员详情
        MemberVO memberVO = (MemberVO) memberFindByIdService.findById(emp_id);
        map.put("memberVO", memberVO);
        //查看该会员是第几次定向卡充值
        CardEmp cardEmp = (CardEmp) cardEmpServiceExe.execute(emp_id);
        //2.查询定向卡规则
        List<LxCardObj> listCard = (List<LxCardObj>) lxCardObjServicelist.list("");
        LxCardObj lxCardObj = null;
        if(listCard != null && listCard.size()>0){
            lxCardObj = listCard.get(0);
        }
        if(cardEmp != null){
            //说明有这个数据  已经是定向卡会员了
            String countMoney = lxCardObj.getLx_card_count();//规定总金额
            String dijianMoney = lxCardObj.getLx_card_dijian();//规定递减
            String lowMoney = lxCardObj.getLx_card_low();//规定最低金额
            String year= cardEmp.getLx_card_emp_year();//第几年
            Double m = Double.valueOf(countMoney) - Double.valueOf(dijianMoney)*Integer.parseInt(year);//总金额减去 递减的
            if(m<Double.valueOf(lowMoney)){
                m = Double.valueOf(lowMoney);
            }
            map.put("money" , String.valueOf(m));
        }else{
            //说明不是定向卡会员 第一次充值定向卡 充值金额为满额
            map.put("money" ,lxCardObj.getLx_card_count());
        }
        return "/lxConsumption/chongzhidxk";
    }

    @Autowired
    @Qualifier("minePackageService")
    private UpdateService minePackageServiceUpdate;

    @Autowired
    @Qualifier("jifenObjService")
    private ExecuteService jifenObjServiceExe;

    @Autowired
    @Qualifier("countService")
    private UpdateService countServiceUpdate;

    @Autowired
    @Qualifier("countRecordService")
    private SaveService countRecordServiceSave;

    /**
     * 充值  记录  积分增加 积分记录
     */
    @RequestMapping("/chongzhiLq")
    @ResponseBody
    public String chongzhiLq(HttpSession session,LxConsumption lxConsumption) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            //充值
            if(StringUtil.isNullOrEmpty(lxConsumption.getLx_consumption_count()) || "0".equals(lxConsumption.getLx_consumption_count())){
                return toJSONString(new ErrorTip(1, "金额不能为空！"));
            }
            if(StringUtil.isNullOrEmpty(lxConsumption.getEmp_id())){
                return toJSONString(new ErrorTip(1, "会员ID不存在，请检查会员！"));
            }
            MinePackage minePackage = new MinePackage();
            minePackage.setEmp_id(lxConsumption.getEmp_id());//会员ID
            minePackage.setPackage_money(lxConsumption.getLx_consumption_count());//充值金额
            minePackageServiceUpdate.update(minePackage);//零钱更新
            //增加充值记录
            if("1".equals(lxConsumption.getLx_consumption_type())){
                lxConsumption.setLx_consumption_cont("后台零钱充值，金额" + lxConsumption.getLx_consumption_count());
            }else if("3".equals(lxConsumption.getLx_consumption_type())){
                lxConsumption.setLx_consumption_cont("后台定向卡充值，金额" + lxConsumption.getLx_consumption_count());
            }
            lxConsumptionServiceSave.save(lxConsumption);//消费记录
            //充值成功 ，给上级增加积分
            //1.查询上级
            MemberVO memberVO = (MemberVO) memberFindByIdService.findById(lxConsumption.getEmp_id());
            if(memberVO != null && !StringUtil.isNullOrEmpty(memberVO.getEmp_up())){
                //说明存在上级
                //2.更新上级积分
                List<JifenObj> listJifens = (List<JifenObj>) jifenObjServiceExe.execute("");//查询推广下线后台充值金额 给上级增加积分的百分率
                if(listJifens != null && listJifens.size()>0){
                    JifenObj jifenObj = listJifens.get(0);//积分规则
                    if(jifenObj != null){
                        String lx_jifen_one = jifenObj.getLx_jifen_one();//推广下线充值金额的X%
                        if(!StringUtil.isNullOrEmpty(lx_jifen_one)){
                            Double jifenCount = Double.parseDouble(lxConsumption.getLx_consumption_count())*(Double.parseDouble(lx_jifen_one)*0.01);//增加的积分
                            //更新上级积分
                            String[] arr = {memberVO.getEmp_up(), String.valueOf(jifenCount)};
                            countServiceUpdate.update(arr);//更新上级积分
                            //添加积分变动记录
                            CountRecord countRecord = new CountRecord();
                            countRecord.setEmp_id(memberVO.getEmp_up());
                            countRecord.setLx_count_record_count("+"+String.valueOf(jifenCount));
                            if("1".equals(lxConsumption.getLx_consumption_type())){
                                countRecord.setLx_count_record_cont(memberVO.getEmpName()+"("+memberVO.getEmpMobile()+")后台充值零钱" + lxConsumption.getLx_consumption_count()+"元");
                            }else if("3".equals(lxConsumption.getLx_consumption_type())){
                                countRecord.setLx_count_record_cont(memberVO.getEmpName()+"("+memberVO.getEmpMobile()+")定向卡充值" + lxConsumption.getLx_consumption_count()+"元");
                            }
                            countRecordServiceSave.save(countRecord);
                        }
                    }
                }
            }
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "充值失败！"));
        }
    }




}
