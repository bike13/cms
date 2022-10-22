package com.xxxx.cms.query;

import com.xxxx.cms.base.BaseQuery;

import java.util.Date;

public class SpeedQuery extends BaseQuery {

    private Integer userId;

    private Date updateTime = new Date();

    private Date updateTime2 = new Date();

    public Date getUpdateTime2() {
        return updateTime2;
    }

    public void setUpdateTime2(Date updateTime2) {
        this.updateTime2 = updateTime2;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SpeedQuery{" +
                "userId=" + userId +
                ", updateTime=" + updateTime +
                '}';
    }
}
