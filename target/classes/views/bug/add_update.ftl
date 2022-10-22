<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="bugId" id="bugId" value="${(bug.bugId)!}">
    <input type="hidden" name="solve" id="solve" value="${(bug.solve)!}">
    <#if bug??>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label"></label>
            <div class="layui-input-block">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>发布人:${(trueName)!}</legend>
                </fieldset>
            </div>
        </div>
    </#if>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">bug标题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="bugTitle" id="bugTitle"  value="${(bug.bugTitle)!}"  placeholder="请输入bug标题" <#if bug??><#if !flag!>readonly="true"</#if></#if>>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">bug内容</label>
        <div class="layui-input-block">
            <#if !bug??>
                <textarea type="text" name="bugContent" id="bugContent" lay-verify="bugContent" placeholder="请输入bug内容" style="width:99%"></textarea>
            <#else>
                <#if flag!>
                    <textarea type="text" name="bugContent" id="bugContent" lay-verify="bugContent" placeholder="请输入bug内容" style="width:99%">${(bug.bugContent)!}</textarea>
                <#else>
                    <blockquote class="layui-elem-quote layui-quote-nm">${(bug.bugContent)!}<br></blockquote>
                </#if>
            </#if>
        </div>
    </div>

    <#if bug??>
    <#--详情-->
        <#if list??>
        <#--有回答-->
            <#list list as c>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label"><h3>回答${c_index+1}:</h3></label>
                    <div class="layui-input-block">
                        <blockquote class="layui-elem-quote">${c["answer"]}<br>(该回答来自于:${c["man"]})</blockquote>
                    </div>
                </div>
            <#else>
            </#list>
        </#if>
    <#--没有回答-->
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">回答${count+1}</label>
            <div class="layui-input-block">
                <textarea type="text" name="answer" id="answer" lay-verify="answer" placeholder="请回答" style="width:99%"></textarea>
            </div>
        </div>
    </#if>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12" >
        <div class="layui-input-block">
            <#if !(bug.bugId)??>
                <button class="layui-btn layui-btn-lg" lay-submit=""
                        lay-filter="addBug">确定
                </button>
            </#if>
            <#if bug??>
                <button class="layui-btn layui-btn-lg" lay-submit=""
                        lay-filter="answer">回答
                </button>
                <#if flag!>
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="updateBug">修改
                    </button>
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="deleteBug">删除
                    </button>
                </#if>
            </#if>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/bug/add.update.js"></script>
</body>
</html>