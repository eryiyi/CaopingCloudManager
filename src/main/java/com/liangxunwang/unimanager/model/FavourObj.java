package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/11/9.
 */
public class FavourObj {
    private String favour_id;
    private String mm_msg_id;
    private String emp_id;
    private String dateline;

    private String emp_name;//点赞人姓名
    private String emp_cover;//点赞人头像

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_cover() {
        return emp_cover;
    }

    public void setEmp_cover(String emp_cover) {
        this.emp_cover = emp_cover;
    }

    public String getFavour_id() {
        return favour_id;
    }

    public void setFavour_id(String favour_id) {
        this.favour_id = favour_id;
    }

    public String getMm_msg_id() {
        return mm_msg_id;
    }

    public void setMm_msg_id(String mm_msg_id) {
        this.mm_msg_id = mm_msg_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
