package com.xxxx.cms.dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.xxxx.cms.base.BaseMapper;
import com.xxxx.cms.vo.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerMapper extends BaseMapper<Answer,Integer> {
    List<Map<String,String>> selectAnswerByBugId(Integer bugId);

    int deleteAnswerByBugId(Integer bugId);

    List<Integer> selectBatch(Integer[] bugIds);

    Integer deleteBatchAnswer(Integer[] ids);

    Answer queryByBrushIdAndUserId(Integer brushId, Integer userId);

    int updateAnswerByBrushIdAndUserId(String answer, Integer brushId, Integer userId);

}