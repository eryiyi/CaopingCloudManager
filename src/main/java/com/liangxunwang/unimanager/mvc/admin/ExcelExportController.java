package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//到处excel相关
@Controller
public class ExcelExportController extends ControllerConstants {

    @Autowired
    @Qualifier("empExcelService")
    private ExecuteService empExcelService;

    //会员导出Excel表格数据
    @RequestMapping("memberExportExcel")
    @ResponseBody
    public String memberExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) empExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
        );
    }

    @Autowired
    @Qualifier("countExcelService")
    private ExecuteService countExcelService;

    //积分导出Excel表格数据
    @RequestMapping("countExportExcel")
    @ResponseBody
    public String countExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) countExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
        );
    }



    @Autowired
    @Qualifier("cardEmpExcelService")
    private ExecuteService cardEmpExcelService;

    //积分导出Excel表格数据
    @RequestMapping("cardEmpExportExcel")
    @ResponseBody
    public String cardEmpExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) cardEmpExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
        );
    }

    @Autowired
    @Qualifier("consumptionExcelService")
    private ExecuteService consumptionExcelService;

    //消费记录导出Excel表格数据
    @RequestMapping("consumptionExportExcel")
    @ResponseBody
    public String consumptionExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) consumptionExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
        );
    }


    @Autowired
    @Qualifier("bankApplyExcelService")
    private ExecuteService bankApplyExcelService;

    //申请记录导出Excel表格数据
    @RequestMapping("bankApplyExportExcel")
    @ResponseBody
    public String bankApplyExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) bankApplyExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
        );
    }

}
