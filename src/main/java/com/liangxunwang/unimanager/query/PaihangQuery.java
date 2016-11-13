package com.liangxunwang.unimanager.query;

/**
 */
public class PaihangQuery{
    private int index;
    private int size;
    private String mm_paihang_id;
    private String cloud_caoping_id;
    private String is_del;
    private String keyWords;

    public String getMm_paihang_id() {
        return mm_paihang_id;
    }

    public void setMm_paihang_id(String mm_paihang_id) {
        this.mm_paihang_id = mm_paihang_id;
    }

    public String getCloud_caoping_id() {
        return cloud_caoping_id;
    }

    public void setCloud_caoping_id(String cloud_caoping_id) {
        this.cloud_caoping_id = cloud_caoping_id;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
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

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }
}
