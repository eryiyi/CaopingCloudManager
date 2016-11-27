package com.liangxunwang.unimanager.query;

/**
 * 订单查询列表
 */
public class OrdersQuery {
    private int index;
    private int size;

    private String emp_id;
    private String seller_emp_id;
    private String status;
    private String pay_status;//付款状态
    private String is_comment;//是否评论了， 0否 1是

    public String getSeller_emp_id() {
        return seller_emp_id;
    }

    public void setSeller_emp_id(String seller_emp_id) {
        this.seller_emp_id = seller_emp_id;
    }

    public String getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(String is_comment) {
        this.is_comment = is_comment;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    private String emptype;

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
