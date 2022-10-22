package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.query.ConferenceNowQuery;
import com.xxxx.cms.query.ConferenceQuestionsQuery;
import com.xxxx.cms.service.ConferenceQuestionsService;
import com.xxxx.cms.service.ConferenceService;
import com.xxxx.cms.vo.Conference;
import com.xxxx.cms.vo.ConferenceQuestions;
import com.xxxx.cms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/conferenceQuestions")
public class ConferenceQuestionsController extends BaseController {
    @Resource
    private ConferenceService conferenceService;

    @Resource
    private ConferenceQuestionsService conferenceQuestionsService;

    /**
     * 直接跳转我的会议页面0
     * @return
     */
    @RequestMapping("index")
    public String index( ConferenceNowQuery conferenceNowQuery,HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");

        conferenceNowQuery.setUserId(user.getUserId());

        System.out.println(conferenceNowQuery);

        List<Conference> conferences = conferenceService.queryAllConferenceByNow(conferenceNowQuery);
        request.setAttribute("conferences",conferences);
        request.setAttribute("conferenceNowQuery",conferenceNowQuery);

        return "conferenceQuestions/my_conference";
    }


    /**
     * 直接跳转我的会议页面
     *  1,显示会议详情
     *  2,提出问题
     *  3,我的问题(接收到的问题)
     * @return
     */
    @RequestMapping("toQuestionsPage")
    public String toQuestionsPage(Integer id,HttpServletRequest request){
        if (id!=null){
            Conference conference = conferenceService.selectByPrimaryKey(id);
            request.setAttribute("conference",conference);

        }
        return "conferenceQuestions/questions";
    }


    /**
     * 查对 当前会议 当前用户 的问题 (返回数据表格形式)
     * @param conferenceQuestionsQuery
     * @return
     */
    @RequestMapping("myList")
    @ResponseBody
    public Map<String,Object> queryQuestionsByRecipientId(ConferenceQuestionsQuery conferenceQuestionsQuery,HttpSession session){
        User user = (User) session.getAttribute("user");
        conferenceQuestionsQuery.setRecipientId(user.getUserId());
        System.out.println(conferenceQuestionsQuery);
        return conferenceQuestionsService.queryQuestionsByRecipientId(conferenceQuestionsQuery);
    }

    /**
     * 查对 所有问题汇总 (返回数据表格形式)
     * @param conferenceQuestionsQuery
     * @return
     */
    @RequestMapping("AllList")
    @ResponseBody
    public Map<String,Object> queryQuestionsByAll(ConferenceQuestionsQuery conferenceQuestionsQuery,HttpSession session){
        if (conferenceQuestionsQuery.getGroupId()==null){
            //默认查一组
            conferenceQuestionsQuery.setGroupId(1);
        }
        System.out.println(conferenceQuestionsQuery);
        return conferenceQuestionsService.queryQuestionsByRecipientId(conferenceQuestionsQuery);

    }


    /**
     * 添加会议问题
     *      传 接收姓名 问题详情 会议id
     *      准备参数  建议人id  问题状态 组号
     *
     * @param conferenceQuestions
     * @return
     */
    @PostMapping("toAdd")
    @ResponseBody
    public ResultInfo toAdd(ConferenceQuestions conferenceQuestions, HttpSession session){
        //设置参数
        User user = (User) session.getAttribute("user");
        //建议人id
        conferenceQuestions.setEmitId(user.getUserId());
        //组号
        conferenceQuestions.setGroupId(user.getGroupId());

        conferenceQuestionsService.toAdd(conferenceQuestions);

        return success("添加问题成功");
    }


    /**
     * 会议问题删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        System.out.println(ids);
        conferenceQuestionsService.deleteConference(ids);
        return success("会议问题删除成功!");
    }


    /**
     * 进入添加修改页面
     */
    @RequestMapping("toUpDataPage")
    public String toUpDataPage(Integer id, HttpServletRequest request){
        if (id!=null){
            ConferenceQuestions conferenceQuestions = conferenceQuestionsService.selectByPrimaryKey(id);
            request.setAttribute("conferenceQuestions",conferenceQuestions);
        }
        return "conferenceQuestions/add_updata";
    }



    /**
     * 跟新会议问题
     *  传id,questionState
     * @param conferenceQuestions
     * @return
     */
    @PostMapping("toUpData")
    @ResponseBody
    public ResultInfo toUpData(ConferenceQuestions conferenceQuestions){
        System.out.println(conferenceQuestions);
        conferenceQuestionsService.toUpData(conferenceQuestions);
        return success("会议问题跟新成功!");
    }

    /**
     * 进入问题所有问题
     * @return
     */
    @RequestMapping("toAllQuestionsPage")
    public String toAllQuestionsPage(){
        return "conferenceQuestions/all_questions";
    }

}


