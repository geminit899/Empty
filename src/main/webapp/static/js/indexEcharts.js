var myChart = echarts.init(document.getElementById('map'));
var geoCoordMap;

$.ajax({                    //获得所有标记的值
    type:"post",
    async: false, //同步执行
    url:"/wetio/index/echarts",
    data:[],
    success:function(result){
        str = eval(result);
        for (var i = 0; i < str.length; i++) {
            if(str[i].type=="library")
                library_data.push({ "name": str[i].name, "value": str[i].value || 0 });
            else if(str[i].type=="cinema")
                cinema_data.push({ "name": str[i].name, "value": str[i].value || 0 });
            else if(str[i].type=="culture")
                culture_data.push({ "name": str[i].name, "value": str[i].value || 0 });

        }
    }
});

var CDData = [
    [{name:'成都'}, {name:'上海',value:95}],
    [{name:'成都'}, {name:'南昌',value:95}],
    [{name:'成都'}, {name:'峨眉山',value:90}],
    [{name:'成都'}, {name:'重庆',value:80}],
    [{name:'成都'}, {name:'西安',value:70}],
    [{name:'成都'}, {name:'北京',value:60}],
    [{name:'北京'}, {name:'郑州',value:50}],
    [{name:'郑州'}, {name:'上海',value:40}],
    [{name:'成都'}, {name:'武汉',value:30}],
    [{name:'武汉'}, {name:'南昌',value:20}]
];

var NCData = [
    [{name:'南昌'},{name:'上海',value:95}],
    [{name:'南昌'},{name:'厦门',value:90}],
    [{name:'南昌'},{name:'赣州',value:80}],
    [{name:'南昌'},{name:'昆明',value:80}],
    [{name:'昆明'},{name:'大理',value:80}],
    [{name:'大理'},{name:'丽江',value:80}],
    [{name:'丽江'},{name:'香格里拉',value:80}],
    [{name:'南昌'},{name:'长沙',value:80}],
    [{name:'南昌'},{name:'成都',value:70}]
];

var SHData = [
    [{name:'上海'},{name:'南昌',value:95}],
    [{name:'上海'},{name:'杭州',value:60}],
    [{name:'上海'},{name:'成都',value:90}],
    [{name:'上海'},{name:'台州',value:80}],
    [{name:'上海'},{name:'南京',value:70}],
    [{name:'上海'},{name:'香港',value:70}],
    [{name:'香港'},{name:'成都',value:70}]
];

var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];
        var fromCoord = geoCoordMap[dataItem[0].name];
        var toCoord = geoCoordMap[dataItem[1].name];
        if (fromCoord && toCoord) {
            res.push({
                fromName: dataItem[0].name,
                toName: dataItem[1].name,
                coords: [fromCoord, toCoord]
            });
        }
    }
    return res;
};

var color = ['#a6c84c', '#ffa022', '#46bee9'];
var series = [];
[['成都', CDData], ['南昌', NCData], ['上海', SHData]].forEach(function (item, i) {
    series.push({
            name:  'From ' + item[0],
            type: 'lines',
            zlevel: 1,
            effect: {
                show: true,
                period: 6,
                trailLength: 0.7,
                color: '#fff',
                symbolSize: 3
            },
            lineStyle: {
                normal: {
                    color: color[i],
                    width: 0,
                    curveness: 0.2
                }
            },
            data: convertData(item[1])
        },
        {
            name: 'From ' + item[0],
            type: 'lines',
            zlevel: 2,
            symbol: ['none', 'arrow'],
            symbolSize: 10,
            effect: {
                show: true,
                period: 6,
                trailLength: 0,
                symbol: planePath,
                symbolSize: 15
            },
            lineStyle: {
                normal: {
                    color: color[i],
                    width: 1,
                    opacity: 0.6,
                    curveness: 0.2
                }
            },
            data: convertData(item[1])
        },
        {
            name: 'From ' + item[0],
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
                brushType: 'stroke'
            },
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    formatter: '{b}'
                }
            },
            symbolSize: 3,
            itemStyle: {
                normal: {
                    color: color[i]
                }
            },
            tooltip :{
                show: false,
            },
            data: item[1].map(function (dataItem) {
                return {
                    name: dataItem[1].name,
                    value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                };
            })
        });
});

option = {
    title : {
        text: '我来到，你的城市。',
        subtext: 'Traveling',
        left: 'center',
        textStyle : {
            color: 'gray'
        }
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        top: 'bottom',
        left: 'left',
        data:['From 成都', 'From 南昌', 'From 上海'],
        textStyle: {
            color: 'gray'
        },
    },
    geo: {
        map: 'china',
        label: {
            emphasis: {
                show: true,
                textStyle: {
                    color: 'rgba(0,0,0,0.4)'
                }
            }
        },
        itemStyle: {
            normal: {
                areaColor: 'lightgray',
                borderColor: '#fff'
            },
            emphasis: {
                areaColor: 'gray'
            }
        }
    },
    series: series
};
myChart.setOption(option);