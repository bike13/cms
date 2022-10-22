package com.xxxx.cms.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SpeedListQuery {

    private Integer id;

    private Integer userId;

    private String videoSp;

    private String codeSp;

    private Integer note;

    private String studyQuestion;

    private String classQuestion;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVideoSp() {
        return videoSp;
    }

    public void setVideoSp(String videoSp) {
        this.videoSp = videoSp;
    }

    public String getCodeSp() {
        return codeSp;
    }

    public void setCodeSp(String codeSp) {
        this.codeSp = codeSp;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getStudyQuestion() {
        return studyQuestion;
    }

    public void setStudyQuestion(String studyQuestion) {
        this.studyQuestion = studyQuestion;
    }

    public String getClassQuestion() {
        return classQuestion;
    }

    public void setClassQuestion(String classQuestion) {
        this.classQuestion = classQuestion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
