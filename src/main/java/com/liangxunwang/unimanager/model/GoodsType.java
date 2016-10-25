package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/2.
 *
 * 商品分类
 */

public class GoodsType {
    private String typeId;
    private String typeName;
    private String typeContent;
    private String typeIsUse;
    private String typeCover;
    private String type_num;
    private String type_is;

    public String getType_is() {
        return type_is;
    }

    public void setType_is(String type_is) {
        this.type_is = type_is;
    }

    public String getType_num() {
        return type_num;
    }

    public void setType_num(String type_num) {
        this.type_num = type_num;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getTypeIsUse() {
        return typeIsUse;
    }

    public void setTypeIsUse(String typeIsUse) {
        this.typeIsUse = typeIsUse;
    }

    public String getTypeCover() {
        return typeCover;
    }

    public void setTypeCover(String typeCover) {
        this.typeCover = typeCover;
    }
}
