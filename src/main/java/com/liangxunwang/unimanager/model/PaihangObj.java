package com.liangxunwang.unimanager.model;

/**
 * Created by zhanghailong on 2016/3/23.
 */
public class PaihangObj {
    private String mm_paihang_id;
    private String cloud_caoping_id;//商品id
    private String top_num;
    private String is_del;
    private String end_time;


    public String getMm_paihang_id() {
        return mm_paihang_id;
    }

    public void setMm_paihang_id(String mm_paihang_id) {
        this.mm_paihang_id = mm_paihang_id;
    }

    public String getCloud_caoping_id() {
        return cloud_caoping_id;
    }

    public void setCloud_caoping_id(String cloud_caoping_id) {
        this.cloud_caoping_id = cloud_caoping_id;
    }

    public String getTop_num() {
        return top_num;
    }

    public void setTop_num(String top_num) {
        this.top_num = top_num;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
