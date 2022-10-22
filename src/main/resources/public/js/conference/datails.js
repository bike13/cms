layui.use(['form','laydate','layer'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    /**
     * 关闭弹出框
     */
    $("#closeBtn").click(function (){
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

    var participants = $("#participants").val();
    var tr= $("[name='participants']").val()


    /**
     * 加载参加者
     */
    $.ajax({
        type:"post",
        url: ctx+"/conference/queryParticipants?id="+$("[name='id']").val(),
        data:{},
        success:function (data){
            console.log(data);
            if (data!=null){
                var tr= null;
                var td= "";
                var groupIds=new Array();
                //根据data求组的最大最小值 提组id到数组
                for (var i=0;i<data.length;i++){
                    groupIds.push(data[i].groupId);
                }
                //遍历,设置tr
                //1-6循环
                var min=Math.min.apply(null,groupIds);
                var max=Math.max.apply(null,groupIds);
                for (var i=min;i< max+1;i++){

                    for (var j=0;j<data.length;j++){
                        if (data[j].groupId==i){
                            td +=" "+data[j].trueName+" ";
                        }
                    }
                    tr="<tr>\n" +
                        "    <td>"+i+"组</td>\n" +
                        "    <td>"+td+"</td>\n" +
                        "</tr>"
                    $("#participants").append(tr);
                    td = "";
                }
            }
        }
    })



});