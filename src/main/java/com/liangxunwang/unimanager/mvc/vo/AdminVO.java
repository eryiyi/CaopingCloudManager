package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Admin;

/**
 * Created by zhl on 2016/9/15.
 */
public class AdminVO extends Admin {
    private String emp_number;
    private String empMobile;

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }
}
