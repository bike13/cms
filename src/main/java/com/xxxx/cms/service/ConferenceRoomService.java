package com.xxxx.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.ConferenceRoomMapper;
import com.xxxx.cms.query.ConferenceRoomQuery;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.vo.ConferenceRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConferenceRoomService extends BaseService<ConferenceRoom,Integer> {
    @Resource
    private ConferenceRoomMapper conferenceRoomMapper;

    //查询 所有会议房间
    //无房间号查所有 有房间号查单房间有无
    public Map<String, Object> queryConferenceRoomByNumber(ConferenceRoomQuery conferenceRoomQuery) {
        Map<String,Object> map=new HashMap<>();
        //开启分页
        PageHelper.startPage(conferenceRoomQuery.getPage(),conferenceRoomQuery.getLimit());
        //分页对象  返回的对象集合
        PageInfo<ConferenceRoom> pageInfo=new PageInfo<>(conferenceRoomMapper.selectByParams(conferenceRoomQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加修改会议室
     *      判读参数
     *          id存在  对应房间不能为空
     *          房间号  不能为空,不能重复
     *              id存在时排除自己
     *          房间名称 不能为空
     *      准备数据
     *          isValid  1
     *      调用方法
     *          id存在 跟新
     *          否则 添加
     *       判断参数
     *          影响数据 与 1
     * @param conferenceRoom
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void toAddOrUpdate(ConferenceRoom conferenceRoom) {
        AssertUtil.isTrue(conferenceRoom.getRoomNumber()==null,"房间号不能为空!");
        AssertUtil.isTrue(conferenceRoom.getRoomName()==null,"房间名称不能为空!");
        //通过房间号 查会议房间
        ConferenceRoom temp=conferenceRoomMapper.selectByRoomNumber(conferenceRoom.getRoomNumber());
        //如果修改
        if (conferenceRoom.getId()!=null){
            AssertUtil.isTrue(conferenceRoomMapper.selectByPrimaryKey(conferenceRoom.getId())==null,"修改参数异常!");
            //房间号  不能重复
            AssertUtil.isTrue(temp!=null&&!(temp.getId().equals(conferenceRoom.getId())),"预修改的房间以存在!");
        }else {
            AssertUtil.isTrue(temp!=null,"预添加的房间以存在!");
        }
        //准备数据
        conferenceRoom.setIsValid(1);
        //调用方法
        if (conferenceRoom.getId()!=null){
            //调用跟新方法
            AssertUtil.isTrue(conferenceRoomMapper.updateByPrimaryKeySelective(conferenceRoom)!=1,"会议室跟新失败!");
        }else {
            //调用添加方法
            AssertUtil.isTrue(conferenceRoomMapper.insertSelective(conferenceRoom)!=1,"会议室添加失败!");
        }

    }

    /**
     * 删除会议室操作
     *  判断参数
     *  判断返回参数
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteConferenceRoom(Integer[] ids) {
        //判断参数
        AssertUtil.isTrue(ids==null||ids.length<1,"待删除的会议室不存在");
        AssertUtil.isTrue(conferenceRoomMapper.deleteBatch(ids)!=ids.length,"删除失败!");

    }
}
