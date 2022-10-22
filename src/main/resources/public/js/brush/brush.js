layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery,
        table = layui.table,
        treeTable = layui.treetable;


    // 渲染表格
      treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'brushId',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx+'/brush/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {field: 'brushId', title: '序号',width: 100},
            {field: 'question', minWidth: 100, title: '作业'},
            {field: 'createTime', title: '创建时间',width:170 },
            {field: 'answer', width: 100,align: 'center', templet:function (result){
                /*先判断是不是题目*/
                if (result.parentId == -1){
                    /*不是题目*/
                    return result.num+"/"+result.count;
                }
                if (result.parentId != -1){
                    /*是题目*/
                    /*判断完成情况*/
                    if (result.answerId!=null){
                        return '<div style="color: green">已完成</div>';
                    }
                    if (result.answerId==null){
                        return '<div style="color: red">未完成</div>';
                    }
                }
                },title: '完成情况'},
            {
                field: 'parentId', width: 80, align: 'center', templet: function (d) {
                    if (d.parentId == -1) {
                        return '<span class="layui-badge-rim">大纲</span>';
                    }
                    if(d.parentId!=-1){
                        return '<span class="layui-badge-rim">题目</span>';
                    }
                }, title: '类型'
            },
            {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    // 头工具栏事件
    table.on('toolbar(munu-table)',function (obj) {
        switch (obj.event) {
            case "add":
                openAddAndUpdateModuleDialog();
                break;
            case "expand"://全部展开
                treeTable.expandAll('#munu-table');
                break;
            case "fold"://全部折叠
                treeTable.foldAll('#munu-table');
                break;
        }
    });


    table.on('tool(munu-table)',function (obj) {
        var layEvent =obj.event;
        if(layEvent === "detail"){
            openAddAndUpdateModuleDialog(obj.data.brushId);
            console.log(obj.data);
        }
    });



    function openAddAndUpdateModuleDialog(id) {
        var title="菜单管理-菜单添加";
        var content=ctx+"/brush/detail";
        if (id){
            title="菜单管理-菜单更新";
            content = content + "?brushId=" + id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:content
        })
    }




});