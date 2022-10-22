package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.query.BugQuery;
import com.xxxx.cms.service.AnswerService;
import com.xxxx.cms.service.BugService;
import com.xxxx.cms.service.UserService;
import com.xxxx.cms.utils.CookieUtil;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Answer;
import com.xxxx.cms.vo.Bug;
import com.xxxx.cms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bug")
public class BugController extends BaseController {

    @Resource
    private BugService bugService;
    @Resource
    private AnswerService answerService;
    @Resource
    private UserService userService;


    //进入bug页面
    @RequestMapping("index")
    public String index(){
        return "bug/bug";
    }

    /**
     * 查询所有Bug记录
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAllBugs(BugQuery bugQuery){
        return bugService.queryAllBugs(bugQuery);
    }

    /**
     * 打开添加,详情或修改的页面
     */
    @RequestMapping("addOrUpdateBugPage")
    public String addOrUpdateBugPage(Integer bug_id,Integer user_id,HttpServletRequest request){

        if (bug_id != null){
            //先根据id获取全部数据
            Bug bug = bugService.selectByPrimaryKey(bug_id);
            //回显
            request.setAttribute("bug",bug);
            //从Cookie中获取当前登录的用户ID
            Integer userID = LoginUserUtil.releaseUserIdFromCookie(request);
            //通过当前传递过来的userId去user表中查询名字，并将名字回显
            User user = userService.selectByPrimaryKey(user_id);
            request.setAttribute("trueName",user.getTrueName());

            //判断是否是本人
            boolean flag = false;
            if (user_id !=null && user_id.equals(userID)){
                flag = true;
            }
            //将flag返回，用作判断
            request.setAttribute("flag",flag);

            //根据bug_id从answer表查询bug对应的回答
            List<Map<String,String>> list = answerService.selectAnswerByBugId(bug_id);
            request.setAttribute("list",list);
            request.setAttribute("count",list.size());

        }

        return "bug/add_update";
    }

    /**
     * 添加(发布)bug集锦
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addBug(Bug bug, HttpServletRequest request){
        //调用Service层的添加方法
        bugService.addBug(bug, request);
        return success("添加成功");
    }

    /**
     * 删除bug(批量删除)
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteBug(HttpServletRequest request,Integer[] bug_ids){

        bugService.deleteSalesChance(request, bug_ids);
        return success("数据删除成功");
    }

    /**
     * 修改
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateBug(Bug bug){
        //调用Service层的放法
        bugService.updateBug(bug);
        return success("数据修改成功");
    }

    /**
     * 删除单条数据
     */

    @PostMapping("deleteOnly")
    @ResponseBody
    public ResultInfo deleteOneBug(Bug bug){

        if (bug.getSolve() == 1){
            //根据bugId删除answer表中的内容
            answerService.deleteAnswerByBugId(bug.getBugId());
        }

        //根据bugId删除bug表中的内容
        bugService.deleteByPrimaryKey(bug.getBugId());
        return success("数据删除成功");
    }

    /**
     * 添加回答
     */
    @PostMapping("addAnswer")
    @ResponseBody
    public ResultInfo addAnswer(Bug bug, Answer answer,HttpServletRequest request){
        //调用Service层的方法
        answerService.addAnswer(bug.getBugId(),answer.getAnswer(),request);
        return success("回答成功");
    }
}
