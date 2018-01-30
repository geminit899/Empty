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
                text-decoration:none;  /*鼠标放上去有下划线*/
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
                                    <a href="${music.url}" style="color: white;">${music.name} - ${music.author}</a>
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
                            <c:forEach var="novel" items="${novelsList}">
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
                    <h3 style="margin-top: 40px;margin-bottom: 40px">Contributions</h3>
                    ${contributions}
                </div>
            </div>
            <hr style="width:90%;height:2px;background-color:gray;"/>
            <div id="travel" style="margin-left: 100px;margin-right: 100px"></div>
            <hr style="width:90%;height:2px;background-color:gray;"/>
            <div style="height: 30px;"></div>
        </div>

        <script type="text/javascript">
            var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
            $("#travel").html("<div id='map' style='width: " + (width-200) + "px; height: 800px;'></div>");
        </script>

        <jsp:include page="util/footer.jsp"></jsp:include>

        <script src="/js/echarts.min.js"></script>
        <script src="/js/china.js"></script>
        <script src="/js/indexEcharts.js"></script>

        <script src="/js/autoPlaySwiper.js"></script>
    </body>
</html>
