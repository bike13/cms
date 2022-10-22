<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id"  value="${(speed.id)!}">
    <input type="hidden" name="userId"  value="${(speed.userId)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-col-xs4">
            <label class="layui-form-label">视屏进度</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       lay-verify="required" name="videoSpStart" id="videoSpStart" placeholder="开始"  value="<#if (speed.videoSpStart)??>${(speed.videoSpStart)?c}</#if>">
            </div>
        </div>
        <div class="layui-col-xs4">
            <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 40px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: center;">~</label>
            <div class="layui-input-block" style="margin-left: 70px;width: 60px;">
                <input type="text" class="layui-input"
                       lay-verify="required" name="videoSpEnd" id="videoSpEnd" placeholder="结束" value="<#if (speed.videoSpEnd)??>${(speed.videoSpEnd)?c}</#if>">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-col-xs4">
            <label class="layui-form-label">代码进度</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       lay-verify="required" name="codeSpStart" id="codeSpStart" placeholder="开始"  value="<#if (speed.codeSpStart)??>${(speed.codeSpStart)?c}</#if>">
            </div>
        </div>
        <div class="layui-col-xs4">
            <label style="float: left;
                        display: block;
                        padding: 9px 15px;
                        width: 40px;
                        font-weight: 400;
                        line-height: 20px;
                        text-align: center;">~</label>
            <div class="layui-input-block" style="margin-left: 70px;width: 60px;">
                <input type="text" class="layui-input"
                       lay-verify="required" name="codeSpEnd" id="codeSpEnd" placeholder="结束" value="<#if (speed.codeSpEnd)??>${(speed.codeSpEnd)?c}</#if>">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">笔记情况</label>
        <div class="layui-input-inline">
            <select name="note"  id="note"  >
                <option value="" >请选择笔记完成情况</option>
                <option value="1" <#if speed??><#if "${speed.note?c}" == "1"> selected = selected</#if></#if>>笔记完成</option>
                <option value="0" <#if speed??><#if "${speed.note?c}" == "0"> selected = selected</#if></#if>>笔记未完成</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学习问题</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入学习问题" name="studyQuestion" id="studyQuestion" class="layui-textarea">${(speed.studyQuestion)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程问题</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入课程问题" name="classQuestion" id="classQuestion" class="layui-textarea">${(speed.classQuestion)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="备注" name="remark" id="remark" class="layui-textarea">${(speed.remark)!}</textarea>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateSaleChance">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/speed/add.update.js"></script>
</body>
</html>