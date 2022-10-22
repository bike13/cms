package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.service.AnswerService;
import com.xxxx.cms.service.BrushService;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Answer;
import com.xxxx.cms.vo.Brush;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("brush")
public class BrushController extends BaseController {

    @Resource
    private BrushService brushService;
    @Resource
    private AnswerService answerService;

    /**
     * 进入界面
     */
    @RequestMapping("index")
    public String index(){
        return "brush/brush";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAllBrush(){
        return brushService.queryAllBrush();
    }

    /**
     * 打开详情界面
     */
    @RequestMapping("detail")
    public String detail(Integer brushId, HttpServletRequest request){
        //判断是添加还是详情,添加没有brushId
        boolean flag = false;
        if (brushId!=null){
            //详情
            flag = true;
            //根据brushId查询数据
            Brush brush = brushService.selectByPrimaryKey(brushId);
            request.setAttribute("brush",brush);
            //将brushId作为parentId进行查询，查询结果为一个list集合
            List<Brush> list = brushService.queryByParentId(brushId);
            request.setAttribute("list",list);

            //去answer表中查询答案
            Answer answer = answerService.queryByBrushIdAndUserId(brushId,LoginUserUtil.releaseUserIdFromCookie(request));
            request.setAttribute("answer",answer);
        }
        request.setAttribute("flag",flag);

        return "brush/detail";
    }

    /**
     * 查询所有大纲
     */
    @RequestMapping("outline")
    @ResponseBody
    public List<Brush> outLine(){
        List<Brush> titleList = brushService.queryByParentId(-1);
        return titleList;
    }

    /**
     * 添加问题、大纲
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addBrush(Brush brush){
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(brush.getQuestion()),"请输入你的问题");
        //设置时间
        brush.setCreateTime(new Date());
       //调用service层执行添加操作
        brushService.insertSelective(brush);
        return success("添加成功");
    }

    @RequestMapping("addAnswer")
    @ResponseBody
    public ResultInfo addAnswer(Answer answer,HttpServletRequest request){
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(answer.getAnswer()),"请输入你的答案");
        //为answer的时间以及userId赋值
        answer.setCreteTime(new Date());
        answer.setUserId(LoginUserUtil.releaseUserIdFromCookie(request));
        System.out.println(answer);
        //调用Service层的添加操作
        answerService.insertSelective(answer);
        return success("添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateAnswer(Answer answer,HttpServletRequest request){
        //为answer的时间以及userId赋值
        answer.setCreteTime(new Date());
        answer.setUserId(LoginUserUtil.releaseUserIdFromCookie(request));
        //调用Service层的方法
        answerService.updateAnswerByBrushIdAndUserId(answer);
        return success("修改成功");
    }
}
