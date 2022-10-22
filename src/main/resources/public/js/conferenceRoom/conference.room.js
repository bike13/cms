layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;



    //第一个实例
    var tableIns= table.render({
        id:'conferenceRoomTable'
        ,elem: '#conferenceRoomlist'
        ,height: "full-125"
        ,cellMinWidth:95
        ,url: ctx+'/conferenceRoom/list' //数据接口
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
            {field: 'roomNumber', title: '房间号',align:'center'},
            {field: 'roomName', title: '房间名称',align:'center'},
            {field: 'createName', title: '创建人',align:'center'},
            //绑定行工具栏
            {title: '操作', templet:'#op',fixed:"right",align:"center", minWidth:150}
        ]]
    })

    $(".search_btn").click(function (){
        // 重载 - 这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                roomNumber: $("[name='roomNumber']").val() // 客户名
                ,roomName: $("[name='roomName']").val()  // 房间名称
                ,createName: $("[name='createName']").val()  // 房间名称
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    //头工具栏事件  添加/删除
    table.on('toolbar(conferenceRoom)', function(obj){
        console.log(obj);
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                //添加房间
                toAddorUpdataConferenceRoom();
                break;
            case "del":
                //删除房间
                delConferenceRoom(checkStatus.data);
                break;
        };
    });


    //行监听  tool(saleChances) tool行  lay-filter="saleChances"容器id
    table.on('tool(conferenceRoom)', function(obj){
        console.log(obj);
        switch(obj.event){
            case "dev":
                //修改房间
                toAddorUpdataConferenceRoom(obj.data.id);
                break;
            case "del":
                //删除房间
                layer.confirm('确定删除该选中的会议室吗?',{icon:3},function (index){
                    //确定删除,关闭
                    layer.close(index);
                    //请求后台
                    $.ajax({
                        type: "post",
                        url: ctx+"/conferenceRoom/delete",
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
    function toAddorUpdataConferenceRoom(id){
        var url  =  ctx+"/conferenceRoom/toAddAndUpdataPage";
        var title="<h2>会议室-添加会议室</h2>";
        //saleChanceId==null添加,e修改
        if (id!=null && id!=""){
            title="<h2>会议室-修改会议室</h2>";
            url = url+"?id="+id;
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["450px","250px"],
            //最大最小化
            maxmin:true,
            content : url
        });
    }

    //head-删除操作窗口
    function delConferenceRoom(datas){
        console.log(datas);
        //判断
        if (datas.length<1){
            //没有选中
            layer.msg("请选择要删除的会议室!",{icon:5});
            return ;
        }
        layer.confirm('确定删除该选中的会议室吗?',{icon:3,title:"会议室-删除会议室"},function (index){
            //关闭询问框
            layer.close(index);
            //提取数据
            var ids="ids=";
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
                url: ctx+"/conferenceRoom/delete",
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
