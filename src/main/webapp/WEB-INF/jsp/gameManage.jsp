<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/2/4
  Time: 上午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-1.10.2.js"></script>
    <script src="/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/personalIndex/_all-skins.min.css">
    <link rel="stylesheet" href="/css/personalIndex/AdminLTE.css">
    <link rel="stylesheet" href="/css/personalIndex/font-awesome.min.css">
</head>
<body style="background-color: #f6f6f6">

<jsp:include page="util/navigation.jsp"></jsp:include>

<jsp:include page="util/personalIndexLeftSide.jsp"></jsp:include>

<div class="content-wrapper">
    <div class="row" style="padding-top: 70px">
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
        <%--搜索框--%>
        <div class="col-md-8 col-sm-8 col-xs-8 form-inline">
            <div class="form-group">
                <label>区域: </label>
                <input type="text" class="form-control" id="name">
            </div>
            <button class="btn btn-primary" id="search-btn">搜索</button>
        </div>
        <div class="col-md-2 col-sm-2 col-xs-2" align="center">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newModal">新增</button>
        </div>
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
    </div>
    <div class="row" style="margin-top: 20px">
        <%--表格--%>
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
        <div class="col-md-10 col-sm-10 col-xs-10">
            <table class="table table-bordered" border="1" id="table-user">
                <tr align="center">
                    <td class="success" width="10%"><label>编号</label></td>
                    <td class="success" width="10%"></td>
                    <td class="success" width="50%"><label>区域</label></td>
                    <td class="success" width="30%"><label>操作</label></td>
                </tr>
                <%--<c:forEach items="${user}" var="key" varStatus="status">--%>
                <tr class="table-bordered" align="center">
                    <td>22</td>
                    <td><input type="checkbox" name="checkbox" value="24"></td>
                    <td>sdfghj</td>
                    <td>
                        <button class="btn btn-warning" onclick="getCheckBoxEdit()">编辑</button>
                        <button class="btn btn-danger" onclick="getCheckBoxDelete()">删除</button>
                    </td>
                </tr>
                <%--</c:forEach>--%>
            </table>
        </div>
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
    </div>
    <%--页数文本--%>
    <div class="row">
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
        <div class="col-md-8 col-sm-8 col-xs-8">
            <a>总1条&nbsp;&nbsp;&nbsp;每页10条&nbsp;&nbsp;&nbsp;共1页</a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-3">
            <nav>
                <ul class="pager" id="page-controller">
                    <%--<c:if test="${previous<1}">--%>
                    <%--<li class="disabled" id="previous"><a href="#">上一页</a></li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${previous>=1}">--%>
                    <li><a href="/region/index?page=1">上一页</a></li>
                    <%--</c:if>--%>
                    <%--<c:if test="${last > page}">--%>
                    <li class="disabled" id="last"><a href="#">下一页</a></li>
                    <%--</c:if>--%>
                    <%--<c:if test="${last <= page}">--%>
                    <%--<li><a href="/region/index?page=${last}">下一页</a></li>--%>
                    <%--</c:if>--%>
                </ul>
            </nav>
        </div>
    </div>
    <%--新建用户模态框--%>
    <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增区域</h4>
                </div>
                <div class="modal-body">
                    <div class="form-inline">
                        <div class="form-group">
                            <label>区域:  </label>
                            <input type="text" id="newname">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="newbutton">新建</button>
                </div>
            </div>
        </div>
    </div>

    <%--编辑用户模态框--%>
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabelEdit">区域编辑</h4>
                </div>
                <div class="modal-body">
                    <div class="form-inline">
                        <div class="form-group">
                            <label>区域:  </label>
                            <input type="hidden" id="editid">
                            <input type="text" id="editname">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="editlbutton">更新</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
