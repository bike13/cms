<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" style="width:80%;">
    <#-- 隐藏域id-->
    <input name="id" type="hidden" value="${(conference.id)!}"/>
    <input name="rankId" type="hidden" value="${(conference.rankId)!}"/>
    <input name="roomNumber" type="hidden" value="${(conference.roomNumber)!}"/>
    <#--会议主题-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议主题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="theme"  value="${(conference.theme)!}" placeholder="请输入会议主题">
        </div>
    </div>
    <#--会议等级，(使用选项卡切换)-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议等级</label>
        <div class="layui-input-block">
            <select name="rankId" id="rank">
                <#if (rankId!1)==1 >
                    <option selected value='1'>班级会议</option>
                <#elseif (rankId!1)==2 >
                    <option selected value='2'>小组会议</option>
                </#if>
            </select>
        </div>
    </div>
    <#--会议房间-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议房间</label>
        <div class="layui-input-block">
            <select name="roomNumber" id="roomNumber">

                <option value="">请选择</option>

            </select>
        </div>
    </div>
    <#--开始时间 ${(conference.createTime)!}-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-row">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       id="dateTime" name="createTime" value="${(conference.createTime?string("yyyy-MM-dd HH:mm:ss"))!}" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>
    </div>
    <#--结束时间 ${(conference.endTime)!}-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-row">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       id="dateTime" name="endTime" value="${(conference.endTime?string("yyyy-MM-dd HH:mm:ss"))!}" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>
    </div>
    <#--会议详情-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会议详情</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入会议详情" name="details" class="layui-textarea">${(conference.details)!}</textarea>
        </div>
    </div>
    <br/>


    <#if (conference.id)??>
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

    </#if>


    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdate">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/conference/add.updata.js"></script>
</body>
</html>