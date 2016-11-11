package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class CzObjQuery {
    private int index;
    private int size;

    private String emp_id;
    private String cloud_caozhong_guige_id;
    private String cloud_caozhong_type_id;
    private String cloud_caozhong_is_use;
    private String cloud_caozhong_is_del;
    private String lat;
    private String lng;
    private String keyWords;
    private String is_time;//如果等于1 按时间最新排序
    private String is_count;//如果等于1 按销量排序

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

    public String getCloud_caozhong_guige_id() {
        return cloud_caozhong_guige_id;
    }

    public void setCloud_caozhong_guige_id(String cloud_caozhong_guige_id) {
        this.cloud_caozhong_guige_id = cloud_caozhong_guige_id;
    }

    public String getCloud_caozhong_type_id() {
        return cloud_caozhong_type_id;
    }

    public void setCloud_caozhong_type_id(String cloud_caozhong_type_id) {
        this.cloud_caozhong_type_id = cloud_caozhong_type_id;
    }

    public String getCloud_caozhong_is_use() {
        return cloud_caozhong_is_use;
    }

    public void setCloud_caozhong_is_use(String cloud_caozhong_is_use) {
        this.cloud_caozhong_is_use = cloud_caozhong_is_use;
    }

    public String getCloud_caozhong_is_del() {
        return cloud_caozhong_is_del;
    }

    public void setCloud_caozhong_is_del(String cloud_caozhong_is_del) {
        this.cloud_caozhong_is_del = cloud_caozhong_is_del;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getIs_time() {
        return is_time;
    }

    public void setIs_time(String is_time) {
        this.is_time = is_time;
    }

    public String getIs_count() {
        return is_count;
    }

    public void setIs_count(String is_count) {
        this.is_count = is_count;
    }
}
