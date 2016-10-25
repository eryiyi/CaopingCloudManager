package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Member;

/**
 * Created by zhl on 2015/1/31.
 */
public class MemberVO  extends Member {
    private String levelName;

    private String emp_mobile_up;
    private String emp_name_up;
    private String lx_attribute_nick;//分销等级昵称

    public String getLx_attribute_nick() {
        return lx_attribute_nick;
    }

    public void setLx_attribute_nick(String lx_attribute_nick) {
        this.lx_attribute_nick = lx_attribute_nick;
    }

    public String getEmp_mobile_up() {
        return emp_mobile_up;
    }

    public void setEmp_mobile_up(String emp_mobile_up) {
        this.emp_mobile_up = emp_mobile_up;
    }

    public String getEmp_name_up() {
        return emp_name_up;
    }

    public void setEmp_name_up(String emp_name_up) {
        this.emp_name_up = emp_name_up;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
