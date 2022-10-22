package com.xxxx.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.SpeedMapper;
import com.xxxx.cms.query.SpeedListQuery;
import com.xxxx.cms.query.SpeedQuery;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Speed;
import com.xxxx.cms.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SpeedService extends BaseService<Speed,Integer> {
    @Resource
    private SpeedMapper speedMapper;

    //查询进度
    public Map querySpeed(SpeedQuery speedQuery) {
        //定义一个集合
        Map<String,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(speedQuery.getPage(),speedQuery.getLimit());
        //得到分页对象
        List<Speed> list = speedMapper.selectByParam(speedQuery);

        List<SpeedListQuery> list2 = new ArrayList<>();
        list.forEach(speed -> {
            System.out.println(speed);
            SpeedListQuery sl = new SpeedListQuery();
            // Id
            sl.setId(speed.getId());
            // 课程问题
            if (StringUtils.isBlank(speed.getClassQuestion()) || "无".equals(speed.getClassQuestion()) || "没有".equals(speed.getClassQuestion())) {
                sl.setClassQuestion("暂无");
            }else {
                sl.setClassQuestion(speed.getClassQuestion());
            }
            // 代码进度
            sl.setCodeSp(speed.getCodeSpStart()+" ~ "+speed.getCodeSpEnd());
            // 用户Id
            sl.setUserId(speed.getUserId());
            // 视频进度
            sl.setVideoSp(speed.getVideoSpStart()+" ~ "+speed.getVideoSpEnd());
            // 备注
            if (StringUtils.isBlank(speed.getRemark()) || "无".equals(speed.getRemark()) || "没有".equals(speed.getRemark())) {
                sl.setRemark("暂无");
            }else {
                sl.setRemark(speed.getRemark());
            }
            // 笔记情况
            sl.setNote(speed.getNote());
            // 学习问题
            if (StringUtils.isBlank(speed.getStudyQuestion()) || "无".equals(speed.getStudyQuestion()) || "没有".equals(speed.getStudyQuestion())) {
                sl.setStudyQuestion("暂无");
            }else {
                sl.setStudyQuestion(speed.getStudyQuestion());
            }
            // 填写时间
            sl.setUpdateTime(speed.getUpdateTime());
            list2.add(sl);
        });

        PageInfo<SpeedListQuery> pageInfo = new PageInfo<>(list2);
        //设置Map的对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }

    //进度填写添加
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertSpeed(Speed speed, HttpServletRequest request) {
        // 参数校验
        check(speed);
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        speed.setUserId(userId);
        speed.setUpdateTime(new Date());
        AssertUtil.isTrue(speedMapper.insertSpeed(speed)<1,"进度添加失败！");
    }

    //进度批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSpeed(Integer[] ids){
        //判断id是否为空
        AssertUtil.isTrue(ids==null||ids.length<1,"要删除的记录不存在");
        //执行删除(更新)操作,判断受影响的行数
        AssertUtil.isTrue(speedMapper.deleteSpeed(ids)!=ids.length,"任务进度删除失败!!!!");
    }

    public Speed selectSpeed(Integer speedId) {
        return speedMapper.selectSpeed(speedId);
    }

    //进度更新
    public void updateByPrimaryKey(Speed speed) {
        //进行参数的校验
        //1.进度id不为空  对应的数据要存在
        AssertUtil.isTrue(speed.getId()==null,"任务进度不存在");
        //2.根据id查询进度记录
        Speed temp = speedMapper.selectSpeed(speed.getId());
        //3.判断记录是否存在
        AssertUtil.isTrue(temp==null,"要更新的记录不存在");
        // 参数校验
        check(speed);
        //4.执行更新操作方法,返回受影响的行数
        AssertUtil.isTrue(speedMapper.updateByPrimaryKeySelective(speed)!=1,"更新失败");

    }

    // 参数校验
    public void check(Speed speed) {
        AssertUtil.isTrue(speed.getCodeSpStart()==null, "代码起始进度不能为空");
        AssertUtil.isTrue(speed.getCodeSpEnd()==null, "代码截至进度不能为空");
        AssertUtil.isTrue(speed.getVideoSpStart()==null, "视频起始进度不能为空");
        AssertUtil.isTrue(speed.getVideoSpEnd()==null, "视频截至进度不能为空");
        AssertUtil.isTrue(speed.getNote()==null, "笔记情况不能为空");
    }
}
