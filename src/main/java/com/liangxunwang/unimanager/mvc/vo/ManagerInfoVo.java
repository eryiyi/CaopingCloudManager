package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.ManagerInfo;

/**
 * Created by zhl on 2016/5/23.
 */
public class ManagerInfoVo extends ManagerInfo {
    private String emp_cover;
    private String emp_mobile;
    private String emp_name;
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getEmp_cover() {
        return emp_cover;
    }

    public void setEmp_cover(String emp_cover) {
        this.emp_cover = emp_cover;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
}
