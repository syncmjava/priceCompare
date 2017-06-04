package com.elecom.crawler.entity;

import java.util.Date;

public class UserMaster {
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String delFg;

    private Date createTime;

    private String createUserId;

    private String createPgm;

    private Date updateIme;

    private String updateUserId;

    private String updatePgm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDelFg() {
        return delFg;
    }

    public void setDelFg(String delFg) {
        this.delFg = delFg == null ? null : delFg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreatePgm() {
        return createPgm;
    }

    public void setCreatePgm(String createPgm) {
        this.createPgm = createPgm == null ? null : createPgm.trim();
    }

    public Date getUpdateIme() {
        return updateIme;
    }

    public void setUpdateIme(Date updateIme) {
        this.updateIme = updateIme;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdatePgm() {
        return updatePgm;
    }

    public void setUpdatePgm(String updatePgm) {
        this.updatePgm = updatePgm == null ? null : updatePgm.trim();
    }
}