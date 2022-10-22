layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //计划项数据展示
    var  tableIns = table.render({
        elem: '#cusDevPlanList',
        url : ctx+'/cus_dev_plan/list?saleChanceId='+$("input[name='id']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "cusDevPlanListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', sort:true, fixed:"left"},
            {field: 'planItem', title: '计划项',align:"center"},
            {field: 'exeAffect', title: '执行效果',align:"center"},
            {field: 'planDate', title: '执行时间',align:"center"},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#cusDevPlanListBar"}
        ]]
    });


    table.on("toolbar(cusDevPlans)",function (obj) {
        switch (obj.event) {
            case "add" :
                openAddOrUpdateCusDevPlanDialog();
                break;
            case "success":
                updateSaleChanceDevResult($("input[name='id']").val(),2);
                break;
            case "failed":
                updateSaleChanceDevResult($("input[name='id']").val(),3);
                break;
        }
    });


    table.on("tool(cusDevPlans)",function (obj) {
        var layEvent = obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateCusDevPlanDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("确认删除当前记录?",{icon: 3, title: "客户开发计划管理"},function (index) {
                $.post(ctx+"/cus_dev_plan/delete",{id:obj.data.id},function (data) {
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



    function openAddOrUpdateCusDevPlanDialog(id) {
        var title="计划项管理管理-添加计划项";
        var url=ctx+"/cus_dev_plan/addOrUpdateCusDevPlanPage?sid="+$("input[name='id']").val();
        if(id){
            title="计划项管理管理-更新计划项";
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["500px","300px"],
            maxmin:true,
            content:url
        })
    }



    function updateSaleChanceDevResult(sid,devResult) {
        layer.confirm("确认更新机会数据状态?",{icon: 3, title: "客户开发计划管理"},function (index) {
            $.post(ctx+"/sale_chance/updateSaleChanceDevResult",{
                id:sid,
                devResult:devResult
            },function (data) {
                if(data.code==200){
                    layer.msg("机会数据更新成功");
                    layer.closeAll("iframe");
                    // 刷新父页面
                    parent.location.reload();
                }else{
                    layer.msg(data.msg);
                }
            })
        })
    }





});
