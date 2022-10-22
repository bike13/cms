layui.use(['form', 'layer', 'layedit'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var layedit = layui.layedit;
    /*layedit.set({	//设置图片接口
        uploadImage: {
            url: 'layEdit/upload', //图片上传接口url
            type: 'post'
        }
    });*/




    //创建一个编辑器
    /*添加问题*/
    var index = layedit.build('question',{
        height: 200,
        tool:[  'strong' //加粗
            ,'italic' //斜体
            ,'underline' //下划线
            ,'del' //删除线

            ,'left' //左对齐
            ,'center' //居中对齐
            ,'right' //右对齐
            ,'face' //表情
         ],
        readonly: true
    });
    /*//提交时把值同步到文本域中
    form.verify({
        //content富文本域中的lay-verify值
        question: function(value) {
            return layedit.sync(index);
        }
    });*/

    //创建一个编辑器
    var answer = layedit.build('answer',{
        height: 200,
        tool:[  'strong' //加粗
            ,'italic' //斜体
            ,'underline' //下划线
            ,'del' //删除线

            ,'left' //左对齐
            ,'center' //居中对齐
            ,'right' //右对齐
            ,'face' //表情
        ]
    });
    //提交时把值同步到文本域中
    form.verify({
        //content富文本域中的lay-verify值
        answer: function(value) {
            return layedit.sync(answer);
        },
        question: function(value) {
            return layedit.sync(index);
        }
    });

    /*查询所有的大纲*/
    $.post(ctx+"/brush/outline",function (res) {
        for(var i=0;i<res.length;i++){
            // 如果循环得到的ID与隐藏域的ID相等，则表示被选中
            if($("input[name='brushId']").val() == res[i].brushId){
                $("#parentId").append("<option value=\""+res[i].brushId+"\"  selected='selected' >"+res[i].question+"</option>");
            }else{
                $("#parentId").append("<option value=\""+res[i].brushId+"\"   >"+res[i].question+"</option>");
            }
        }
        // 重新渲染下拉框内容
        layui.form.render("select");
    });

    form.on('submit(addBrush)',function (data) {
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/brush/add";
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

    form.on('submit(updateAnswer)',function (data) {
        var index= top.layer.msg("请求提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/brush/update";
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
        var url = ctx+"/brush/addAnswer";
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