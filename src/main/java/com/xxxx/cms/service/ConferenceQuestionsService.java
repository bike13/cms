package com.xxxx.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.ConferenceQuestionsMapper;
import com.xxxx.cms.dao.UserMapper;
import com.xxxx.cms.query.ConferenceQuestionsQuery;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.vo.ConferenceQuestions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConferenceQuestionsService extends BaseService<ConferenceQuestions,Integer> {
    @Resource
    private ConferenceQuestionsMapper conferenceQuestionsMapper;
    @Resource
    private UserMapper userMapper;

    public Map<String, Object> queryQuestionsByRecipientId(ConferenceQuestionsQuery conferenceQuestionsQuery) {
        Map<String,Object> map=new HashMap<>();
        //开启分页
        PageHelper.startPage(conferenceQuestionsQuery.getPage(),conferenceQuestionsQuery.getLimit());
        //分页对象  返回的对象集合
        PageInfo<ConferenceQuestions> pageInfo=new PageInfo<>(conferenceQuestionsMapper.selectByParams(conferenceQuestionsQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;

    }



    /**
     * 添加会议问题
     *      传 接收姓名 问题详情 会议id
     *      准备参数  建议人id  问题状态 组号
     *
     * @param conferenceQuestions
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void toAdd(ConferenceQuestions conferenceQuestions) {
        //判断参数
        AssertUtil.isTrue(StringUtils.isBlank(conferenceQuestions.getRecipientname()),"指定人姓名不能为空!");
        AssertUtil.isTrue(conferenceQuestions.getConferenceId()==null,"数据异常!");
        //设置参数
        conferenceQuestions.setQuestionState(2);
        conferenceQuestions.setIsValid(1);
        //将传入的接收人姓名转为接收人id

        conferenceQuestions.setRecipientId(
                userMapper.queryUserByTrueName(conferenceQuestions.getRecipientname()).getUserId()
        );

        AssertUtil.isTrue(conferenceQuestionsMapper.insertSelective(conferenceQuestions)!=1,"添加问题失败!");

    }

    /**
     * 会议问题删除
     * @param ids
     * @return
     */
    public void deleteConference(Integer[] ids) {
        //判断参数
        AssertUtil.isTrue(ids==null||ids.length<1,"待删除的问题不存在");
        AssertUtil.isTrue(conferenceQuestionsMapper.deleteBatch(ids)!=ids.length,"删除失败!");

    }
    /**
     * 跟新会议问题
     *  传id,questionState
     *  1,判断参数
     *  2.设置值
     *  3.判断影响行数
     * @param conferenceQuestions
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void toUpData(ConferenceQuestions conferenceQuestions) {
        //1,判断参数
        AssertUtil.isTrue(
                conferenceQuestions.getId()==null,
                "修改数据不存在!"
                );
        ConferenceQuestions temp = conferenceQuestionsMapper.selectByPrimaryKey(conferenceQuestions.getId());

        List<Integer> QuestionState=new ArrayList<>();
        QuestionState.add(1);
        QuestionState.add(2);
        AssertUtil.isTrue(!QuestionState.contains(conferenceQuestions.getQuestionState()),"问题状态未知!");
        //2.设置值
        temp.setQuestionState(conferenceQuestions.getQuestionState());
        temp.setAnswer(conferenceQuestions.getAnswer());
        //3.判断影响行数
        AssertUtil.isTrue(conferenceQuestionsMapper.updateByPrimaryKeySelective(temp)!=1
                ,"会议问题跟新失败");
    }
}
