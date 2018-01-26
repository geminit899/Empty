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
                        <a href="/" class="home-link">
                            <img alt="Logo" src="/img/png/logo.png"
                                 style="height: 30px;width: 70px;margin-right: 20px;margin-bottom: 10px;">
                            <span class="logo" style="margin-top: 8px;">WETIO</span>
                        </a>
                        <ul class="menu">
                            <li><a href="/recipes">Service Recipes</a></li>
                            <li class="has-dropdown"><a href="#">Docs</a>
                                <ul class="subnav" style="background: rgba(30, 30, 30, .9)">
                                    <li><a href="/">Getting Started Guide</a></li>
                                    <li><a href="/">User Guide</a></li>
                                    <li><a href="/">REST API</a></li>
                                </ul>
                            </li>
                            <li><a href="/">Pricing</a></li>
                            <li><a href="/">Blog</a></li>
                            <li><a href="/">Contact</a></li>
                        </ul>
                        <div class="text-right">
                            <ul class="social-links" style="margin-top: -5px;">
                                <li><a href="https://app.eventn.com/signup" target="_blank">Sign Up</a></li>
                                <li><a href="https://app.eventn.com/login" target="_blank">Login</a></li>
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
    </body>
</html>
