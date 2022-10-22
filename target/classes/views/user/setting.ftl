<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<form class="layui-form" style="width:80%;">
    <input name="userId" type="hidden" value="${(user.userId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学&nbsp;&nbsp;&nbsp;&nbsp;号 :</label>
        <div class="layui-input-block">
            <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">${(user.userName)!}</label>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓&nbsp;&nbsp;&nbsp;&nbsp;名 :</label>
        <div class="layui-input-block">
            <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">${(user.trueName)!}</label>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">组&nbsp;&nbsp;&nbsp;&nbsp;别 :</label>
        <div class="layui-input-block">
            <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">${(user.groupId)!}</label>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号 :</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail" lay-verify="phone" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入手机号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">QQ :</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail" lay-verify="qq" name="qq" value="${(user.qq)!}" id="qq" placeholder="请输入QQ">
        </div>
    </div>

    <br><br>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <span class="layui-btn layui-btn-lg layui-col-xs-offset5" lay-submit="" lay-filter="saveBtn">提交修改</span>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/user/setting.js"></script>

</body>
</html>