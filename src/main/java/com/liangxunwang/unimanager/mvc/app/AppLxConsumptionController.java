package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
import com.liangxunwang.unimanager.service.ListService;
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
public class AppLxConsumptionController extends ControllerConstants {
    @Autowired
    @Qualifier("lxConsumptionService")
    private ListService lxConsumptionServiceList;

    /**
     * 获得会员自己的消费记录
     * @return
     */
    @RequestMapping(value = "/appGetConsumption", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetConsumption(LxConsumptionQuery query ,Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            Object[] result = (Object[]) lxConsumptionServiceList.list(query);
            long count = (Long) result[1];

            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

}
