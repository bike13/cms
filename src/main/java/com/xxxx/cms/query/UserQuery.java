package com.xxxx.cms.query;

import com.xxxx.cms.base.BaseQuery;

public class UserQuery extends BaseQuery {
    private String userName;//用户名
    private String trueName;//真实姓名
    private String phone;//手机号码

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

