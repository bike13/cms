package com.xxxx.cms.query;

import java.util.Date;
import java.util.List;

public class DataAnalysisQuery {
    private String videoSpStart;
    private String videoSpEnd;
    private String codeSpStart;
    private String codeSpEnd;
    private String note;
    private String trueName;
    private Integer groupId;
    private Date updateTime;
    private String sign;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVideoSpStart() {
        return videoSpStart;
    }

    public void setVideoSpStart(String videoSpStart) {
        this.videoSpStart = videoSpStart;
    }

    public String getVideoSpEnd() {
        return videoSpEnd;
    }

    public void setVideoSpEnd(String videoSpEnd) {
        this.videoSpEnd = videoSpEnd;
    }

    public String getCodeSpStart() {
        return codeSpStart;
    }

    public void setCodeSpStart(String codeSpStart) {
        this.codeSpStart = codeSpStart;
    }

    public String getCodeSpEnd() {
        return codeSpEnd;
    }

    public void setCodeSpEnd(String codeSpEnd) {
        this.codeSpEnd = codeSpEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserName() {
        return trueName;
    }

    public void setUserName(String userName) {
        this.trueName = userName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
