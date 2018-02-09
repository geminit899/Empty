<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Date: 2018/1/21
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</title>
        <script src="/js/jquery-1.10.2.js"></script>
        <script src="/js/bootstrap.js"></script>
        <script src="/js/swiper.js"></script>
        <link rel="stylesheet" href="/css/bootstrap.css">
        <link rel="stylesheet" href="/css/swiper.css">
        <link rel="stylesheet" href="/css/fadeSwiper.css">
        <%-- Demo styles --%>
        <style>
            .fade {
                filter: alpha(Opacity=80);
                -moz-opacity: 0.8;
                opacity: 0.8;
            }
            #body {
                background-size: 100% 100%;
                background-repeat: no-repeat;
                background-color: #F5FFFA;
            }
            a:hover{
                text-decoration:none;  /*鼠标放上去没有下划线*/
            }
        </style>
    </head>
    <body>
        <div id="header" style="height: 800px; position: relative;">

            <jsp:include page="util/navigation.jsp"></jsp:include>

            <%-- Swiper --%>
            <div id="swiper" class="swiper-container" style="position: absolute;z-index: 1;">
                <div class="swiper-wrapper">
                    <%-- Swiper Content --%>
                    <c:forEach var="image" items="${imagesList}">
                        <div class="swiper-slide" style="position: relative;">
                            <a href="#" style="position:absolute;width: 100%;z-index: 1;">
                                <img src="${image.url}" style="height: 100%;width: 100%;"/>
                            </a>
                            <div style="position: absolute;z-index: 2;"></div>
                        </div>
                    </c:forEach>
                </div>
                <%-- Add Pagination --%>
                <div class="swiper-pagination swiper-pagination-white"></div>
                <%-- Add Arrows --%>
                <div class="swiper-button-next swiper-button-white"></div>
                <div class="swiper-button-prev swiper-button-white"></div>
            </div>
            <%-- Swiper End --%>
        </div>
        <%-- body的css设置在开头 --%>
        <div id="body">
            <div id="head" class="row">
                <div id="notice" class="col-md-4 col-sm-4 col-xs-4">
                    <div class="fade" style="margin-left: 50px;">
                        <div style="height: 80px;">
                            <div class="row">
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <img src="/img/png/pink.png"
                                         style="height: 100%;padding-top: 20px;padding-bottom: 20px;" />
                                </div>
                                <div class="col-md-9 col-sm-9 col-xs-9" style="padding-left: 50px;">
                                    <h2 class="fade" style="color: pink;padding-top:5px;">Notice</h2>
                                </div>
                            </div>
                        </div>
                        <div style="height: 350px;background-color: pink;padding: 10px 30px 10px 30px;">
                            <c:forEach var="notice" items="${notices}">
                                <h5>
                                    <a href="${notice.url}" style="color: white;">${notice.title}</a>
                                </h5>
                                <hr style="margin-top: 10px;margin-bottom: 10px;" color="gray" />
                            </c:forEach>
                            <a href="http://www.cdut.edu.cn/xww/type/1000020104.html">
                                <h5 style="text-align: right;color: white;">More...</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div id="music" class="col-md-4 col-sm-4 col-xs-4">
                    <div class="fade" style="margin-left: 25px;margin-right: 25px;">
                        <div style="height: 80px;">
                            <div class="row">
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <img src="/img/png/brown.png"
                                         style="height: 100%;padding-top: 20px;padding-bottom: 20px;" />
                                </div>
                                <div class="col-md-9 col-sm-9 col-xs-9" style="padding-left: 50px;">
                                    <h2 class="fade" style="color: #786F3B;padding-top: 5px;">Music</h2>
                                </div>
                            </div>
                        </div>
                        <div style="height: 350px;background-color: #786F3B;padding: 10px 30px 10px 30px;">
                            <c:forEach var="music" items="${musics}">
                                <h5>
                                    <a href="${music.url}" style="color: white;">${music.name} - ${music.singer}</a>
                                </h5>
                                <hr style="margin-top: 10px;margin-bottom: 10px;" color="gray" />
                            </c:forEach>
                            <a href="http://music.163.com/user/songs/rank?id=258625371">
                                <h5 style="text-align: right;color: white;">More...</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div id="novel" class="col-md-4 col-sm-4 col-xs-4">
                    <div class="fade" style="margin-right: 50px;">
                        <div style="height: 80px;">
                            <div class="row">
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <img src="/img/png/skyblue.png"
                                         style="height: 100%;padding-top: 20px;padding-bottom: 20px;" />
                                </div>
                                <div class="col-md-9 col-sm-9 col-xs-9" style="padding-left: 50px;">
                                    <h2 class="fade" style="color: deepskyblue;padding-top: 5px;">Novel</h2>
                                </div>
                            </div>
                        </div>
                        <div style="height: 350px;background-color: deepskyblue;padding: 10px 30px 10px 30px;">
                            <c:forEach var="novel" items="${novels}">
                                <h5>
                                    <a href="${novel.url}" style="color: white;">
                                            ${novel.name}  :  ${novel.latestChapter}
                                    </a>
                                </h5>
                                <hr style="margin-top: 10px;margin-bottom: 10px;" color="gray" />
                            </c:forEach>
                            <a href="http://www.biquge.com.tw/">
                                <h5 style="text-align: right;color: white;">More...</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 30px;"></div>
            <hr style="width:90%;height:2px;background-color:gray;"/>
            <div id="github" style="height: 300px;margin-left: 50px;margin-right: 50px" class="row">
                <div class="col-md-3 col-sm-3 col-xs-3" align="right">
                    <img src="${githubImageURL}" height="230" width="230" style="margin-top: 30px">
                </div>
                <div class="col-md-9 col-sm-9 col-xs-9" align="center">
                    <h3 style="margin-top: 40px;margin-bottom: 40px; color: gray">Contributions</h3>
                    ${contributions}
                </div>
            </div>
            <hr style="width:90%;height:2px;background-color:gray;"/>
            <div id="travel" style="margin-left: 100px;margin-right: 100px"></div>
            <script type="text/javascript">
                var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                $("#travel").html("<div id='map' style='width: " + (width-200) + "px; height: 800px;'></div>");
            </script>
            <div style="height: 30px;"></div>
        </div>



        <jsp:include page="util/footer.jsp"></jsp:include>

        <script src="/js/echarts.min.js"></script>
        <script src="/js/china.js"></script>

        <script type="text/javascript">
            var myChart = echarts.init(document.getElementById('map'));
            var geoCoordMap = eval(${geoJsonObject});
            var NCData = [];
            var SHData = [];
            var CDData = [];

            for (var i =0; i<eval(${ncArray.size()}); i++){
                NCData.push([{'name':${ncArray}[i].from}, {'name':${ncArray}[i].to, 'value': 90}]);
            }

            for (var i =0; i<eval(${shArray.size()}); i++){
                SHData.push([{'name':${shArray}[i].from}, {'name':${shArray}[i].to, 'value': 90}]);
            }

            for (var i =0; i<eval(${cdArray.size()}); i++){
                CDData.push([{'name':${cdArray}[i].from}, {'name':${cdArray}[i].to, 'value': 90}]);
            }

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
        </script>

        <script src="/js/autoPlaySwiper.js"></script>
    </body>
</html>
