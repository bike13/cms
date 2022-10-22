layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#updateTime',
            type: 'date'
        });
    });

    //用户列表展示
    var  tableIns = table.render({
        elem: '#saleChanceList',
        url : ctx+'/crew/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: 'updateTime', title: '日期', align:'center',edit:"text"},
            {field: 'videoSp', title: '视频进度',  align:'center',edit:"text"},
            {field: 'codeSp', title: '代码进度',  align:'center',edit:"text"},
            {field: 'note', title: '笔记完成状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.note);
                }},
            {field: 'studyQuestion', title: '学习问题', align:'center',edit:"text"},
            {field: 'classQuestion', title: '课程问题', align:'center',edit:"text"},
            {field: 'remark', title: '备注', align:'center',edit:"text"},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    function formatterDevResult(value){
        /**
         * 0-笔记完成
         * 1-笔记未完成

         */
        if(value==1){
            return "<div style='color: #00B83F'>已完成</div>";
        }else if(value==0) {
            return "<div style='color: red;'>未完成</div>";
        }
    }

    // 多条件搜索
    $(".search_btn").on("click",function () {

        var updateTime = $("input[name='updateTime']").val();   // 2022-10-11
        if (updateTime !== ""){
            updateTime = updateTime.replace(/-/g,"/");  // 2022/10/11
            var updateTime2 = updateTime + " 23:59:59"; // 2022/10/11 23:59:59
            updateTime = updateTime + " 00:00:00"       // 2022/10/11 00:00:00
            updateTime = new Date(updateTime);          // 字符串转日期格式
            updateTime2 = new Date(updateTime2);
        }else {
            updateTime = new Date();
            updateTime2 = new Date();
            console.log(updateTime);
        }

        table.reload("saleChanceListTable",{
            page:{
                curr:1 //重新从第 1 页开始
            },
            where:{
                updateTime: updateTime, // 日期格式
                updateTime2: updateTime2
            }
        })
    });

    //头工具栏事件
    table.on('toolbar(saleChances)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                $.ajax({
                    type:"post",
                    url:ctx+"/crew/judge",
                    dataType:"json",
                    success:function (data) {
                        if(data.code==200){
                            openAddOrUpdateSaleChanceDialog();
                        }else{
                            layer.msg(data.msg, {icon: 5});
                        }
                    }
                })
                break;
            case "del":
                delSaleChance(checkStatus.data);
                break;
        }
    });


    /**
     * 行监听
     */
    table.on("tool(saleChances)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            var speedId = obj.data.id;
            openAddOrUpdateSaleChanceDialog(speedId);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前数据？', {icon: 3, title: "任务进度管理"}, function (index) {
                $.post(ctx+"/crew/delete",{ids:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }
    });


    // 打开添加机会数据页面
    function openAddOrUpdateSaleChanceDialog(speedId){
        var url  =  ctx+"/crew/toAddUserPage";
        var title="任务进度-进度添加";
        if(speedId!=null&&speedId!=''){
            url = url+'?speedId='+speedId;
            title="任务进度-进度更新";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["700px","620px"],
            maxmin:true,
            content : url
        });
    }


    /**
     * 批量删除
     * @param datas
     */
    function delSaleChance(datas) {
        if(datas.length==0){
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }
        layer.confirm('确定删除选中的进度数据？', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close(index);
            var ids= "ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else {
                    ids=ids+datas[i].id
                }
            }
            $.ajax({
                type:"post",
                url:ctx+"/crew/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                }
            })
        });
    }




});
