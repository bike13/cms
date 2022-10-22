<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" style="width:80%;">
    <#-- 隐藏域id-->
    <input name="id" type="hidden" value="${(conferenceQuestions.id)!}"/>
    <input name="questionState" type="hidden" value="${(conferenceQuestions.questionState)!}"/>

    <#--问题状态-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">问题状态</label>
        <div class="layui-input-block">
            <select name="questionState" id="rank">
                <option value="0">请选择</option >
                <#if 1 == conferenceQuestions.questionState >
                    <option selected value="1">已解决</option>
                    <option value="2">未解决</option>

                <#elseif 2 == conferenceQuestions.questionState >
                    <option value="1">已解决</option>
                    <option selected value="2">未解决</option>

                </#if>
            </select>
        </div>
    </div>

    <#--会议详情-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">回复</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入会议详情" name="answer" class="layui-textarea">${(conferenceQuestions.answer)!}</textarea>
        </div>
    </div>
    <br/>
    <#if 2 == conferenceQuestions.questionState >
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdate">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
    </#if>
</form>
<script type="text/javascript" src="${ctx}/js/conferenceQuestions/add.updata.js"></script>
</body>
</html>