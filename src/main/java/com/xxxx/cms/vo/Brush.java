package com.xxxx.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Brush {
    private Integer brushId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String question;

    private Integer parentId;

    private Integer answerId;//answer表中的id值，用于在前台判断改题目是否已经作答

    private Integer count;

    private Integer num;

    public Integer getBrushId() {
        return brushId;
    }

    public void setBrushId(Integer brushId) {
        this.brushId = brushId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Brush{" +
                "brushId=" + brushId +
                ", createTime=" + createTime +
                ", question='" + question + '\'' +
                ", parentId=" + parentId +
                ", answerId=" + answerId +
                ", count=" + count +
                ", num=" + num +
                '}';
    }
}