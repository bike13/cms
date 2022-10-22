layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //营销机会列表展示
    var  tableIns = table.render({
        // 容器元素的ID属性值
        elem: '#bugList',
        // 访问数据的URL
        url : ctx+'/bug/list',
        // 单元格最小的宽度
        cellMinWidth : 95,
        // 开启分页
        page : true,
        // 容器的高度 full-差值
        height : "full-125",
        // 每页页数可选项
        limits : [10,15,20,25],
        // 每页默认显示数量
        limit : 10,
        // 开启头部工具栏
        toolbar: "#toolbarDemo",
        id : "bugListTable",
        // 表头
        cols : [[
            // field：要求field属性值与返回的数据中对应的属性字段名一致
            // title：设置列的标题
            // sort：是否允许排序（默认：false）
            // fixed：固定列
            {type: "checkbox", fixed:"center"},
            {field: "bugId", title:'编号',fixed:"true"},
            {field: 'bugTitle', title: 'bug标题',align:"center"},
            {field: 'trueName', title: '发布人',align:"center"},
            {field: 'solve', title: '解决情况',  align:'center', templet:function(d){
                return formatterState(d.solve);
            }},
            {title: '操作', templet:'#bugListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    function formatterState(solve){
        if(solve==0){
            return "<div style='color: #ff0000 '>未解决</div>";
        }else if(solve==1){
            return "<div style='color: green'>已解决</div>";
        }else{
            return "<div style='color: yellow'>未知</div>";
        }
    }



    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("bugListTable",{
            page:{
                curr:1
            },
            where:{
                bugTitle:$("input[name='bugTitle']").val(),// bug标题
                trueName:$("input[name='trueName']").val(),// 发布人
                solve:$("#solve").val()    //分配状态
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(bugList)',function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateBugDialog();
                break;
            case "del":
                delSaleChance(table.checkStatus(obj.config.id).data);
                break;
        }
    });


    function delSaleChance(datas){
        /**
         * 批量删除
         *   datas:选择的待删除记录数组
         */
        if(datas.length==0){
            layer.msg("请选择待删除记录!");
            return;
        }

        layer.confirm("确定删除选中的记录",{
            btn:['确定','取消']
        },function (index) {
            layer.close(index);
            // ids=10&ids=20&ids=30
            var bug_ids="bug_ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    bug_ids=bug_ids+datas[i].bugId+"&bug_ids=";
                }else{
                    bug_ids=bug_ids+datas[i].bugId;
                }
            }

            $.ajax({
                type:"post",
                url:ctx+"/bug/delete",
                data:bug_ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        layer.msg("删除成功！",function (){
                            tableIns.reload();
                        })
                    }else{
                        layer.msg(data.msg);
                    }
                }
            })

        })
    }



    table.on('tool(bugList)',function (obj) {
          var layEvent =obj.event;
          if(layEvent === "details"){
              openAddOrUpdateBugDialog(obj.data.bugId,obj.data.userId);
          }

    });



    /**
     * 打开添加或更新对话框
     */
    function openAddOrUpdateBugDialog(bug_id,user_id) {
        var title="bug集锦-bug添加";
        var url=ctx+"/bug/addOrUpdateBugPage";
        if(bug_id){
            title="bug集锦-bug详情";
            url=url+"?bug_id="+bug_id+"&user_id="+user_id;
        }
        layui.layer.open({
            // 标题
            title:title,
            // 类型
            type:2,
            // 宽高
            area:["700px","500px"],
            // 可以最大化与最小化
            maxmin:true,
            content:url
        })
    }


});
