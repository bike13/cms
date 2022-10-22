layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //营销机会列表展示
    var  tableIns = table.render({
        // 容器元素的ID属性值
        elem: '#saleChanceList',
        // 访问数据的URL
        url : ctx+'/sale_chance/list',
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
        id : "saleChanceListTable",
        // 表头
        cols : [[
            // field：要求field属性值与返回的数据中对应的属性字段名一致
            // title：设置列的标题
            // sort：是否允许排序（默认：false）
            // fixed：固定列
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'chanceSource', title: '机会来源',align:"center"},
            {field: 'customerName', title: '客户名称',  align:'center'},
            {field: 'cgjl', title: '成功几率', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'uname', title: '指派人', align:'center'},
            {field: 'assignTime', title: '分配时间', align:'center'},
            {field: 'state', title: '分配状态', align:'center',templet:function(d){
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    function formatterState(state){
        if(state==0){
            return "<div style='color:yellow '>未分配</div>";
        }else if(state==1){
            return "<div style='color: green'>已分配</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }

    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }



    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("saleChanceListTable",{
            page:{
                curr:1
            },
            where:{
                customerName:$("input[name='customerName']").val(),// 客户名
                createMan:$("input[name='createMan']").val(),// 创建人
                state:$("#state").val()    //分配状态
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(saleChances)',function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateSaleChanceDialog();
                break;
            case "del":
                //console.log(table.checkStatus(obj.config.id).data);
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
            var ids="ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg);
                    }
                }
            })



        })


    }



    table.on('tool(saleChances)',function (obj) {
          var layEvent =obj.event;
          if(layEvent === "edit"){
              openAddOrUpdateSaleChanceDialog(obj.data.id);
          }else if(layEvent === "del"){
              layer.confirm("确认删除当前记录?",{icon: 3, title: "机会数据管理"},function (index) {
                  // 关闭确认框
                  layer.close(index);
                  $.post(ctx+"/sale_chance/delete",{ids:obj.data.id},function (data) {
                      if(data.code==200){
                          layer.msg("删除成功");
                          tableIns.reload();
                      }else{
                          layer.msg(data.msg);
                      }
                  })
              })
          }

    });



    /**
     * 打开添加或更新对话框
     */
    function openAddOrUpdateSaleChanceDialog(sid) {
        var title="营销机会管理-机会添加";
        var url=ctx+"/sale_chance/addOrUpdateSaleChancePage";
        if(sid){
            title="营销机会管理-机会更新";
            url=url+"?id="+sid;
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
