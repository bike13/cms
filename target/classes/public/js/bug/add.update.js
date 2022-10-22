layui.use(['form', 'layer', 'layedit'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var layedit = layui.layedit;

    var bugContent = layedit.build('bugContent',{
        height: 200,
        tool:[  'strong' //加粗
            ,'italic' //斜体
            ,'underline' //下划线
            ,'del' //删除线
            ,'link' //超链接

            ,'left' //左对齐
            ,'center' //居中对齐
            ,'right' //右对齐
            ,'face' //表情
        ],
        readonly: true
    });
    var answer = layedit.build('answer',{
        height: 200,
        tool:[  'strong' //加粗
            ,'italic' //斜体
            ,'underline' //下划线
            ,'del' //删除线
            ,'link' //超链接
            ,'left' //左对齐
            ,'center' //居中对齐
            ,'right' //右对齐
            ,'face' //表情
        ],
        readonly: true
    });
    form.verify({
        //content富文本域中的lay-verify值
        bugContent: function(value) {
            return layedit.sync(bugContent);
        },
        answer: function(value) {
            return layedit.sync(answer);
        }
    });


    form.on('submit(addBug)',function (data) {
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/bug/add";
        $.post(url,data.field,function (res) {
            if(res.code==200){
                top.layer.msg("操作成功");
                // 关闭加载层
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新加载数据
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });

    form.on('submit(updateBug)',function (data) {
        var index= top.layer.msg("请求提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/bug/update";
        $.post(url,data.field,function (res) {
            if(res.code==200){
                top.layer.msg("操作成功");
                // 关闭加载层
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新加载数据
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });

    form.on('submit(deleteBug)',function (data) {
        var index= top.layer.msg("请求提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/bug/deleteOnly";
        $.post(url,data.field,function (res) {
            if(res.code==200){
                top.layer.msg("操作成功");
                // 关闭加载层
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新加载数据
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });

    form.on('submit(answer)',function (data) {
        var index= top.layer.msg("请求提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/bug/addAnswer";

        $.post(url,data.field,function (res) {
            if(res.code==200){
                top.layer.msg("操作成功");
                // 关闭加载层
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新加载数据
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        // 当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index); // 再执行关闭
    });

});