package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.service.UserService;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {

        // 重置所有用户的签到状态
        userService.queryAll();
        // 获取cookie中的用户Id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 查询用户对象
        User user = userService.selectByPrimaryKey(userId);
        // 设置session作用域
        request.getSession().setAttribute("user",user);

        return "main";
    }
}
