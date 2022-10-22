package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.query.DataAnalysisResult;
import com.xxxx.cms.service.DataAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("dataAnalysis")
public class DataAnalysisController extends BaseController {
    @Resource
    private DataAnalysisService dataAnalysisService;

    //通过前端传过来的组号id查询学习进度,返回speedResult结果集
    @PostMapping("select")
    @ResponseBody
    public DataAnalysisResult<List> selectSpeed(Integer groupId, String updateTime, HttpServletRequest request){
        DataAnalysisResult dataAnalysisResult = dataAnalysisService.selectSpeed(groupId,updateTime);
        request.setAttribute("group",groupId);
        request.setAttribute("speedResult", dataAnalysisResult);
        return dataAnalysisResult;
    }
    @RequestMapping("toDataAnalysis")
    public String toSpeed(HttpServletRequest request){
        int pageNum= dataAnalysisService.selectGroupId();
        String[] dates= dataAnalysisService.selectDate();
        request.setAttribute("dates",dates);
        request.setAttribute("pageNum",pageNum);
        return "dataAnalysis/dataAnalysis";
    }
}
