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
            $("#rankId").val("1");
        }else {
            $("#rankId").val("2");
        }
        loadData()
    });

    function loadData(){
        // 重载 - 这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                rankId: $("[name='rankId']").val()  // 房间号
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }

    //班级会议加载
    var tableIns= table.render({
        id:'conferenceTable'
        ,elem: '#conferenceClassList'
        ,height: "full-150"
        ,cellMinWidth:95
        ,url: ctx+'/conference/list' //数据接口
        ,page: true //开启分页
        ,limit:10
        ,limits:[10,20,30]
        //开启头部工具栏
        ,toolbar: '#toolbarDemo'
        ,cols: [[ //表头
            //sort: true有排序箭头
            //复选框
            {type: "checkbox", fixed:"center"},
            {field: 'id', title: '编号', sort: true, fixed: 'left'},
            {field: 'rankId', title: '等级', align:'center',templet: function (d){
                    return formatRank(d.rankId);//返回状态
                }},
            {field: 'theme', title: '主题',align:'center'},
            {field: 'roomNumber', title: '房间号',align:'center'},
            {field: 'userName', title: '创建人',align:'center'},
            {field: 'createTime', title: '创建时间',align:'center'},
            {field: 'endTime', title: '结束时间',align:'center'},
            //绑定行工具栏
            {title: '操作', templet:'#op',fixed:"right",align:"center", minWidth:150}
        ]]
    })

    //搜索按钮
    $(".search_btn").click(function (){
        // 重载 - 这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                theme: $("[name='theme']").val() // 主题
                ,roomNumber: $("[name='roomNumber']").val()  // 房间号
                ,rankId: $("[name='rankId']").val()  // 房间号
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })


    function formatRank(rankId){
        if (rankId==1){
            return "<div style='color:blue '>班级会议</div>";
        }else if(rankId==2){
            return "<div style='color: chartreuse'>小组会议</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }


    //头工具栏事件  添加/删除
    table.on('toolbar(conference)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                //添加会议
                toAddorUpdataConference();
                break;
            case "del":
                // 删除会议
                delConference(checkStatus.data);
                break;
        };
    });


    //行监听  tool(saleChances) tool行  lay-filter="saleChances"容器id
    table.on('tool(conference)', function(obj){
        console.log(obj);
        switch(obj.event){
            case "edit":
                //编辑
                toAddorUpdataConference(obj.data.id);
                break;
            case "details":
                //详情
                toDetailsConferencePage(obj.data.id);
                break;
            case "del":
                //删除会议
                layer.confirm('确定删除该选中的会议吗?',{icon:3},function (index){
                    //确定删除,关闭
                    layer.close(index);
                    //请求后台
                    $.ajax({
                        type: "post",
                        url: ctx+"/conference/delete",
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

    //打开详情会议界面
    function toDetailsConferencePage(id){
        var url  =  ctx+"/conference/toDetailsConferencePage?id="+id;
        var title="<h2>会议-详情会议</h2>";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","550px"],
            //最大最小化
            maxmin:true,
            content : url
        });
    }

    //打开弹出层窗口
    function toAddorUpdataConference(id){
        var url  =  ctx+"/conference/toAddAndUpdataPage?rankId="+$("[name='rankId']").val();
        var title="<h2>会议-添加会议</h2>";
        if (id!=null && id!=""){
            title="<h2>会议-修改会议</h2>";
            url = url+"&id="+id;
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["500px","550px"],
            //最大最小化
            maxmin:true,
            content : url
        });
    }


    //head-删除操作窗口
    function delConference(datas){
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
                url: ctx+"/conference/delete",
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
