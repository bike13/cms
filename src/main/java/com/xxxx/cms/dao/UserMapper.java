package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);

    List<User> queryAll();

    //通过用户名查询用户记录，返回用户对象
    User queryUserByName(String userName);
    //查本次会议的参与人员
    List<Map<String,Object>> queryParticipants();
    //根据真实姓名查用户
    User queryUserByTrueName(String recipientname);

}