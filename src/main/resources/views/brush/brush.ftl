<!DOCTYPE html>
<html>
<head>
    <title>快乐刷题</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<table id="munu-table" class="layui-table" lay-filter="munu-table"></table>

<!-- 操作列 -->
<script type="text/html" id="auth-state">
    <a class="layui-btn layui-bg-blue layui-btn-xs"  lay-event="detail">详情</a>
</script>


<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="expand">
            <i class="layui-icon">&#xe608;</i>
            全部展开
        </a>
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="fold">
            <i class="layui-icon">&#xe608;</i>
            全部折叠
        </a>
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            发布作业
        </a>
    </div>
</script>

<script type="text/javascript" src="${ctx}/js/brush/brush.js"></script>
</body>
</html>