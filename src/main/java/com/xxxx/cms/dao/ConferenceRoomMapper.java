package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.vo.ConferenceRoom;

import java.util.List;

public interface ConferenceRoomMapper extends BaseMapper<ConferenceRoom,Integer> {

    //通过名称查会议房间
    ConferenceRoom selectByRoomNumber(Integer roomNumber);

    /**
     * 查询所有的房间
     * @return
     */
    List<Object> queryAllRoom();

}

