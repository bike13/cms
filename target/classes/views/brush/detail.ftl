<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#if "${flag?c}"=="true">
        <input type="hidden" name="brushId" id="brushId" value="${brush.brushId}">
    <#if "${brush.parentId?c}" == "-1">
    <#--如果是大纲，显示如下内容-->
        <fieldset class="layui-elem-field layui-field-title layui-border-red">
            <legend>该大纲下的题目为:</legend>
        </fieldset>
        <#--<h3>该大纲下的题目为:</h3><br/><hr class="layui-border-red"/>-->
        <#list list as c>
            <div class="layui-input-block">
                <blockquote class="layui-elem-quote"> <h2>${c_index+1}:</h2>${c.question}</blockquote>

            </div>
            <br/>
        </#list>
    </#if>
    </#if>

    <#if "${flag?c}"=="false">
        <#--添加-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">大纲</label>
        <div class="layui-input-block">
            <select name="parentId" id="parentId">
                <option value="-1" >新建大纲</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">作业内容</label>
        <div class="layui-input-block">
            <textarea type="text" name="question" id="question" lay-verify="question" placeholder="请输入作业内容" style="width:99%"></textarea>
        </div>
    </div>
    </#if>

    <#if "${flag?c}"=="true">
        <#--修改-->
    <#if "${brush.parentId?c}" != "-1">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">作业内容</label>
        <div class="layui-input-block">
            <#--<blockquote class="layui-elem-quote layui-quote-nm">
                ${brush.question}
            </blockquote>-->
            <blockquote class="layui-elem-quote">${brush.question}</blockquote>
<#--
            <textarea type="text" name="question" id="question" lay-verify="content" placeholder="请输入作业内容" style="width:99%">${brush.question}</textarea>
-->
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">回答</label>
        <#if answer??>
        <div class="layui-input-block">
            <textarea type="text" name="answer" id="answer" lay-verify="answer" placeholder="请作答" style="width:99%">${answer.answer}</textarea>
            <#--<input type="text" class="layui-textarea"
                   name="answer" id="answer" placeholder="请作答" value="${answer.answer}">-->
        </div>
        </#if>
        <#if !answer??>
            <div class="layui-input-block">
                <textarea type="text" name="answer" id="answer" lay-verify="answer" placeholder="请作答" style="width:99%"></textarea>
                <#--<input type="text" class="layui-textarea"
                       name="answer" id="answer" placeholder="请作答" value="">-->
            </div>
        </#if>
    </div>
    </#if>
    </#if>

    <div class="layui-form-item layui-row layui-col-xs12" >
        <div class="layui-input-block">
            <#if "${flag?c}"!="true">
            <#--添加时才会有确认按钮-->
            <button class="layui-btn layui-btn-lg " lay-submit=""
                    lay-filter="addBrush">确定
            </button>
            </#if>
            <#if "${flag?c}"=="true">
            <#if "${brush.parentId?c}" != "-1">
            <#--详情才会有回答按钮-->
            <#if !answer??>
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="answer">回答
            </button>
            </#if>

            <#if answer??>
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateAnswer">修改
            </button>
            </#if>
            </#if>
            </#if>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/brush/detail.js"></script>
</body>
</html>