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


    //公司部分信息 可能为空
    private String company_id;
    private String company_name;
    private String company_person;
    private String company_tel;
    private String company_address;
    private String lat_company;
    private String lng_company;
    private String company_pic;
    private String is_check;
    private String provinceid;
    private String cityid;
    private String areaid;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_person() {
        return company_person;
    }

    public void setCompany_person(String company_person) {
        this.company_person = company_person;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getLat_company() {
        return lat_company;
    }

    public void setLat_company(String lat_company) {
        this.lat_company = lat_company;
    }

    public String getLng_company() {
        return lng_company;
    }

    public void setLng_company(String lng_company) {
        this.lng_company = lng_company;
    }

    public String getCompany_pic() {
        return company_pic;
    }

    public void setCompany_pic(String company_pic) {
        this.company_pic = company_pic;
    }

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

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
