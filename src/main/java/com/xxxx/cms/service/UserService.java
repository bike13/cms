package com.xxxx.cms.service;

import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.dao.SpeedMapper;
import com.xxxx.cms.dao.UserMapper;
import com.xxxx.cms.model.UserModel;
import com.xxxx.cms.query.SpeedListQuery;
import com.xxxx.cms.utils.*;
import com.xxxx.cms.vo.Speed;
import com.xxxx.cms.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SpeedMapper speedMapper;

    public UserModel userLogin(String userName, String userPwd){
        // 参数判断，判断用户姓名、用户密码非空
        checkLoginParams(userName, userPwd);
        // 调用数据访问层，通过用户名查询用户记录，返回用户对象
        User user = userMapper.queryUserByUserName(userName);
        // 判断用户对象是否为空
        AssertUtil.isTrue(user==null,"用户不存在或已注销!");
        // 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
        checkUserPwd(userPwd, user.getUserPwd());
        // 返回构建用户对象
        return buildUserInfo(user);
    }

    // 参数判断，判断用户姓名、用户密码非空
    private void checkLoginParams(String userName, String userPwd) {

        // 验证用户姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空!");
        // 验证用户密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");

    }

    // 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
    private void checkUserPwd(String userPwd, String userPwd1) {

        // 将客户端传递的密码加密
        userPwd = Md5Util.encode(userPwd);
        // 判断密码是否相等
        AssertUtil.isTrue(!userPwd.equals(userPwd1), "用户密码不正确！");

    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getUserId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId, String oldPwd, String newPwd, String repeatPwd){

        // 通过用户ID查询用户记录，返回用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 判断用户记录是否存在
        AssertUtil.isTrue(user == null, "待更新记录不存在！");
        // 参数校验
        checkPasswordParams(user, oldPwd, newPwd, repeatPwd);
        // 设置用户的新密码
        user.setUserPwd(Md5Util.encode(newPwd));
        // 执行更新，判断受影响行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1, "修改密码失败！");

    }

    private void checkPasswordParams(User user, String oldPwd, String newPwd, String repeatPwd) {

        // 判断原始密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码不能为空！");
        // 判断原始密码是否正确（查询的用户对象中的用户密码是否与原始密码一致）
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)), "原始密码不正确！");
        // 判断新密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        // 判断新密码是否与原始密码一致
        AssertUtil.isTrue(oldPwd.equals(newPwd), "新密码不能与原始密码相同！");
        // 判断确认密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd), "确认密码不能为空！");
        // 判断确认密码是否与新密码一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "确认密码与新密码不一致！");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSetting(Integer userId, String phone, String qq){

        // 通过用户ID查询用户记录，返回用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 判断用户记录是否存在
        AssertUtil.isTrue(user == null, "待更新记录不存在！");
        if (phone!=null){
            // 判断手机号是否符合规范
            AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"请输入正确的手机号！");
            // 设置用户的新手机号
            user.setPhone(phone);
        }
        if (qq!=null){
            // 设置用户的新QQ号
            user.setQq(qq);
        }
        user.setUpdateTime(new Date());
        // 执行更新，判断受影响行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1, "修改资料失败！");

    }

    public void userSign(Integer userId){
        // 通过用户ID查询用户记录，返回用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 判断用户记录是否存在
        AssertUtil.isTrue(user == null, "待签到用户不存在！");

        // 通过Date对象获取
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        long time = 0;
        try{
            time = format.parse(format.format(date)).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }

        AssertUtil.isTrue(!(time >= 1200000 && time <= 1800000),"签到失败，不在签到时间内！");

        user.setSign(1);
        user.setSignTime(new Date());

        // 执行更新，判断受影响行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1, "系统错误，签到失败！");

    }

    public void queryAll(){

        // 通过Date对象获取
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        // 查询所有用户的id和签到时间
        List<User> list = userMapper.queryAll();
        list.forEach(x->{
            if (x.getSignTime() == null || !format.format(x.getSignTime()).equals(format.format(date)) ){
                x.setSign(0);
                // 执行更新
                userMapper.updateByPrimaryKeySelective(x);
            }
        });

    }

    /**
     *添加用户：
     * 1.参数校验
     *     用户名 userName 非空唯一
     *     真实姓名  非空
     *     手机号    格式，可以为空，
     *     QQ号     可以为空
     *     组别      非空
     * 2.设置参数的默认值
     *      默认密码     123456->md5加密
     *     createTime 当前系统时间
     *     updateTime 当前系统事件
     *     isValid    1     是否可用
     *     sign       0     签到状态
     * 3.执行添加操作，判断受影响行数
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //  1.参数校验
        //      用户名 userName 非空唯一
        //      真实姓名  非空
        //      手机号    格式，可以为空，
        //      QQ号     可以为空
        //      组别      非空
        checkUserParmas(null,user.getUserName(),user.getTrueName(),user.getPhone(),user.getGroupId());
        // 2.设置参数的默认值
        //      默认密码     123456->md5加密
        user.setUserPwd(Md5Util.encode("123456"));
        //     createTime 当前系统时间
        user.setCreateTime(new Date());
        //     updateTime 当前系统时间
        user.setUpdateTime(new Date());
        //     isValid    1     是否可用
        user.setIsValid(1);
        //     sign       0     签到状态
        user.setSign(0);
        AssertUtil.isTrue(userMapper.insertSelective(user)!=1,"用户添加失败！");
    }

    /**
     * 校验参数，返回结果异常
     * @param userName  用户名
     * @param trueName  真实姓名
     * @param phone     电话号码
     * @param groupId   组别
     */
    private void checkUserParmas(Integer userId,String userName,String trueName,String phone,Integer groupId) {
        //  用户ID userID  是否为空且数据存在
        User temp=userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(temp!=null&&!(temp.getUserId().equals(userId)),"用户名已存在，请重新输入！");

        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不允许为空");
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"真实姓名不允许为空");
        //AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不允许为空");
        System.out.println(phone);
        if (!StringUtils.isBlank(phone)){
            AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号码不符合规范");
        }
        //AssertUtil.isTrue(StringUtils.isBlank(qQ),"QQ号码不允许为空");
        AssertUtil.isTrue(groupId==null,"组别不允许为空");
    }
    /**
     *更新用户：
     * 1.参数校验
     *     判断用户ID是否为空，且数据存在
     *     用户名 userName 非空唯一
     *     真实姓名  非空
     *     手机号    格式，可以为空，
     *     QQ号     可以为空
     *     组别      非空
     * 2.设置参数的默认值
     *     updateTime 当前系统事件
     * 3.执行更新操作，判断受影响行数
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //  1.参数校验
        //  用户ID userID  是否为空且数据存在
        AssertUtil.isTrue(null==user.getUserId(),"待更新记录不存在！");
        User temp=userMapper.selectByPrimaryKey(user.getUserId());
        AssertUtil.isTrue(temp==null,"待更新记录不存在！");

        //      用户名 userName 非空唯一
        //      真实姓名  非空
        //      手机号    格式，可以为空，
        //      QQ号     可以为空
        //      组别      非空
        checkUserParmas(user.getUserId(),user.getUserName(),user.getTrueName(),user.getPhone(),user.getGroupId());
        // 2.设置参数的默认值
        //     updateTime 当前系统时间
        user.setUpdateTime(new Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)!=1,"用户添加失败！");
    }

    /**
     * 逻辑批量删除用户
     * @param ids Integer类型的主键数组
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteByIds(Integer[] ids) {
        // 判断ids是否为空  长度是否大于0
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除记录不存在!");
        // 判断受影响的行数
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "用户删除失败!");


    }

    public SpeedListQuery querySpeed(HttpServletRequest request) {

        SpeedListQuery sl = new SpeedListQuery();

        // 获取cookie中的用户Id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 通过userId查询个人进度
        List<Speed> list = speedMapper.selectSpeedByUserId(userId);
        // new 一个SpeedListQuery对象存储个人进度
        list.forEach(x->{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            // 判断是否为当天的个人进度
            if (x.getUpdateTime() !=null && format.format(date).equals(format.format(x.getUpdateTime()))){
                // 视频进度
                sl.setVideoSp(x.getVideoSpStart()+" ~ "+x.getVideoSpEnd());
                // 代码进度
                sl.setCodeSp(x.getCodeSpStart()+" ~ "+x.getCodeSpEnd());
                // 笔记情况
                sl.setNote(x.getNote());
            }
        });

        return sl;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserFile(User user) {

        //  1.参数校验
        //      用户名 userName 非空唯一
        //      真实姓名  非空
        //      手机号    格式，非空，
        //      QQ号     非空
        //      组别      非空
        checkUserParmas(null,user.getUserName(),user.getTrueName(),user.getPhone(),user.getGroupId());
        // 2.设置参数的默认值
        //      默认密码     123456->md5加密
        user.setUserPwd(Md5Util.encode("123456"));
        //     isValid    1     是否可用
        user.setIsValid(1);
        //     sign       0     签到状态
        user.setSign(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        AssertUtil.isTrue(userMapper.insertSelective(user)!=1,"用户添加失败！");
    }

    public ResultInfo userCheckUserName(User p) {
        User temp=new User();
        ResultInfo resultInfo=new ResultInfo();
        AssertUtil.isTrue(p.getUserName()==null?true:p.getUserName()==""?true:false,"学号不允许为空！");
        temp=userMapper.queryUserByUserName(p.getUserName());
        if(temp==null){
            resultInfo.setCode(200);
            resultInfo.setMsg("学号已存在，请修改Excel表中学号！");
        }
        AssertUtil.isTrue(temp!=null,"学号已存在，请修改Excel表中学号！");
        return resultInfo;
    }

}
