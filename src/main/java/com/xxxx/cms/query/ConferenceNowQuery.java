package com.xxxx.cms.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxxx.cms.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConferenceNowQuery extends BaseQuery {

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String createTimeStr;

    private Integer roomNumber;

    private Integer rankId;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
