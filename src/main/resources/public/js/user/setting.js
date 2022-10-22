layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    form.on('submit(saveBtn)',function (data) {
        data =data.field;
        $.ajax({
            type:"post",
            url:ctx+"/user/updateSetting",
            data:{
                userId:data.userId,
                phone:data.phone,
                qq:data.qq
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg("资料修改成功！",function (){
                        window.parent.location.reload();
                    })
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    })
});