package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/1/29.
 */
public class Admin {
    private String id;
    private String username;
    private String password;
    private String isUse;//是否禁用   0否  1 是
    private String permissions;
    private String emp_id;
    private String manager_cover;

    public String getManager_cover() {
        return manager_cover;
    }

    public void setManager_cover(String manager_cover) {
        this.manager_cover = manager_cover;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }
}
