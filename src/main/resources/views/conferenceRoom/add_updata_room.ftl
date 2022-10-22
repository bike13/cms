<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 隐藏域id-->
    <input name="id" type="hidden" value="${(conferenceRoom.id)!}"/>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="roomName" id="roomName"  value="${(conferenceRoom.roomName)!}" placeholder="请输入房间名称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="roomNumber" id="roomNumber" value="<#if (conferenceRoom.roomNumber)??>${(conferenceRoom.roomNumber)?c}</#if>" placeholder="请输入房间号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateConferenceRoom">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/conferenceRoom/add.updata.room.js"></script>
</body>
</html>