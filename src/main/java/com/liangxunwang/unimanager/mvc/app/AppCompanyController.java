package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.CompanyQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.ListView;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppCompanyController extends ControllerConstants {

    @Autowired
    @Qualifier("appCompanyService")
    private ExecuteService appCompanyService;


    @Autowired
    @Qualifier("appCompanyService")
    private SaveService appCompanyServiceSave;


    @Autowired
    @Qualifier("appCompanyService")
    private UpdateService appCompanyServiceUpdate;

    @Autowired
    @Qualifier("appCompanyService")
    private ListService appCompanyServiceList;


    /**
     * 查询公司详情
     * @return
     */
    @RequestMapping(value = "/appGetCompanyDetail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCompanyDetail(String emp_id){
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(new ErrorTip(2, "暂无会员ID"));
        }
        try {
            Company company = (Company) appCompanyService.execute(emp_id);
            DataTip tip = new DataTip();
            tip.setData(company);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 保存公司详情或者更新公司详情
     * @return
     */
    @RequestMapping(value = "/appSaveCompanyDetail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveCompanyDetail(Company company){
        Company company1 = null;
        try {
            if(StringUtil.isNullOrEmpty(company.getCompany_id())){
                //没有ID说明是新增
                company.setCompany_id(UUIDFactory.random());
                company1 = (Company) appCompanyServiceSave.save(company);
            }else {
                //更新
                appCompanyServiceUpdate.update(company);
                company1 = company;
            }
            DataTip tip = new DataTip();
            tip.setData(company1);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 更新公司图片
     * @return
     */
    @RequestMapping(value = "/appSaveCompanyDetailPic", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveCompanyDetailPic(Company company){
        try {
                //更新
            appCompanyServiceUpdate.update(company);
            DataTip tip = new DataTip();
            tip.setData(company);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 查询名企排行
     * @return
     */
    @RequestMapping(value = "/appGetCompanyList", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCompanyList(CompanyQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

            query.setIs_gys("1");
            query.setIs_check("1");
            query.setIs_paihang("1");
            Object[] result = (Object[]) appCompanyServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }

    /**
     * 查询最新入驻企业
     * @return
     */
    @RequestMapping(value = "/appGetCompanyNews", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCompanyNews(CompanyQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            query.setIs_gys("1");
            query.setIs_check("1");
            query.setIs_time("1");
            Object[] result = (Object[]) appCompanyServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！"));
        }
    }
}
