package com.xxxx.cms.query;

import com.xxxx.cms.base.BaseQuery;

public class ConferenceQuestionsQuery extends BaseQuery {

   //会议id
    private Integer conferenceId;
    //接收人id
    private Integer recipientId;
    //组id
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }
}
