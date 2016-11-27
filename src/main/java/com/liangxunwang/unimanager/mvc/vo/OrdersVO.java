package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Order;

/**
 * Created by zhl on 2015/8/20.
 * 后台订单列表详情
 */
public class OrdersVO extends Order {
    private String emp_mobile;
    private String emp_name;
    private String emp_cover;

    private String emp_mobile_seller;
    private String emp_name_seller;
    private String emp_cover_seller;

    private String provinceName;//省
    private String cityName;//市
    private String areaName;//区
    private String cloud_caoping_title;//商品名称
    private String cloud_caoping_pic;//商品图片
    private String cloud_caoping_address;//商品地址
    private String cloud_caoping_prices;//商品价格

    public String getEmp_mobile_seller() {
        return emp_mobile_seller;
    }

    public void setEmp_mobile_seller(String emp_mobile_seller) {
        this.emp_mobile_seller = emp_mobile_seller;
    }

    public String getEmp_name_seller() {
        return emp_name_seller;
    }

    public void setEmp_name_seller(String emp_name_seller) {
        this.emp_name_seller = emp_name_seller;
    }

    public String getEmp_cover_seller() {
        return emp_cover_seller;
    }

    public void setEmp_cover_seller(String emp_cover_seller) {
        this.emp_cover_seller = emp_cover_seller;
    }

    public String getCloud_caoping_pic() {
        return cloud_caoping_pic;
    }

    public void setCloud_caoping_pic(String cloud_caoping_pic) {
        this.cloud_caoping_pic = cloud_caoping_pic;
    }

    public String getCloud_caoping_address() {
        return cloud_caoping_address;
    }

    public void setCloud_caoping_address(String cloud_caoping_address) {
        this.cloud_caoping_address = cloud_caoping_address;
    }

    public String getCloud_caoping_prices() {
        return cloud_caoping_prices;
    }

    public void setCloud_caoping_prices(String cloud_caoping_prices) {
        this.cloud_caoping_prices = cloud_caoping_prices;
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

    public String getEmp_cover() {
        return emp_cover;
    }

    public void setEmp_cover(String emp_cover) {
        this.emp_cover = emp_cover;
    }

    public String getCloud_caoping_title() {
        return cloud_caoping_title;
    }

    public void setCloud_caoping_title(String cloud_caoping_title) {
        this.cloud_caoping_title = cloud_caoping_title;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


}
