package com.xxxx.cms.service;

import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.AnswerMapper;
import com.xxxx.cms.dao.BugMapper;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.utils.CookieUtil;
import com.xxxx.cms.vo.Answer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService extends BaseService<Answer,Integer> {

    @Resource
    private AnswerMapper answerMapper;
    @Resource
    private BugMapper bugMapper;

    //根据bug_id查询回答，返回值为集合
    public List<Map<String,String>> selectAnswerByBugId(Integer bugId) {
        //参数校验
        AssertUtil.isTrue(bugId==null,"未有数据");
        List<Map<String,String>> list = answerMapper.selectAnswerByBugId(bugId);
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addAnswer(Integer bugId, String answer, HttpServletRequest request) {
        //参数判断
        AssertUtil.isTrue(bugId==null,"要回答的问题不存在");
        AssertUtil.isTrue(StringUtils.isBlank(answer),"请填写回答");
        //创建Answer对象
        Answer answer1 = new Answer();
        //设置属性
        answer1.setBugId(bugId);
        answer1.setCreteTime(new Date());
        answer1.setAnswer(answer);
        //设置回答人为当前登录用户的真实姓名
        answer1.setMan(CookieUtil.getCookieValue(request,"trueName"));
        //先设置为aaa
        //answer1.setMan("aaa");
        //调用Service层的添加方法
        AssertUtil.isTrue(answerMapper.insertSelective(answer1)!=1,"添加回答失败");
        //修改bug的解决状态为已解决
        AssertUtil.isTrue(bugMapper.updateSolveByBugId(bugId)!=1,"添加回答失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAnswerByBugId(Integer bugId) {
        //参数校验
        AssertUtil.isTrue(bugId==null,"要删除的数据不存在");
        //调用dao层
        AssertUtil.isTrue(answerMapper.deleteAnswerByBugId(bugId)==0,"删除失败");
    }

    public Answer queryByBrushIdAndUserId(Integer brushId, Integer userId) {
        //调用dao层的方法
        Answer answer = answerMapper.queryByBrushIdAndUserId(brushId,userId);
        return answer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAnswerByBrushIdAndUserId(Answer answer) {
        //参数校验
        AssertUtil.isTrue(answer.getBrushId()==null,"要修改的数据不存在");
        AssertUtil.isTrue(StringUtils.isBlank(answer.getAnswer()),"请输入修改的答案!");
        //调用dao层的修改方法
        AssertUtil.isTrue(answerMapper.updateAnswerByBrushIdAndUserId(answer.getAnswer(),answer.getBrushId(),answer.getUserId())!=1,"答案修改失败");
    }

}
