package com.xxxx.cms.query;

import com.xxxx.cms.base.BaseQuery;

public class ConferenceQuery extends BaseQuery {
    //等级
    private Integer rankId;
    //主题
    private String theme;
    //房间号
    private Integer roomNumber;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

}
