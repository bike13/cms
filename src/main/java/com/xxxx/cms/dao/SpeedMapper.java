package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.base.BaseQuery;
import com.xxxx.cms.vo.Speed;

import java.util.List;

public interface SpeedMapper extends BaseMapper<Speed,Integer>{

    List<Speed> selectByParam(BaseQuery baseQuery);

    int insertSpeed(Speed speed);

    int deleteSpeed(Integer[] ids);

    Speed selectSpeed(Integer speedId);

    Integer updateByPrimaryKeySelective(Speed speed);

    List<Speed> selectSpeedByUserId(Integer userId);
}