layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /**
     * 关闭弹出层
     *
     */
    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);// 得到当前iframe层的索引
        parent.layer.close(index);// 执行关闭
    });

    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/crew/insert";
        var speedId=$("input[name='id']").val();
        if(speedId !=null && speedId != ''){
            url=ctx+"/crew/update";
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