package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class TransportQuery {
    private int index;
    private int size;
    private String emp_id;
    private String is_type;
    private String is_use;
    private String is_del;
    private String lat;
    private String lng;
    private String keyWords;

    private String areaidStart;//出发地
    private String areaidEnd;//抵达地

    public String getAreaidStart() {
        return areaidStart;
    }

    public void setAreaidStart(String areaidStart) {
        this.areaidStart = areaidStart;
    }

    public String getAreaidEnd() {
        return areaidEnd;
    }

    public void setAreaidEnd(String areaidEnd) {
        this.areaidEnd = areaidEnd;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
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

    public String getIs_type() {
        return is_type;
    }

    public void setIs_type(String is_type) {
        this.is_type = is_type;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }
}
