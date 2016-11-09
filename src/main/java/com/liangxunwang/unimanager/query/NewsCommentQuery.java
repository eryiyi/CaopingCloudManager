package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/2/3.
 */
public class NewsCommentQuery {
    private int index;
    private int size;
    private String mm_msg_id;
    private String comment_emp_id;

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

    public String getMm_msg_id() {
        return mm_msg_id;
    }

    public void setMm_msg_id(String mm_msg_id) {
        this.mm_msg_id = mm_msg_id;
    }

    public String getComment_emp_id() {
        return comment_emp_id;
    }

    public void setComment_emp_id(String comment_emp_id) {
        this.comment_emp_id = comment_emp_id;
    }
}
