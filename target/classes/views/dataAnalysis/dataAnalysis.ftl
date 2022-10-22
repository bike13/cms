<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8" />
    <title>小组智能分析</title>
    <#include "../common.ftl">
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="${ctx}/js/jquery/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>

    <script src="${ctx}/js/lay-module/echarts/echarts.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>
</head>
<body>

<div class="layui-form-item layui-row layui-col-xs12 quoteBox layui-elem-quote">
    <div style="float: left; margin-right: 10px;">
        <input class="layui-input" id="devResult" name="devResult" placeholder="年-月-日"
               value="<#list dates as date><#if !date_has_next>${date}</#if></#list>">
    </div>
    <#list 1..pageNum as x>
        <div style="float: left; margin-right: 10px;">
            <button class="layui-btn search_btn" type="button" onclick="loadModuleInfo(${x!})"><i
                        class="layui-icon">&#xe615;</i> ${x!}组</button>
        </div>
    </#list>
    <div style="float: left; margin-right: 10px;">
        <button class="layui-btn search_btn" type="button" onclick="loadModuleInfo(0)"><i
                    class="layui-icon">&#xe615;</i> 班级</button>
    </div>
    <#--<select name="devResult"  id="devResult"  >
        <option value="" >请选择日期</option>
        <#list dates as date>
            <#if !date_has_next>
                <option value="${date}" selected> ${date}</option>
            <#else >
                <option value="${date}" > ${date}</option>
            </#if>
        </#list>
    </select>-->
</div>

<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div class="layui-row">
    <div class="layui-col-md7">
        <div id="main" style="width: 700px;height:600px;"></div>
    </div>
    <div id="tables" class="layui-col-md5" style="background-color: rosybrown;">
        <table id="roleList" class="layui-table"  lay-filter="roles"></table>
        <div id="123">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>类别</th>
                    <th>分析</th>
                </tr>
                </thead>
                <tbody id="tdBd">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/dataAnalysis/dataAnalysis.js"></script>
</body>