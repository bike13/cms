<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">

</head>
<body class="childrenBody">
<br><br>
<form class="layui-form" style="width:80%;">
    <input name="userId" id="userId" type="hidden" value="${(userInfo.userId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="userName" id="userName"  value="${(userInfo.userName)!}" placeholder="请输入学号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="trueName" id="trueName" value="${(userInfo.trueName)!}" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userPhone"
                   lay-verify="Phone" name="phone" value="${(userInfo.phone)!}" id="phone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">QQ号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userQq"
                   lay-verify="qq" name="qq" value="${(userInfo.qq)!}" id="qq" placeholder="请输入QQ号码">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">组别</label>
        <div class="layui-input-block">
            <select name="groupId" id="groupId">
                <option value="">请选择组别</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 1>selected</#if> value="1" >1</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 2>selected</#if> value="2" >2</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 3>selected</#if> value="3" >3</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 4>selected</#if> value="4" >4</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 5>selected</#if> value="5" >5</option>
                <option <#if userInfo?? && (userInfo.groupId)! == 6>selected</#if> value="6" >6</option>
            </select>
        </div>
    </div>
<#--    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleIds"  xm-select="selectId">
            </select>
        </div>
    </div>-->


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/user/add.update.js"></script>

</body>
</html>