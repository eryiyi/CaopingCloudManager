package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/2/5.
 */
public class GoodsCommentQuery {
    private int index;
    private int size;
    private String goodsId;
    private String goods_emp_id;
    private String emp_id;

    public String getGoods_emp_id() {
        return goods_emp_id;
    }

    public void setGoods_emp_id(String goods_emp_id) {
        this.goods_emp_id = goods_emp_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
