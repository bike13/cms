<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--隐藏域,会议id-->
    <input name="id" type="hidden" value="${(id)!}"/>
    <#--会议人员-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议人员</label>
        <div class="layui-input-block">
            <table class="layui-table">
                <#--表格占比-->
                <colgroup>
                    <col width="70">
                    <col width="350">
                </colgroup>
                <#--表头-->
                <thead>
                <tr>
                    <th>组别</th>
                    <th>人员</th>
                </tr>
                </thead>
               <#-- 表结构 value="${(conference.participants)!}-->
                <tbody id="participants">
                </tbody>
            </table>
        </div>
    </div>

    <#--会议详情-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议简介</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入会议详情" readonly name="details" class="layui-textarea">${(details)!}</textarea>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/conference/datails.js"></script>
</body>
</html>