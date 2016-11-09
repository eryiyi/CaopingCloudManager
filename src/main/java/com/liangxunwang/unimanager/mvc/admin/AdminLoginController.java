package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class AdminLoginController extends ControllerConstants {

    @Autowired
    @Qualifier("adminLoginService")
    private ExecuteService adminLoginService;

    @RequestMapping("/adminLogin")
    @ResponseBody
    public String adminLogin(HttpSession session,HttpServletRequest request, @Param(value = "username")String username,@Param(value = "password") String password){
        if (StringUtil.isNullOrEmpty(username)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        if (StringUtil.isNullOrEmpty(password)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        Object[] params = new Object[]{username, password};

        try {
            Object[] results = (Object[]) adminLoginService.execute(params);
            AdminVO admin = (AdminVO) results[0];
            session.setAttribute(ControllerConstants.ACCOUNT_KEY, admin);
            String permissions = (String) results[1];

            if(permissions == null || permissions.isEmpty()) {
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }
            session.setAttribute(ControllerConstants.PERMISSIONS, permissions);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("USERNAME_ERROR")){
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }
            if (msg.equals("PASSWORD_ERROR")){
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }else {
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }
        }
    }
}
