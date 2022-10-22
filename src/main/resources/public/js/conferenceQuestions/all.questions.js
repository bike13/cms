layui.use(['table','layer','element'],function(){
    var element = layui.element;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var rankId=1;

    //选项卡切换事件
    element.on('tab(conference)', function(data){
        var index=data.index;
        //console.log(data.index); //得到当前Tab的所在下标
        if (index==0){
            //点击第一组
            $("#groupId").val("1");
        }else if (index==1){
            //点击第2组
            $("#groupId").val("2");
        }else if (index==2){
            //点击第3组
            $("#groupId").val("3");
        }else if (index==3){
            //点击第4组
            $("#groupId").val("4");
        }else if (index==4){
            //点击第5组
            $("#groupId").val("5");
        }else if (index==5){
            //点击第6组
            $("#groupId").val("6");
        }
        loadData()
    });

    function loadData(){
        // 重载 - 这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                groupId: $("[name='groupId']").val()  // 房间号
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }

    //数据表格请求后台,   所有会议
    var tableIns= table.render({
        elem: '#allQuestionlist'
        ,height: "full-100"
        ,cellMinWidth:75
        ,url: ctx+'/conferenceQuestions/AllList'
        ,page: true //开启分页
        ,limit:6
        ,limits:[6,10,15]
        //开启头部工具栏
        ,toolbar: '#toolbarDemo'
        ,cols: [[ //表头
            //sort: true有排序箭头
            {type: "checkbox", fixed:"center"},
            {field: 'id', title: '问题编号', sort: true, fixed: 'left'},
            {field: 'theme', title: '会议名称',align:'center'},
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
    table.on('toolbar(allQuestion)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "del":
                // 删除问题
                delQuestion(checkStatus.data);
                break;
        };
    });

    //行监听   lay-filter="myQuestion"
    table.on('tool(allQuestion)', function(obj){
        console.log(obj);
        switch(obj.event){
            case "details":
                //问题详情
                toUpdataQuestion(obj.data.id);
                break;
            case "edit":
                //问题回复
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


});
