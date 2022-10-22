layui.use(['table','layer','form'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    var form = layui.form;


    //数据表格请求后台,查本次会议,中对当前用户的问题
    var tableIns= table.render({
        id:'myQuestionTable'
        ,elem: '#myQuestionlist'
        ,height: "full-250"
        ,cellMinWidth:75
        ,url: ctx+'/conferenceQuestions/myList?conferenceId='+$("[name='conferenceId']").val() //数据接口
        ,page: true //开启分页
        ,limit:10
        ,limits:[10,20,30]
        //开启头部工具栏
        ,toolbar: '#toolbarDemo'
        ,cols: [[ //表头
            //sort: true有排序箭头
            {type: "checkbox", fixed:"center"},
            {field: 'id', title: '问题编号', sort: true, fixed: 'left'},
            {field: 'emitName', title: '建议人',align:'center'},
            {field: 'recipientname', title: '问题人',align:'center'},
            {field: 'question', title: '问题描述',align:'center'},
            {field: 'questionState', title: '问题状态',align:'center',templet: function (d){
                    return formatState(d.questionState);//返回状态
                }},
            {title: '操作', templet:'#op',fixed:"right",align:"center",templet: function (d){
                    return formatOperate(d.questionState);//返回状态
                } }

        ]]
    })
    //返回操作
    function formatOperate(questionState){
        if (questionState==1){
            //问题状态已解决
            return "<a class='layui-btn layui-btn-warm layui-btn-xs'  lay-event='details'>详情</a>" +
                "<a class=\"layui-btn layui-btn-normal layui-btn-xs\"  lay-event=\"del\">删除</a>";
        }else if(questionState==2){
            //问题状态未解决
            return "<a class='layui-btn layui-btn-warm layui-btn-xs'  lay-event='edit'>回复</a>" +
                "<a class=\"layui-btn layui-btn-normal layui-btn-xs\"  lay-event=\"del\">删除</a>";
        }
    }

    //返回状态
    function formatState(questionState){
        if (questionState==1){
            return "<div style='color:blue '>已解决</div>";
        }else if(questionState==2){
            return "<div style='color: chartreuse'>未解决</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }

    //头工具栏事件  删除  lay-filter="myQuestion"
    table.on('toolbar(myQuestion)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "del":
                // 删除问题
                delQuestion(checkStatus.data);
                break;
        };
    });


    //行监听   lay-filter="myQuestion"
    table.on('tool(myQuestion)', function(obj){
        console.log(obj);
        switch(obj.event){
            case "details":
                //问题详情
                toUpdataQuestion(obj.data.id);
                break;
            case "edit":
                //问题详情
                toUpdataQuestion(obj.data.id);
                break;
            case "del":
                //删除问题
                layer.confirm('确定删除该选中的问题吗?',{icon:3},function (index){
                    //确定删除,关闭
                    layer.close(index);
                    //请求后台
                    $.ajax({
                        type: "post",
                        url: ctx+"/conferenceQuestions/delete",
                        data:{
                            ids: obj.data.id
                        },
                        success:function (result){
                            //成功|失败提示
                            if (result.code==200){
                                layer.msg("删除成功!",{icon:6});
                                //刷新表单 //第一个实例
                                tableIns.reload();
                            }else {
                                layer.msg(result.msg,{icon:5});
                            }
                        }
                    })
                })
                break;

        };
    });

    //打开弹出层窗口
    function toUpdataQuestion(id){
        var url  =  ctx+"/conferenceQuestions/toUpDataPage?id="+id;
        var title="<h2>问题-问题详情</h2>";
        layui.layer.open({
            title : title,
            type : 2,
            area:["500px","300px"],
            //最大最小化
            maxmin:true,
            content : url
        });

    }


    //head-删除操作窗口
    function delQuestion(datas){
        console.log(datas);
        //判断
        if (datas.length<1){
            //没有选中
            layer.msg("请选择要删除的会议!",{icon:5});
            return ;
        }
        layer.confirm('确定删除该选中的会议吗?',{icon:3,title:"会议室-删除会议"},function (index){
            //关闭询问框
            layer.close(index);
            //提取数据
            var ids= "ids=" ;
            for (var i=0;i <datas.length;i++){
                if (i<datas.length-1){
                    ids = ids + datas[i].id+"&ids=";
                }else {
                    ids = ids + datas[i].id;
                }
            }
            console.log(ids);
            //请求后台
            $.ajax({
                type: "post",
                url: ctx+"/conferenceQuestions/delete",
                data: ids,
                success:function (result){
                    //成功|失败提示
                    if (result.code==200){
                        layer.msg("删除成功!",{icon:6});
                        //刷新表单 //第一个实例
                        tableIns.reload();
                    }else {
                        layer.msg(result.msg,{icon:5});
                    }
                }
            })
        });
    }



    /**
     * 加载接收人的下拉框
     */
    $.ajax({
        type:"get",
        url: ctx+"/conference/queryParticipants?id="+$("[name='conferenceId']").val(),
        data:{},
        success:function (data){
            console.log(data);
            if (data!=null){
                var opt=null;
                for (var i=0;i<data.length;i++){
                    opt="<option >"+data[i].trueName +"</option>"

                    $("#rank").append(opt);
                }
            }
            //重新渲染下拉框
            layui.form.render("select")
        }
    })

    //提交按钮监听
    //添加问题
    form.on("submit(putQuestion)", function (data) {
        console.log(data);
        var url  =  ctx+"/conferenceQuestions/toAdd";
        var field={
            recipientname: data.field.Name
            ,question: data.field.question
            ,conferenceId: $("[name='conferenceId']").val()
        }
        console.log(field);
        $.post(url, field, function (res) {
            var index = top.layer.msg('数据提交中，请稍候', {
                icon: 16, //图标
                time: false,
                shade: 0.8
            });
            //登录成功
            if (res.code == 200) {
                top.layer.msg("操作成功！",{icon:6});
                top.layer.close(index);
                //关闭弹出层
                layer.closeAll("iframe");
                //刷新父页面
                location.reload();
            } else {
                layer.msg(
                    res.msg, {
                        icon: 5
                    }
                );
            }
        });
        return false;
    });


});
