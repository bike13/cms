package com.xxxx.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.AnswerMapper;
import com.xxxx.cms.dao.BugMapper;
import com.xxxx.cms.query.BugQuery;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.utils.CookieUtil;
import com.xxxx.cms.utils.LoginUserUtil;
import com.xxxx.cms.vo.Bug;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BugService  extends BaseService<Bug,Integer> {
    @Resource
    private BugMapper bugMapper;
    @Resource
    private AnswerMapper answerMapper;

    /**
     * 查询所有BUG
     */
    public Map<String,Object> queryAllBugs(BugQuery bugQuery){
        Map<String,Object> map=new HashMap<String,Object>();
        PageHelper.startPage(bugQuery.getPage(),bugQuery.getLimit());
        List<Bug> list = bugMapper.queryAllBugs(bugQuery);
        PageInfo<Bug> pageInfo=new PageInfo<Bug>(list);
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addBug(Bug bug, HttpServletRequest request) {
        //判断参数
        check(bug.getBugTitle(),bug.getBugContent());
        //设置默认值
        //1.设置userID为当前登录用户的id,将userID设置为字符串，是为了与cookie中的userID作比较，判断是否是本人
        bug.setUserId(LoginUserUtil.releaseUserIdFromCookie(request));

        //2.设置创建时间为当前时间
        bug.setCreateTime(new Date());
        //调用Dao层执行添加操作，判断受影响行数
        AssertUtil.isTrue(bugMapper.insertSelective(bug)!=1,"发布失败");
    }

    private void check(String bugTitle, String bugContent) {
        //bug标题，非空
        AssertUtil.isTrue(StringUtils.isBlank(bugTitle),"bug标题不能为空");
        //bug标题，非空
        AssertUtil.isTrue(StringUtils.isBlank(bugContent),"bug内容不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSalesChance(HttpServletRequest request, Integer[] bugIds) {

        //参数判断
        AssertUtil.isTrue(bugIds == null || bugIds.length == 0,"请选择要删除的数据");

        for (Integer bugId : bugIds){
            AssertUtil.isTrue(bugMapper.selectUserIdByBugId(bugId) != LoginUserUtil.releaseUserIdFromCookie(request),"您不能删除其他用户发布的BUG！");
        }


        //删除答案表中数据
        List<Integer> listIds = answerMapper.selectBatch(bugIds);
        Integer[] ids = new Integer[listIds.size()];
        listIds.toArray(ids);
        if (ids.length > 0){
            //调用Dao层的方法
            AssertUtil.isTrue(answerMapper.deleteBatchAnswer(ids) != ids.length,"答案删除失败");
        }
        
        //调用Dao层的方法
        AssertUtil.isTrue(bugMapper.deleteBatch(bugIds)!= bugIds.length,"删除失败");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBug(Bug bug) {
        //参数校验
        check(bug.getBugTitle(),bug.getBugContent());
        //设置默认值
        //创建时间
        bug.setCreateTime(new Date());
        //执行dao层操作
        AssertUtil.isTrue(bugMapper.updateByPrimaryKeySelective(bug)!=1,"数据更新失败");
    }
}
