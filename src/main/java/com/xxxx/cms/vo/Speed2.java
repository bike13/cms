package com.xxxx.cms.vo;

import java.util.Date;

public class Speed2 {
    private Integer id;

    private Integer userId;

    private Integer videoSpStart;

    private Integer videoSpEnd;

    private Integer codeSpStart;

    private Integer codeSpEnd;

    private String note;

    private String studyQuestion;

    private String classQuestion;

    private String remark;

    private Date updateTime;

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

    public Integer getVideoSpStart() {
        return videoSpStart;
    }

    public void setVideoSpStart(Integer videoSpStart) {
        this.videoSpStart = videoSpStart;
    }

    public Integer getVideoSpEnd() {
        return videoSpEnd;
    }

    public void setVideoSpEnd(Integer videoSpEnd) {
        this.videoSpEnd = videoSpEnd;
    }

    public Integer getCodeSpStart() {
        return codeSpStart;
    }

    public void setCodeSpStart(Integer codeSpStart) {
        this.codeSpStart = codeSpStart;
    }

    public Integer getCodeSpEnd() {
        return codeSpEnd;
    }

    public void setCodeSpEnd(Integer codeSpEnd) {
        this.codeSpEnd = codeSpEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getStudyQuestion() {
        return studyQuestion;
    }

    public void setStudyQuestion(String studyQuestion) {
        this.studyQuestion = studyQuestion == null ? null : studyQuestion.trim();
    }

    public String getClassQuestion() {
        return classQuestion;
    }

    public void setClassQuestion(String classQuestion) {
        this.classQuestion = classQuestion == null ? null : classQuestion.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}