package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.query.BugQuery;
import com.xxxx.cms.vo.Bug;

import java.util.List;

public interface BugMapper extends BaseMapper<Bug,Integer> {
    List<Bug> queryAllBugs(BugQuery bugQuery);

    int updateSolveByBugId(Integer bugId);

    Integer selectUserIdByBugId(Integer bugId);
}