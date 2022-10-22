<!DOCTYPE html>
<html>
<head>
    <title>bug集锦</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="bugTitle" class="layui-input searchVal" placeholder="bug标题" />
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" name="trueName" class="layui-input searchVal" placeholder="发布人" />
                    </div>
                    <div class="layui-input-inline">
                        <select name="solve"  id="solve">
                            <option value="" >解决情况</option>
                            <option value="0">未解决</option>
                            <option value="1" >已解决</option>
                        </select>
                    </div>
                    <a class="layui-btn search_btn" data-type="reload"><i
                                class="layui-icon">&#xe615;</i> 搜索</a>
                </div>
            </form>
        </blockquote>

    <table id="bugList" class="layui-table"  lay-filter="bugList"></table>


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


    <!--操作-->
    <script id="bugListBar" type="text/html">
            <a class="layui-btn layui-btn-xs" id="details" lay-event="details">详情</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/bug/bug.js"></script>

</body>
</html>