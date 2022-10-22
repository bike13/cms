
layui.use('laydate', function(){
    var laydate = layui.laydate;
    laydate.render({
        elem: '#devResult',
        type: 'date'
    });
});

$(function () {
    loadModuleInfo();
});
function  loadModuleInfo(groupId){
    /*var myselect=document.getElementById("devResult");
    var index=myselect.selectedIndex;
    var updateTime=myselect.options[index].value;*/
    var updateTime=$("input[name='devResult']").val();
    console.log(updateTime);
    getTable(groupId,updateTime);
    $.ajax({
        type:"post",
        url:ctx+"/dataAnalysis/select",
        dataType:"json",
        data:{
            groupId:groupId,
            updateTime:updateTime
        },
        success:function (obj) {
            if (obj!=null){
                var name = obj.result[0];
                var vedioSpeedEnd = obj.result[1];
                var codeSpeedEnd = obj.result[2];
                var note =obj.result[3];
                var sign=obj.result[4];
                var groupId=obj.groupId;
                // 加载柱状图
                loadMonthChart(name, vedioSpeedEnd,codeSpeedEnd,note,groupId,sign);
            }
        }
    });
}

function loadMonthChart(name, vedioSpeedEnd,codeSpeedEnd,note,groupId,sign) {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var text;
    var cent;
    if (groupId==0){
        text='班级情况';
        cent='平均';
    }else {
        text=groupId+'组情况智能分析';
        cent='';
    }
    console.log(text);
    var option = {
        title: {
            text:text
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {},
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: { readOnly: false },
                magicType: { type: ['line', 'bar'] },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: name
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: [
            {
                name: cent+'视频进度',
                type: 'line',
                data: vedioSpeedEnd,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            },
            {
                name: cent+'代码进度',
                type: 'line',
                data: codeSpeedEnd,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            },
            {
                name: cent+'签到情况',
                type: 'line',
                data: sign,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            },
            {
                name: cent+'笔记完成情况',
                type: 'line',
                data: note,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            }
        ]
    };
    option && myChart.setOption(option);
}
function getTable(groupId,updateTime) {
    var t1;
    if (groupId==0){
        t1="班级问题情况";
    }else {
        t1="小组问题情况";
    }
        $.ajax({
            type: "post",//用POST传递数据，GET也可以
            url:ctx+"/dataAnalysis/select",//我用的是MVC，这样可以直接触发对应的Controle
            data: {
                groupId:groupId,
                updateTime:updateTime
            },
            dataType: "json",//定义的是返回数据的格式，直接写json就对了，写text的话还要用JSON.pare()再转换一次
            success: function (data) {
                if (data == null || data === '') {
                    alert("请联系\n管理员维护数据");
                } else {
                    var str = "";//把数据组装起来
                        str += "<tr><td>" +"日期"+
                            "</td><td>" + updateTime +
                            "<tr><td>" +"视频进度平均值"+
                            "</td><td>" + data.speedVedioAverage +
                            "<tr><td>" +"代码进度平均值" +
                            "</td><td>" + data.speedCodeAverage +
                            "<tr><td>" +"视频进度分析" +
                            "</td><td>" + data.speedVedioMin +
                            "<tr><td>" +"代码进度分析" +
                            "</td><td>" + data.speedCodeMin+
                            "<tr><td>" +"笔记完成情况分析" +
                            "</td><td>" + data.noteMin +
                            "<tr><td>" +"签到情况分析" +
                            "</td><td>" + data.signMin +
                            "<tr><td>" +t1 +
                            "</td><td>" +data.question + "</td></tr>";
                    $("#tdBd").html(str);//把拼好的样式填到指定的位置，一个Ajax的表格刷新功能就完成了
                }
            }
        });
}
