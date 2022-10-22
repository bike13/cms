layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //用户列表展示
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/user/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            //{field: "userId", title:'编号',fixed:"true", width:80},
            {field: 'userName', title: '学号', minWidth:50, align:"center", sort:"true"},
            {field: 'trueName', title: '真实姓名', minWidth:50, align:"center"},
            {field: 'phone', title: '手机号', minWidth:100, align:'center'},
            {field: 'qq', title: 'QQ号', minWidth:100, align:'center'},
            {field: 'createTime', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateTime', title: '更新时间', align:'center',minWidth:150},
            {field: 'groupId', title: '小组', align:'center',minWidth:50},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("userListTable",{
            page:{
                curr:1
            },
            where:{
                userName:$("input[name='userName']").val(),// 用户名
                trueName:$("input[name='trueName']").val(),// 真实姓名
                phone:$("input[name='phone']").val()    //手机号
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(users)',function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateUserDialog();
                break;
            case "del":
                delUser(table.checkStatus(obj.config.id).data);
                break;
            case "fileInBtn":
                $('#fileIn').click();
        }
    });



    function delUser(datas){
        /**
         * 批量删除
         *   datas:选择的待删除记录数组
         */
        if(datas.length==0){
            layer.msg("请选择待删除记录!",{icon:5});
            return;
        }
        layer.confirm("确定删除选中的记录",{
            btn:['确定','取消']
        },function (index) {
            layer.close(index);
            var ids="ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].userId+"&ids=";
                }else{
                    ids=ids+datas[i].userId;
                }
            }
            $.ajax({
                type:"post",
                url:ctx+"/user/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        //提示成功
                        layer.msg("删除成功!",{icon:6});
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg,{icon:5});
                    }
                }
            })



        })
    }

    table.on('tool(users)',function (obj) {
        var layEvent =obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateUserDialog(obj.data.userId);
        }else if(layEvent === "del"){
            layer.confirm("确认删除当前记录?",{icon: 3, title: "用户管理"},function (index) {
                $.post(ctx+"/user/delete",{ids:obj.data.userId},function (data) {
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


    function openAddOrUpdateUserDialog(id) {
        var title="用户管理-用户添加";
        var url=ctx+"/user/addOrUpdateUserPage";
        if(id){
            title="用户管理-用户更新";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["520px","450px"],
            maxmin:true,
            content:url
        })
    }


//给input标签绑定change事件，一上传选中的.xls文件就会触发该函数
    $('#fileIn').change(function(e) {
        var files = e.target.files;
        var fileReader = new FileReader();
        fileReader.onload = function(ev) {
            try {
                var data = ev.target.result
                var workbook = XLSX.read(data, {
                    type: 'binary'
                }) // 以二进制流方式读取得到整份excel表格对象
                var persons = []; // 存储获取到的数据
            } catch (e) {
                layer.confirm("文件类型不正确！",{icon: 5, title: "批量导入"})
                console.log("文件类型不正确");
                return;
            }
            // 表格的表格范围，可用于判断表头是否数量是否正确
            var fromTo = '';
            // 遍历每张表读取
            for (var sheet in workbook.Sheets) {
                if (workbook.Sheets.hasOwnProperty(sheet)) {
                    fromTo = workbook.Sheets[sheet]['!ref'];
                    console.log(fromTo);
                    persons = persons.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
                    //break; // 如果只取第一张表，就取消注释这行
                }
            }
            //查询上传数据中是否存在相同学号
            checkUserName(persons);
        };
        // 以二进制方式打开文件
        fileReader.readAsBinaryString(files[0]);
    });

    //判断导入学号是否有重复
    function checkUserName(persons) {
        if(persons.length>0){
            var newArr=[];
            var newArr1=[];
            newArr1[0]=1;
            for(var i = 0; i < persons.length; i++) {
                newArr[i] = persons[i]["学号"];
            }
            for(var j=0;j<newArr.length;j++){
                for(var y=j+1;y<newArr.length+1;y++){
                    if(newArr[j]==newArr[y]){
                        newArr1[1]=newArr[j];
                    }
                }
            }
            if(newArr1.length > 1){
                layer.confirm("请检查Excel表学号，学号不允许重复！",{icon: 5, title: "批量导入"})
                return;
            }else if(newArr1.length==1){
                //调用检查数据库学号方法，查询Excel表中学号是否与数据库表学号重复
                checkUserNameInDatabase(persons);
            }
        }
        else{
            layer.confirm("请导入正确表格数据！",{icon: 5, title: "批量导入"})
            return;
        }
    }

    //根据学号查询数据库中是否有相同的数据
    function checkUserNameInDatabase(persons){
        //全局变量
        var personUserName=[];
        var i=0;
        for (var j=0;j<persons.length;j++){
            var k=0;
            //局部变量
            var person=[];
            person[k++] = persons[j]["学号"];
            personUserName[i++]=person;
        }
        //var flag = false;
        //var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:500,shade:0.8});
        var url = ctx+"/user/userCheckUserName";
        //for (var y=0;y<personUserName.length;y++){
        $.ajax({
            type: "post",
            traditional: true,
            url: url,
            data: {
                person1: personUserName//[y]
            },
            cache:true,
            dataType: "json",
            success: function(data){
                if(data.code==200){
                    layer.closeAll("iframe");
                    // 刷新父页面
                    //parent.location.reload();
                    addUserInDatabase(persons);
                }else{
                    console.log("返回错误！");
                    layer.msg(data.msg);
                    flag = true;
                }
            }
        });
        /*            if (flag == true){
                        break;
                    }*/
        // }
    };

    //遍历添加用户
    function addUserInDatabase(persons) {
        //全局变量
        var person1=[];
        var i=0;
        for (var j=0;j<persons.length;j++){
            var k=0;
            //局部变量
            var person=[];
            person[k++] = persons[j]["QQ号"];
            person[k++] = persons[j]["创建时间"];
            person[k++] = persons[j]["学号"];
            person[k++] = persons[j]["小组"];
            person[k++] = persons[j]["手机号"];
            person[k++] = persons[j]["更新时间"];
            person[k++] = persons[j]["真实姓名"];
            person[k++] = persons[j]["编号"];
            person1[i++]=person;
        }
        var flag = false;
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:500,shade:0.8});
        var url = ctx+"/user/userFileIn";
        for (var i=0;i<person1.length;i++){
            $.ajax({
                type: "post",
                traditional: true,
                url: url,
                data: {
                    person1: person1[i]
                },
                dataType: "json",
                success: function(data){
                    if(data.code==200){
                        checkUserNameInDatabase(persons);
                        top.layer.msg("操作成功");
                        top.layer.close(index);
                        layer.closeAll("iframe");
                        // 刷新父页面
                        parent.location.reload();
                    }else{
                        layer.msg(data.msg);
                        flag=true;
                    }
                }
            });
            if (flag==true){
                break;
            }
        }
        $("#area").val(JSON.stringify(persons));

    }
});
