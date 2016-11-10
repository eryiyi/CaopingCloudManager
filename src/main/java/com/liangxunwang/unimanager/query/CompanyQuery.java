package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class CompanyQuery {
    private int index;
    private int size;
    private String emp_id;
    private String is_check;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String is_paihang;

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }

    public String getIs_paihang() {
        return is_paihang;
    }

    public void setIs_paihang(String is_paihang) {
        this.is_paihang = is_paihang;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
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
}
