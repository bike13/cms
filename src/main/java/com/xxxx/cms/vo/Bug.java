package com.xxxx.cms.vo;

import java.util.Date;

public class Bug {
    private Integer bugId;

    private Integer userId;

    private String bugTitle;

    private String bugContent;

    private String bugPicture;

    private Date createTime;

    private Integer solve;

    private String trueName;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle == null ? null : bugTitle.trim();
    }

    public String getBugContent() {
        return bugContent;
    }

    public void setBugContent(String bugContent) {
        this.bugContent = bugContent == null ? null : bugContent.trim();
    }

    public String getBugPicture() {
        return bugPicture;
    }

    public void setBugPicture(String bugPicture) {
        this.bugPicture = bugPicture == null ? null : bugPicture.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSolve() {
        return solve;
    }

    public void setSolve(Integer solve) {
        this.solve = solve;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + bugId +
                ", userId='" + userId + '\'' +
                ", bugTitle='" + bugTitle + '\'' +
                ", bugContent='" + bugContent + '\'' +
                ", bugPicture='" + bugPicture + '\'' +
                ", createTime=" + createTime +
                ", solve=" + solve +
                '}';
    }
}