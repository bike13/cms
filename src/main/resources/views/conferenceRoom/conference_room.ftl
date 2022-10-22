<!DOCTYPE html>
<html>
<head>
    <title>会议室管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >

    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="roomNumber"
<#--                           class="layui-input searchVal" placeholder="房价名称" />-->
                           class="layui-input searchVal" placeholder="房间号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="roomName"

                           class="layui-input searchVal" placeholder="房间名称" />
                </div>

                <div class="layui-input-inline">
                    <input type="text" name="createName"

                           class="layui-input searchVal" placeholder="创建人" />
                </div>

                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>


    <#--容器-->
    <table id="conferenceRoomlist" class="layui-table"  lay-filter="conferenceRoom"></table>


    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>

    <!--行工具操作-->
    <script id="op" type="text/html" >
        <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="dev">修改</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="del">删除</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/conferenceRoom/conference.room.js"></script>

</body>
</html>