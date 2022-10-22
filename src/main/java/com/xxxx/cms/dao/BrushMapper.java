package com.xxxx.cms.dao;

import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.vo.Brush;

import java.util.List;

public interface BrushMapper extends BaseMapper<Brush,Integer> {
    /*查询所有的题目*/
    List<Brush> queryAllBrush();

    List<Brush> queryByParentId(Integer brushId);
}