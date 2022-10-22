<!DOCTYPE html>
<html>
<head>
    <title>会议预约</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">

<div class="layui-tab" lay-filter="conference">
    <ul class="layui-tab-title">
        <li class="layui-this">班级会议</li>
        <li >小组会议</li>
    </ul>
</div>


<form class="layui-form" >

    <#--搜索框 主题 房间号-->
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <#--隐藏域等级-->
                <input type="hidden" name="rankId" id="rankId" class="layui-input searchVal">

                <div class="layui-input-inline">
                    <input type="text" name="roomNumber"
                           class="layui-input searchVal" placeholder="房间号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="theme"

                           class="layui-input searchVal" placeholder="主题" />
                </div>

                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>


    <#--容器-->
    <table id="conferenceClassList" class="layui-table"  lay-filter="conference"></table>

    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加会议
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除会议
            </a>
        </div>
    </script>

    <!--行工具操作-->
    <script id="op" type="text/html" >
        <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="edit">详情</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/conference/conference.js"></script>

</body>


</html>