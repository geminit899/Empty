<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/1/22
  Time: 上午12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Navigation</title>
        <link rel="stylesheet" href="/css/nav.css">
    </head>
    <body>
        <nav class="nav-1" style="height: 50px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <a href="/wetio/index" class="home-link">
                            <img alt="Logo" src="/img/png/logo.png"
                                 style="height: 30px;width: 70px;margin-right: 20px;margin-bottom: 10px;">
                            <span class="logo" style="margin-top: 8px;">WETIO</span>
                        </a>
                        <ul class="menu">
                            <li><a href="/">学习</a></li>
                            <li class="has-dropdown"><a href="#">娱乐</a>
                                <ul class="subnav" style="background: rgba(30, 30, 30, .9)">
                                    <li><a href="/">电影</a></li>
                                    <li><a href="/">音乐</a></li>
                                    <li><a href="/">游戏</a></li>
                                </ul>
                            </li>
                            <li><a href="/">社交</a></li>
                            <li><a href="/">其他</a></li>
                        </ul>
                        <div class="text-right">
                            <ul class="social-links" style="margin-top: -5px;">
                                <li><a data-toggle="modal" data-target="#Login">Login</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mobile-toggle">
                <div class="bar-1"></div>
                <div class="bar-2"></div>
            </div>
        </nav>

        <div class="modal fade" id="Login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">来啊，快活啊。</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-5 col-sm-5 col-xs-5" align="right" style="margin-top: 10px">账号：</div>
                            <div class="col-md-7 col-sm-7 col-xs-7" align="left" style="margin-top: 7px">
                                <input id="username">
                            </div>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-md-5 col-sm-5 col-xs-5" align="right" style="margin-top: 10px">密码：</div>
                            <div class="col-md-7 col-sm-7 col-xs-7" align="left" style="margin-top: 7px">
                                <input id="password" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="login()">登录</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">错误</h4>
                    </div>
                    <div class="modal-body">用户名或密码错误</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <script type="text/javascript">
            function login(){
                var str = {};
                str["username"] = $("#username").val();
                str["password"] = $("#password").val();

                $.ajax({                    //获得各个区域的值
                    type:"post",
                    async: false, //同步执行
                    url:"/wetio/index/login",
                    data:str,
                    success:function(result){
                        if(result == "error"){
                            $("#password").val("");
                            $('#error').modal('show');
                        }else
                            $('#Login').modal('hide');
                    }
                });
            }
        </script>
    </body>
</html>
