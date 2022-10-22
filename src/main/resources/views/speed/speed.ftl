<!DOCTYPE html>
<html>
<head>
	<title>进度汇总</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<blockquote class="layui-elem-quote quoteBox">
	<form class="layui-form">
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input class="layui-input" id="updateTime" name="updateTime" placeholder="年-月-日">
			</div>
			<a class="layui-btn search_btn" data-type="reload"><i
						class="layui-icon">&#xe615;</i> 搜索</a>
		</div>
	</form>
</blockquote>

<form class="layui-form" >

	<table id="saleChanceList" class="layui-table"  lay-filter="saleChances"></table>

	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				增加
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
				<i class="layui-icon">&#xe608;</i>
				删除
			</a>
		</div>
	</script>


	<!--操作-->
	<script id="saleChanceListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>

</form>
<script type="text/javascript" src="${ctx}/js/speed/speed.js"></script>

</body>
</html>