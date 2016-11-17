package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
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
public class AppMembersController extends ControllerConstants {

    @Autowired
    @Qualifier("appMemberListService")
    private ListService appMemberListService;

    /**
     * 获得商家广告主页图列表
     * @return
     */
    @RequestMapping(value = "/appGetMembersByIds", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetMembersByIds(String hxUserNames){
        try {
            Object[] params = new Object[]{hxUserNames};
            List<Member> list = (List<Member>) appMemberListService.list(params);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

}
