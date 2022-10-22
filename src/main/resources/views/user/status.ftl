<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人状态</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<br><br>
    <h2 style="text-align: center">亲爱的${(user.trueName)!}，开始今天的学习之旅吧！</h2>
<br><br>
<hr>
<form class="layui-form" style="width:100%;">
    <input name="userId" type="hidden" value="${(user.userId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号 :</label>
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
    <hr>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 :</label>
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
    <hr>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">视频进度 :</label>
        <div class="layui-input-block">
            <#if (speedListQuery.videoSp)??>
                <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">${(speedListQuery.videoSp)!"未汇总！"}</label>
            <#else>
                <label style="float: left;
                            display: block;
                            padding: 9px 15px;
                            width: 80px;
                            color: red;
                            font-weight: 400;
                            line-height: 20px;
                            text-align: left;">未汇总</label>
            </#if>
        </div>
    </div>
    <hr>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">代码进度 :</label>
        <div class="layui-input-block">
            <#if (speedListQuery.codeSp)??>
                <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">${(speedListQuery.codeSp)!}</label>
            <#else>
                <label style="float: left;
                            display: block;
                            padding: 9px 15px;
                            width: 80px;
                            color: red;
                            font-weight: 400;
                            line-height: 20px;
                            text-align: left;">未汇总</label>
            </#if>
        </div>
    </div>
    <hr>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">笔记情况 :</label>
        <div class="layui-input-block">
            <#if (speedListQuery.note)??>
                <#if speedListQuery.note==1>
                    <label style="float: left;
                            display: block;
                            padding: 9px 15px;
                            width: 80px;
                            color: #00B83F;
                            font-weight: 400;
                            line-height: 20px;
                            text-align: left;">已完成</label>
                <#else>
                    <label style="float: left;
                            display: block;
                            padding: 9px 15px;
                            width: 80px;
                            color: red;
                            font-weight: 400;
                            line-height: 20px;
                            text-align: left;">未完成</label>
                </#if>
            <#else>
                <label style="float: left;
                            display: block;
                            padding: 9px 15px;
                            width: 80px;
                            color: red;
                            font-weight: 400;
                            line-height: 20px;
                            text-align: left;">未汇总</label>
            </#if>
        </div>
    </div>
    <hr>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label layui-col-xs-offset3">签到状态 :</label>
        <div class="layui-input-block">
            <#if user.sign==1>
                <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        color: #00B83F;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">已签到</label>
            <#else>
                <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 80px;
                        color: red;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: left;">未签到</label>
                <span class="layui-btn layui-btn-sm" lay-submit="" lay-filter="signBtn">签到</span>
            </#if>
        </div>
    </div>
</form>
<hr>
<script type="text/javascript" src="${ctx}/js/user/status.js"></script>

</body>
</html>