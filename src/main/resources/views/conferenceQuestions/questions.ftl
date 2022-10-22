<!DOCTYPE html>
<html>
<head>
    <title>会议室管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
    <#--隐藏域 会议id-->
    <input name="conferenceId" type="hidden" value="${(conference.id)!}"/>

    <fieldset class="layui-elem-field layui-field-title">
        <legend>会议详情</legend>
        <div class="layui-field-box">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">会议主题</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" readonly
                           lay-verify="required" name="theme"  value="${(conference.theme)!}" >
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">会议描述</label>
                <div class="layui-input-block">
                    <textarea name="details" readonly
                              class="layui-textarea">${(conference.details)!}</textarea>
                </div>
            </div>
        </div>
    </fieldset>

    <#--2,提出问题-->

    <fieldset class="layui-elem-field layui-field-title">
        <legend>提出问题</legend>
        <div class="layui-field-box">
            <form class="layui-form" >
                <#--会议等级-->
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">指定人</label>
                    <div class="layui-input-block">
                        <select name="Name" id="rank">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">问题描述</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入问题描述" name="question" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-lg" lay-submit=""
                                lay-filter="putQuestion">提出
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>

   <#-- 3,我的问题(接收到的问题)-->

    <fieldset class="layui-elem-field layui-field-title">
        <legend>我的问题</legend>
        <div class="layui-field-box">
            <table id="myQuestionlist" class="layui-table"  lay-filter="myQuestion"></table>
            <#--头部工具栏-->
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        删除问题
                    </a>
                </div>
            </script>

            <!--行工具操作-->
            <script id="op" type="text/html" >
            </script>

        </div>
    </fieldset>

    <script type="text/javascript" src="${ctx}/js/conferenceQuestions/questions.js"></script>

</body>
</html>