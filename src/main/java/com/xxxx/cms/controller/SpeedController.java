package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.dao.SpeedMapper;
import com.xxxx.cms.query.SpeedQuery;
import com.xxxx.cms.service.SpeedService;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Speed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("crew")
public class SpeedController extends BaseController {
    @Resource
    private SpeedService speedService;

    @Resource
    private SpeedMapper speedMapper;

    //进入到进度汇总页面
    @RequestMapping("speed")
    public String speed(){
        return "speed/speed";
    }


    //查询进度汇总
    @RequestMapping("list")
    @ResponseBody
    public Map querySpeed(SpeedQuery speedQuery, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        speedQuery.setUserId(userId);
        return speedService.querySpeed(speedQuery);
    }

    //进入增加进度页面
    @RequestMapping("toAddUserPage")
    public String toAddUserPage(Integer speedId,HttpServletRequest request){
        //判断是否为空
        if (speedId !=null){    // 进入修改页面
            Speed speed = speedService.selectSpeed(speedId);
            //将数据设置到请求域中
            request.setAttribute("speed",speed);
        }
        return "speed/add_update";
    }

    //增加进度
    @PostMapping("insert")
    @ResponseBody
    public ResultInfo insertUser(Speed speed,HttpServletRequest request){
        speedService.insertSpeed(speed,request);
        return success("添加成功");
    }

    //删除任务进度
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteSpeed(Integer[] ids){
        //调用Service层的删除方法
        speedService.deleteSpeed(ids);
        return success("任务进度删除成功！");
    }

    //更新任务进度
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateSpeed(Speed speed,HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        speed.setUserId(userId);
        speed.setUpdateTime(new Date());
        //调用service层方法
        speedService.updateByPrimaryKey(speed);
        return success("任务进度更新成功！");
    }

    //更新任务进度
    @PostMapping("judge")
    @ResponseBody
    public ResultInfo progressIsEmpty(HttpServletRequest request){
        // 进入添加页面前判断今天是否已经添加了进度
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        SpeedQuery speedQuery = new SpeedQuery();
        speedQuery.setUserId(userId);
        List<Speed> list= speedMapper.selectByParam(speedQuery);
        list.forEach(x->{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            AssertUtil.isTrue(format.format(date).equals(format.format(x.getUpdateTime())),"今天已添加过任务进度，请前往修改！");
        });
        return success();
    }

}
