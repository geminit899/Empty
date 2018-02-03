<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/2/2
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header" style="color: gray; background-color: #737373; height: 50px;">
                <h3 style="color: white; margin-top: 0px;" align="center">导航栏</h3>
            </li>
            <li class="active treeview menu-open">
                <a onclick="natigator()" style="background-color: rgba(30, 30, 30, .4)">
                    <i class="fa fa-dashboard"></i>
                    <span>个人信息</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/manage/index"><i class="fa fa-circle-o"></i>个人信息</a></li>
                    <li><a href="/manage/index"><i class="fa fa-circle-o"></i>主页照片</a></li>
                </ul>
            </li>
            <li class="active treeview menu-open">
                <a onclick="natigator()" style="background-color: rgba(30, 30, 30, .4)">
                    <i class="fa fa-dashboard"></i>
                    <span>学习管理</span>
                    <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>用户管理</a></li>
                </ul>
            </li>
            <li class="active treeview menu-open">
                <a onclick="natigator()" style="background-color: rgba(30, 30, 30, .4)">
                    <i class="fa fa-dashboard"></i>
                    <span>娱乐管理</span>
                    <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>电影管理</a></li>
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>音乐管理</a></li>
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>游戏管理</a></li>
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>小说管理</a></li>
                </ul>
            </li>
            <li class="active treeview menu-open">
                <a onclick="natigator()" style="background-color: rgba(30, 30, 30, .4)">
                    <i class="fa fa-dashboard"></i>
                    <span>社交管理</span>
                    <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>用户管理</a></li>
                </ul>
            </li>
            <li class="active treeview menu-open">
                <a onclick="natigator()" style="background-color: rgba(30, 30, 30, .4)">
                    <i class="fa fa-dashboard"></i>
                    <span>其他管理</span>
                    <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/user/index?page=1"><i class="fa fa-circle-o"></i>用户管理</a></li>
                </ul>
            </li>
        </ul>
    </section>
</aside>

<script type="text/javascript">

</script>
