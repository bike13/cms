package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.query.ConferenceNowQuery;
import com.xxxx.cms.vo.Conference;

import java.util.List;
import java.util.Map;

public interface ConferenceMapper extends BaseMapper<Conference,Integer> {
    /**
     * 查询所有的等级
     * @return
     */
    List<Object> queryAllRank();

    //查会议详情信息
    String queryDetailsById(Integer id);

    //查本组会议 rank_id=2  userId对应对象的组id 查所有对象,包含创建人的姓名
    List<Conference> queryGroupConferenceByNow(ConferenceNowQuery conferenceNowQuery);

    //查班级会议 rank_id=1
    List<Conference> queryClassConferenceByNow(ConferenceNowQuery conferenceNowQuery);
}