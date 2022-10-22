layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#closeBtn").click((function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }));

    form.on('submit(addOrUpdateCusDevPlan)',function (data) {
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});

        var url = ctx+"/cus_dev_plan/save";

        if($("input[name='id']").val()){
            url=ctx+"/cus_dev_plan/update";
        }

        $.post(url,data.field,function (res) {
            if(res.code==200){
                top.layer.msg("操作成功");
                top.layer.close(index);
                layer.closeAll("iframe");
                // 刷新父页面
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });



});