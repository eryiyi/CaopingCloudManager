package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/8/18.
 */
public class PaopaoGoodsQuery {
    private int index;
    private int size;
    private String cont;
    private String typeId;
    private String empId;
    private String type;//商品类别标志
    private String isMine;//是否查询我的

    private String manager_id;
    private String lat_company;
    private String lng_company;
    private String is_time;//如果是1说明是最新排序
    private String is_count;//如果是1说明是按照销量排序
    private String isUse;
    private String is_dxk;

    public String getIs_dxk() {
        return is_dxk;
    }

    public void setIs_dxk(String is_dxk) {
        this.is_dxk = is_dxk;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getIs_count() {
        return is_count;
    }

    public void setIs_count(String is_count) {
        this.is_count = is_count;
    }

    public String getIs_time() {
        return is_time;
    }

    public void setIs_time(String is_time) {
        this.is_time = is_time;
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

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
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

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsMine() {
        return isMine;
    }

    public void setIsMine(String isMine) {
        this.isMine = isMine;
    }
}
