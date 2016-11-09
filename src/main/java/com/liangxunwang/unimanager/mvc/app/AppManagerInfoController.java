package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppManagerInfoController extends ControllerConstants {
    @Autowired
    @Qualifier("managerInfoService")
    private SaveService managerInfoServiceSave;

    @Autowired
    @Qualifier("managerInfoService")
    private ListService managerInfoServiceList;

    @Autowired
    @Qualifier("appManagerInfoService")
    private ListService appManagerInfoServiceList;

    /**
     * 保存申请
     * @return
     */
    @RequestMapping(value = "/saveManagerInfo", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String saveManagerInfo(ManagerInfo managerInfo){
        try {
            managerInfoServiceSave.save(managerInfo);
            return toJSONString(SUCCESS);//保存成功
        }catch (ServiceException e){

            if (e.getMessage().equals("hax_exist_info")){
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }else
            if (e.getMessage().equals("is_error")){
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }else{
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }

        }
    }

    /**
     * 获得附近店铺
     * @return
     */
    @RequestMapping(value = "/appGetNearbyDianpu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetNearbyDianpu(ManagerInfoVo managerInfoVo){
        try {
            List<ManagerInfoVo> list = (List<ManagerInfoVo>) appManagerInfoServiceList.list(managerInfoVo);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


    @Autowired
    @Qualifier("managerInfoService")
    private ExecuteService managerInfoServiceExe;

    /**
     * 获得店铺详情
     * @return
     */
    @RequestMapping(value = "/appGetDianpuDetailByEmpId", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetDianpuDetailByEmpId(String emp_id) throws Exception {
        try {
            ManagerInfoVo managerInfoVo = (ManagerInfoVo) managerInfoServiceExe.execute(emp_id);
            DataTip tip = new DataTip();
            tip.setData(managerInfoVo);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }
}
