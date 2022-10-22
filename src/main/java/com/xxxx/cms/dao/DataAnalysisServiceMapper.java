package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.query.ConfQuery;
import com.xxxx.cms.query.DataAnalysisQuery;
import com.xxxx.cms.vo.Speed;
import com.xxxx.cms.vo.Speed2;

import java.util.Date;
import java.util.List;

public interface DataAnalysisServiceMapper extends BaseMapper<Speed2,Integer> {
    List<DataAnalysisQuery> selectSpeed(Integer groupId, Date updateTime);
    Integer selectGroupId();
    Date[] selectUpdateTime();
    //根据组号,会议时间查询小组会议有关内容
    List<ConfQuery> selectConf(Integer groupId,Date updateTime);
    List<ConfQuery>  selectConfClass(Date updateTime);
}