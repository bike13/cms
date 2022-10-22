layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    form.on('submit(signBtn)',function (data) {
        data =data.field;
        $.ajax({
            type:"post",
            url:ctx+"/user/sign",
            data:{
                userId:data.userId
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg("签到成功！",function (){
                        window.parent.location.reload();
                    })
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    })
});