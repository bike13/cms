package com.xxxx.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Speed {
    private Integer id;

    private Integer userId;

    private Integer videoSpStart;

    private Integer videoSpEnd;

    private Integer codeSpStart;

    private Integer codeSpEnd;

    private Integer  note;

    private String studyQuestion;

    private String classQuestion;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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

    public Integer  getNote() {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "id=" + id +
                ", userId=" + userId +
                ", videoSpStart=" + videoSpStart +
                ", videoSpEnd=" + videoSpEnd +
                ", codeSpStart=" + codeSpStart +
                ", codeSpEnd=" + codeSpEnd +
                ", note='" + note + '\'' +
                ", studyQuestion='" + studyQuestion + '\'' +
                ", classQuestion='" + classQuestion + '\'' +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}