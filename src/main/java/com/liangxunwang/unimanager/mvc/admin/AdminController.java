package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
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

/**
 */
@Controller
public class AdminController extends ControllerConstants {

    @Autowired
    @Qualifier("adminService")
    private UpdateService adminUpdateService;

    @Autowired
    @Qualifier("adminService")
    private ListService adminUpdateServiceList;

    @Autowired
    @Qualifier("adminService")
    private ExecuteService adminExecuteService;

    @Autowired
    @Qualifier("adminRolesService")
    private ExecuteService adminRolesServiceExe;

    @Autowired
    @Qualifier("adminService")
    private FindService adminServiceFind;

    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(String empId, String roleId){
        if (StringUtil.isNullOrEmpty(empId) || StringUtil.isNullOrEmpty(roleId)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        Object[] params = new Object[]{empId, roleId};
        adminUpdateService.update(params);
        return toJSONString(SUCCESS);
    }


    @RequestMapping("/changePass")
    @ResponseBody
    public String changePass(String password, String id){
        try {
            Object[] params = new Object[]{id, password};
            adminExecuteService.execute(params);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    //赋权限
    @RequestMapping("/changroles")
    @ResponseBody
    public String changroles(String permissions, String id){
        try {
            Object[] params = new Object[]{id, permissions};
            adminRolesServiceExe.execute(params);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }

    @RequestMapping("/toChangePass")
    public String toChanagePass(){
        return "/admin/pass";
    }

    @RequestMapping("/admin/list")
    public String list(HttpSession session,ModelMap map, AdminQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) adminUpdateServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/admin/list";
    }

    @RequestMapping("/admin/detail")
    public String adminDetail(ModelMap map, String id) throws Exception {
        Object[] results = (Object[]) adminServiceFind.findById(id);
        if(results != null){
            AdminVO admin = (AdminVO) results[0];
            String permissions = (String) results[1];
            Role role  = (Role) results[2];
            map.put("admin", admin);
            if(role != null){
                map.put("role", role);
            }else {
                map.put("roleRname", "最高管理员");
            }
            map.put("permissions_admin", permissions);
        }
        return "/admin/detail";
    }

    @Autowired
    @Qualifier("roleService")
    private ListService roleService;

    @RequestMapping("/admin/role")
    public String adminRole(ModelMap map, String id) throws Exception {
        Object[] results = (Object[]) adminServiceFind.findById(id);
        if(results != null){
            AdminVO admin = (AdminVO) results[0];
            String permissions = (String) results[1];
            Role role  = (Role) results[2];
            map.put("admin", admin);
            if(role != null){
                map.put("role", role);
            }else {
                map.put("roleRname", "最高管理员");
            }
            map.put("permissions_admin", permissions);
        }
        //角色
        List<Role> roles = (List<Role>) roleService.list("");
        map.put("roles", roles);
        return "/admin/adminRole";
    }

    @RequestMapping("/admin/updateType")
    @ResponseBody
    public String updateType(HttpSession session, String id, String is_use ){
        try {
            Object[] params = new Object[]{id, is_use};
            //is_use :0 1 是启动和禁止管理员 2是删除管理员
            //更改管理员状态
            adminUpdateService.update(params);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


}
