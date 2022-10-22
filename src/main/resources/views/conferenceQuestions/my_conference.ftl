<!DOCTYPE html>
<html>
<head>
    <title>会议室管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<#--搜索框 主题 房间号-->
<blockquote class="layui-elem-quote quoteBox">
    <div class="layui-inline">
        <form class="layui-form">
            <div class="layui-input-inline">
                <input type="text" class="layui-input"
                       id="date" name="createTime" placeholder="开始时间"
                       value="<#if conferenceNowQuery.createTime??>${(conferenceNowQuery.createTime)?string('yyyy-MM-dd')}</#if>"
                />

            </div>

            <div class="layui-input-inline">
                <input type="text" name="roomNumber"
                       class="layui-input searchVal" placeholder="房间号"
                       value="<#if conferenceNowQuery.roomNumber??>${(conferenceNowQuery.roomNumber)}</#if>"
                />
            </div>

            <#--会议等级-->
            <div class="layui-input-inline">
                <select name="rankId" id="rank">
                    <option value="">会议等级</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-lg" lay-submit=""
                        lay-filter="search">搜索
                </button>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-lg" lay-submit=""
                        lay-filter="allQuestions">问题汇总
                </button>
            </div>
        </form>
    </div>
</blockquote>




<#--时间线-->
<ul class="layui-timeline" id="timeline">
    <#if conferences??>
        <#list conferences as conference>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-input-inline">
                        <h3 class="layui-timeline-title" id="createDate" name="createDate">${(conference.createTime)?string('yyyy-MM-dd')}</h3>
                    </div>
                    <div class="layui-input-inline layui-col-md-offset1">
                        <h3 class="layui-timeline-title" id="rankId" name="rankId">
                            <#if conference.rankId == 1>
                                <div style='color: #00b7ee '>  班级会议</div>
                            <#else>
                                <div style='color: lightgreen'>  小组会议</div>
                            </#if>
                        </h3>
                    </div>
                    <p>
                        <#--偏移layui-col-md-offset1-->
                    <div class="layui-col-md8">
                        <table class="layui-table">
                            <colgroup>
                                <col width="50">
                                <col width="100">
                                <col>
                            </colgroup>
                            <tbody>
                            <tr>
                                <td>会议主题</td>
                                <td>${(conference.theme)!}</td>
                            </tr>
                            <tr>
                                <td>会议地点</td>
                                <td>${(conference.roomNumber)!}</td>
                            </tr>
                            <tr>
                                <td>开始时间</td>
                                <td>${(conference.createTime)?string('yyyy-MM-dd HH:mm')}</td>
                            </tr>
                            <tr>
                                <td>结束时间</td>
                                <td>${(conference.endTime)?string('yyyy-MM-dd HH:mm')}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="layui-form-item layui-row layui-col-xs12">
                        <form class="layui-form" style="width:80%;">

                            <input name="id" type="hidden" value="${(conference.id)!}"/>

                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-lg" lay-submit=""
                                        lay-filter="contents">进入会议
                                </button>
                            </div>
                        </form>
                    </div>
                    </p>
                </div>
            </li>
        </#list>
    </#if>
</ul>


<script type="text/javascript" src="${ctx}/js/conferenceQuestions/my.conference.js"></script>

</body>
</html>