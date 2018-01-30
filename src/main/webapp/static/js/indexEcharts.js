var myChart = echarts.init(document.getElementById('map'));
var geoCoordMap = {
    '上海': [121.4648,31.2891],
    '乌鲁木齐': [87.9236,43.5883],
    '北京': [116.4551,40.2539],
    '北海': [109.314,21.6211],
    '南京': [118.8062,31.9208],
    '南宁': [108.479,23.1152],
    '南昌': [116.0046,28.6633],
    '南通': [121.1023,32.1625],
    '厦门': [118.1689,24.6478],
    '台州': [121.1353,28.6688],
    '峨眉山': [103.48,29.60],
    '合肥': [117.29,32.0581],
    '赣州': [114.93 ,25.83],
    '呼和浩特': [111.4124,40.4901],
    '香格里拉': [99.70,27.83],
    '丽江': [100.23,26.88],
    '大理': [100.13,25.34],
    '咸阳': [108.4131,34.8706],
    '哈尔滨': [127.9688,45.368],
    '天津': [117.4219,39.4189],
    '太原': [112.3352,37.9413],
    '威海': [121.9482,37.1393],
    '广州': [113.5107,23.2196],
    '廊坊': [116.521,39.0509],
    '延安': [109.1052,36.4252],
    '成都': [103.9526,30.7617],
    '拉萨': [91.1865,30.1465],
    '无锡': [120.3442,31.5527],
    '昆明': [102.9199,25.4663],
    '杭州': [119.5313,29.8773],
    '武汉': [114.3896,30.6628],
    '沈阳': [123.1238,42.1216],
    '济南': [117.1582,36.8701],
    '海口': [110.3893,19.8516],
    '深圳': [114.5435,22.5439],
    '珠海': [113.7305,22.1155],
    '西安': [109.1162,34.2004],
    '贵阳': [106.6992,26.7682],
    '郑州': [113.4668,34.6234],
    '重庆': [107.7539,30.1904],
    '香港': [114.08,22.20],
    '长沙': [113.0823,28.2568]
};

var CDData = [
    [{name:'成都'}, {name:'上海',value:95}],
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
                show: false
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