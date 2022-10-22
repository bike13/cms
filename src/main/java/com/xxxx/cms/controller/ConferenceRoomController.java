package com.xxxx.cms.controller;

import com.xxxx.cms.base.BaseController;
import com.xxxx.cms.base.ResultInfo;
import com.xxxx.cms.query.ConferenceRoomQuery;
import com.xxxx.cms.service.ConferenceRoomService;

import com.xxxx.cms.vo.ConferenceRoom;
import com.xxxx.cms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/conferenceRoom")
public class ConferenceRoomController extends BaseController {
    @Resource
    private ConferenceRoomService conferenceRoomService;

    //进入会议房间,跳转
    @RequestMapping("index")
    public String index(){
        return "conferenceRoom/conference_room";
    }

    //查询 所有会议房间
    //无房间号查所有 有房间号查单房间有无
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryConferenceRoomByNumber(ConferenceRoomQuery conferenceRoomQuery){
        return conferenceRoomService.queryConferenceRoomByNumber(conferenceRoomQuery);
    }

    //进入进入会议页面
    @RequestMapping("toAddAndUpdataPage")
    public String toAddAndUpdataPage(Integer id, HttpServletRequest request){
        if (id!=null){
            ConferenceRoom conferenceRoom = conferenceRoomService.selectByPrimaryKey(id);
            request.setAttribute("conferenceRoom",conferenceRoom);
        }
        return "conferenceRoom/add_updata_room";
    }

    /**
     * 通过是否有id判断是添加还是修改
     *  添加从session中获取当前用户名
     *
     * 调用方法添加修改
     * @param conferenceRoom
     * @param session
     * @return
     */
    @PostMapping("toAddOrUpdate")
    @ResponseBody
    public ResultInfo toAddOrUpdate(ConferenceRoom conferenceRoom, HttpSession session){

        if (conferenceRoom.getId()==null){
            //表示添加房间
            User user = (User) session.getAttribute("user");
            System.out.println(user);
            conferenceRoom.setCreateName(user.getTrueName());
            conferenceRoomService.toAddOrUpdate(conferenceRoom);
            return success("会议室添加成功!");
        }else {
            conferenceRoomService.toAddOrUpdate(conferenceRoom);
            return success("会议室修改成功!");
        }
    }

    /**
     * 会议室删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        conferenceRoomService.deleteConferenceRoom(ids);
        return success("会议室删除成功!");
    }


}
