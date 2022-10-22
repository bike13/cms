package com.xxxx.cms.query;

public class DataAnalysisResult<T> {
    private T result;
    private String speedVedioMin;//视频进度最慢
    private String speedCodeMin;//代码进度最慢
    private String noteMin;
    private String speedVedioAverage;//视频进度平均值
    private String speedCodeAverage;//代码进度平均值
    private String comprehensive;//综合评价
    private Integer groupId;
    private String signMin;
    private String question;
    private Integer questionState ;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    public String getSignMin() {
        return signMin;
    }

    public void setSignMin(String signMin) {
        this.signMin = signMin;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getNoteMin() {
        return noteMin;
    }

    public void setNoteMin(String noteMin) {
        this.noteMin = noteMin;
    }

    public String getSpeedVedioAverage() {
        return speedVedioAverage;
    }

    public void setSpeedVedioAverage(String speedVedioAverage) {
        this.speedVedioAverage = speedVedioAverage;
    }

    public String getSpeedCodeAverage() {
        return speedCodeAverage;
    }

    public void setSpeedCodeAverage(String speedCodeAverage) {
        this.speedCodeAverage = speedCodeAverage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getSpeedVedioMin() {
        return speedVedioMin;
    }

    public void setSpeedVedioMin(String speedVedioMin) {
        this.speedVedioMin = speedVedioMin;
    }

    public String getSpeedCodeMin() {
        return speedCodeMin;
    }

    public void setSpeedCodeMin(String speedCodeMin) {
        this.speedCodeMin = speedCodeMin;
    }

    public String getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(String comprehensive) {
        this.comprehensive = comprehensive;
    }
}
