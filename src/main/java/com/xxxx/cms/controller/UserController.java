package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.model.UserModel;
import com.xxxx.cms.query.SpeedListQuery;
import com.xxxx.cms.query.UserQuery;
import com.xxxx.cms.service.UserService;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Person;
import com.xxxx.cms.vo.Speed;
import com.xxxx.cms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd){

        ResultInfo resultInfo = new ResultInfo();

        // 调用service层登录方法
        UserModel userModel = userService.userLogin(userName, userPwd);
        // 设置ResultInfo的result的值
        resultInfo.setResult(userModel);

        return resultInfo;
    }

    @RequestMapping("/toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }

    @RequestMapping("/toSettingPage")
    public String toSettingPage(){
        return "user/setting";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request, String oldPassword, String newPassword, String repeatPassword){

        ResultInfo resultInfo = new ResultInfo();

        // 获取cookie中的userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调用Service层的修改密码方法
        userService.updatePassWord(userId, oldPassword, newPassword, repeatPassword);

        return resultInfo;
    }

    @PostMapping("/updateSetting")
    @ResponseBody
    public ResultInfo updateSetting(Integer userId, String phone, String qq){
        ResultInfo resultInfo = new ResultInfo();

        // 调用Service层的修改用户信息方法
        userService.updateSetting(userId, phone, qq);

        return resultInfo;
    }

    @RequestMapping("/status")
    public String toStatusPage(HttpServletRequest request){

        // 查询个人进度
        SpeedListQuery speedListQuery = userService.querySpeed(request);
        //将数据设置到请求域中
        request.setAttribute("speedListQuery",speedListQuery);

        return "user/status";
    }

    @RequestMapping("/sign")
    @ResponseBody
    public ResultInfo sign(Integer userId){
        ResultInfo resultInfo = new ResultInfo();

        userService.userSign(userId);

        return resultInfo;
    }

    /**
     * 打开用户管理页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "user/user";
    }

    /**
     * 分页多条件查询用户列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String ,Object> selectByParams(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * 打开用户添加或修改页面
     * @return
     */
    @RequestMapping("/addOrUpdateUserPage")
    public String AddOrUpdate(Integer id,HttpServletRequest request){
        //判断id是否为空，不为空表示更新操作，查询用户对象
        if(id != null&&id>0){
            //通过id查询用户对象
            User user=userService.selectByPrimaryKey(id);
            //将数据设置到请求域中
            request.setAttribute("userInfo",user);
        }
        return "user/add_update";
    }

    /**
     * 1、调用service层添加用户方法
     * 2、返回结果
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(User user){
        userService.addUser(user);
        return success("用户添加成功！");
    }

    /**
     * 1、调用service层更新用户方法
     * 2、返回结果
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(User user){
        userService.updateUser(user);
        ResultInfo resultInfo =new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("用户更新成功");
        return resultInfo;
    }


    /**
     * 逻辑批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {
        // 调用service层执行逻辑删除
        userService.deleteByIds(ids);
        return success("数据删除成功!");
    }

    /**
     * 打开摸鱼圈
     * @param ids
     * @return
     */
    @RequestMapping("/myq")
    public String myq(Integer[] ids) {
        return "user/myq";
    }

    @RequestMapping("userFileIn")
    @ResponseBody
    public ResultInfo userFileIn(Person person1){
        List<String> l = person1.getPerson1();
        User p = new User();
        p.setQq(l.get(0));
        p.setUserName(l.get(2));
        p.setGroupId(Integer.valueOf(l.get(3)));
        p.setPhone(l.get(4));
        p.setTrueName(l.get(6));
        userService.addUserFile(p);
        return success("用户添加成功！");
    }

    @RequestMapping("userCheckUserName")
    @ResponseBody
    public ResultInfo userCheckUserName(Person person1){
        ResultInfo resultInfo=new ResultInfo();
        User p=new User();
        List<String> l = person1.getPerson1();
        for (int i = 0; i < l.size(); i++) {
            p.setUserName(l.get(0));
            resultInfo=userService.userCheckUserName(p);
            if(resultInfo.getCode()!=200){
                break;
            }
        }
        return resultInfo;
    }

}
