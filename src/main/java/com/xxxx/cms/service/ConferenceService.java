package com.xxxx.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cms.base.BaseService;
import com.xxxx.cms.dao.ConferenceMapper;
import com.xxxx.cms.dao.ConferenceRoomMapper;
import com.xxxx.cms.dao.UserMapper;
import com.xxxx.cms.query.ConferenceNowQuery;
import com.xxxx.cms.query.ConferenceQuery;
import com.xxxx.cms.utils.AssertUtil;
import com.xxxx.cms.vo.Conference;
import com.xxxx.cms.vo.ConferenceRoom;
import com.xxxx.cms.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ConferenceService extends BaseService<Conference,Integer> {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private ConferenceRoomMapper conferenceRoomMapper;
    /**
     * 多条件查询
     *  判断参数        rank只能为 1\2
     *  准备参数       无
     *  执行判返回       无
     * @param conferenceQuery
     * @return
     */
    public Map<String, Object> queryConferenceByParams(ConferenceQuery conferenceQuery) {
        //执行
        Map<String,Object> map=new HashMap<>();
        //开启分页
        PageHelper.startPage(conferenceQuery.getPage(),conferenceQuery.getLimit());
        //分页对象
        PageInfo<Conference> pageInfo=new PageInfo<>(conferenceMapper.selectByParams(conferenceQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    /**
     * 查询所有的等级
     * @return
     */
    public List<Object> queryAllRank() {
        return conferenceMapper.queryAllRank();
    }
    /**
     * 查询所有的房间号
     * @return
     */
    public List<Object> queryAllRoom() {
        return conferenceRoomMapper.queryAllRoom();
    }

    /**
     * 添加或修改会议操作
     *  判断参数
     *      若修改 -修改的对象存在 -id!=null,对象!=null
     *      theme,rankId(),roomNumber(根据房间,查id房间id非空),
     *      createTime,endTime,非空
     *   准备数据
     *      根据房间名称,查房间id并设置
     *   执行后判断
     *
     * @param conference
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void toAddOrUpdate(Conference conference) {
        //判断参数
        if (conference.getId()!=null){
            AssertUtil.isTrue(conferenceMapper.selectByPrimaryKey(conference.getId())==null,"修改参数异常!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(conference.getTheme()),"标题不能为空");

        AssertUtil.isTrue(conference.getRankId()==null,"会议等级不能为空");

        ConferenceRoom conferenceRoom = conferenceRoomMapper.selectByRoomNumber(conference.getRoomNumber());
        AssertUtil.isTrue(conferenceRoom==null
                ,"会议室不存在");
        AssertUtil.isTrue(conference.getCreateTime()==null,"会议开始时间不能为空");
        AssertUtil.isTrue(conference.getEndTime()==null,"会议结束时间不能为空");
        //结束时间大于开始时间
        AssertUtil.isTrue(
                conference.getEndTime().hashCode()<=conference.getCreateTime().hashCode(),"结束时间必须大于开始时间"
        );
        //准备数据
        conference.setRoomId(conferenceRoom.getId());
        //执行后判断
        if (conference.getId()!=null){
            //表示跟新会议
            AssertUtil.isTrue(conferenceMapper.updateByPrimaryKeySelective(conference)!=1
            ,"会议跟新失败");

        }else {
            //添加会议
            AssertUtil.isTrue(conferenceMapper.insertSelective(conference)!=1
                    ,"会议添加失败");
        }

    }

    /**
     * 删除会议室操作
     *  判断参数
     *  判断返回参数
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteConference(Integer[] ids) {
        //判断参数
        AssertUtil.isTrue(ids==null||ids.length<1,"待删除的会议室不存在");
        AssertUtil.isTrue(conferenceMapper.deleteBatch(ids)!=ids.length,"删除失败!");
    }

    //查会议详情信息
    public String queryDetailsById(Integer id) {
        return conferenceMapper.queryDetailsById(id);
    }


    //根据组别查会议人员
    public List<Map<String,Object>> queryParticipantsById(Integer id) {
        //查所有的人员
        List<Map<String,Object>> participants= userMapper.queryParticipants();
        //按组分类
        List<Map<String,Object>> sortParticipants=new ArrayList<>();
        //假设一共不超过10组
        for (int i=0;i<=10;i++){
            int finalI = i;
            participants.forEach(v->{
                //if (v.containsKey(finalI)){
                if (v.get("groupId").equals(finalI)){
                    Map<String,Object> vv = new HashMap<>();
                    vv.put("groupId",finalI);
                    vv.put("trueName",v.get("trueName"));
                    sortParticipants.add(vv);
                }
            });

        }
        //查询当前id等级
        Conference conference = conferenceMapper.selectByPrimaryKey(id);
        if (conference.getRankId()==1){
            //班级会议
            return sortParticipants;
        }else {
            //小组会议
            User user = userMapper.queryUserByTrueName(conference.getUserName());
//            sortParticipants.forEach(v->{
//                if (!v.get("groupId").equals(user.getGroupId())){
//                    sortParticipants.remove(v);
//                }
//            });

//            for (int i=0;i<sortParticipants.size();i++){
//                if (!sortParticipants.get(i).get("groupId").equals(user.getGroupId())) {
//                    sortParticipants.remove(i);
//                }
//            }
            ListIterator<Map<String, Object>> mapListIterator = sortParticipants.listIterator();
            while (mapListIterator.hasNext()) {
                Map<String,Object> next = mapListIterator.next();
                if (!next.get("groupId").equals(user.getGroupId())) {
                    mapListIterator.remove();
                }
            }
            return sortParticipants;
        }
    }

    /**
     * 查询 当前用户的所有会议
     *  1.预约会议时,设置本组会议,设置人的组信息等于当前用户的组信息
     *  2.预约会议时,设置的班级会议
     *  3.将两种总和
     *
     *
     *  判断参数
     *      当前用户存在
     *
     * @return
     */
    public List<Conference> queryAllConferenceByNow(ConferenceNowQuery conferenceNowQuery) {
        Integer userId = conferenceNowQuery.getUserId();
        AssertUtil.isTrue(userId==null||userMapper.selectByPrimaryKey(userId)==null
                ,"参数异常请重试!!!");

        //对输入时间进行搜索,格式修改
        if (conferenceNowQuery.getCreateTime()!=null){
            conferenceNowQuery.setCreateTimeStr(dateToSimpleStr(conferenceNowQuery.getCreateTime()));
        }

        //查本组会议
        List<Conference> GroupConference=conferenceMapper.queryGroupConferenceByNow(conferenceNowQuery);
        //查班级会议
        List<Conference> ClassConference=conferenceMapper.queryClassConferenceByNow(conferenceNowQuery);
        List<Conference> Conference=new ArrayList<>();
        Conference.addAll(GroupConference);
        Conference.addAll(ClassConference);
        //按时间进行倒序排序
        Conference.sort((a,b)->{
            return b.getCreateTime().hashCode()-a.getCreateTime().hashCode();
        });
        //最多显示15条
        Integer length=Conference.size();
        if (Conference.size()>15){
            length=15;
        }
        Conference=Conference.subList(0,length);

        return Conference;
    }

    /**
     * 转SimpleDateFormat
     *  需要在原时间上加一天
     *  转对应格式
     * @param createTime
     * @return
     */
    private String dateToSimpleStr(Date createTime) {

        //需要在原时间上加一天
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createTime);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        createTime = calendar.getTime();


        // 转对应格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(createTime);
    }
}
