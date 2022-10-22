<!DOCTYPE html>
<html>
<head>
    <title>会议预约</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">

<div class="layui-tab" lay-filter="conference">
    <ul class="layui-tab-title">
        <li class="layui-this">一组问题</li>
        <li >二组问题</li>
        <li >三组问题</li>
        <li >四组问题</li>
        <li >五组问题</li>
        <li >六组问题</li>
    </ul>
</div>


<form class="layui-form" >

    <#--容器-->
    <table id="allQuestionlist" class="layui-table"  lay-filter="allQuestion"></table>
    <#--隐藏域等级-->
    <input type="hidden" name="groupId" id="groupId" class="layui-input searchVal">


    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除问题
            </a>
        </div>
    </script>

    <!--行工具操作-->
    <script id="op" type="text/html" >
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/conferenceQuestions/all.questions.js"></script>

</body>


</html>