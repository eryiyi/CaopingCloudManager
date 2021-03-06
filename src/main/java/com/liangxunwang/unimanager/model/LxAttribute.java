package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/10/4.
 * 三级分销的等级  三个等级划分
 */
public class LxAttribute {
    private String lx_attribute_id;//等级Id
    private String lx_attribute_name;//等级名称 不可改
    private String lx_attribute_nick;//别称，这个名称可以改，展示给会员用

    public String getLx_attribute_id() {
        return lx_attribute_id;
    }

    public void setLx_attribute_id(String lx_attribute_id) {
        this.lx_attribute_id = lx_attribute_id;
    }

    public String getLx_attribute_name() {
        return lx_attribute_name;
    }

    public void setLx_attribute_name(String lx_attribute_name) {
        this.lx_attribute_name = lx_attribute_name;
    }

    public String getLx_attribute_nick() {
        return lx_attribute_nick;
    }

    public void setLx_attribute_nick(String lx_attribute_nick) {
        this.lx_attribute_nick = lx_attribute_nick;
    }
}
