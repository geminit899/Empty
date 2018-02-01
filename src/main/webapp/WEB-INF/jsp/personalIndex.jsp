<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/2/1
  Time: 下午6:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Geminit</title>
    <script src="/js/jquery-1.10.2.js"></script>
    <script src="/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <style>
        #tatooDiv {
            position: absolute;
            margin-top: 60px;
            margin-left: 30px;
            height: 180px;
            width: 180px;
            background-color: white;
            border-radius:25px;
            -moz-border-radius:25px; /* Old Firefox */
        }
        #tatoo {
            height: 100%;
            width: 100%;
            padding: 5px 5px 5px 5px;
            border-radius:25px;
            -moz-border-radius:25px; /* Old Firefox */
        }
        .radius {
            border-radius:5px;
            -moz-border-radius:5px; /* Old Firefox */
        }
    </style>
</head>
<body style="background-color: #f6f6f6">

    <jsp:include page="util/navigation.jsp"></jsp:include>

    <div class="row" style="margin-top: 60px">
        <div class="col-md-2 col-sm-2 col-xs-2"></div>
        <div id="body" class="col-md-8 col-sm-8 col-xs-8" style="padding-left: 0px; padding-right: 0px">
            <div class="radius" style="height: 130px;">
                <div class="radius" style="position: absolute; height: 130px; overflow: hidden;">
                    <img class="radius" src="/img/bg.jpg" style="width: 100%">
                </div>
                <div id="tatooDiv"><img id="tatoo" src="/img/me.jpg"></div>
            </div>
            <div class="radius" style="height: 130px;background-color: white;">
                <div style="margin-left: 250px">Geminit</div>
            </div>
        </div>
        <div class="col-md-2 col-sm-2 col-xs-2"></div>
    </div>

</body>
</html>
