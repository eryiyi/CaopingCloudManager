package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PaopaoGoods;

/**
 * Created by zhl on 2015/8/18.
 */
public class PaopaoGoodsVO extends PaopaoGoods {
    private String nickName;
    private String empCover;

    private String managerName;
    private String managerCover;
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerCover() {
        return managerCover;
    }

    public void setManagerCover(String managerCover) {
        this.managerCover = managerCover;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }


}
