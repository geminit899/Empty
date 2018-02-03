<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/2/3
  Time: 下午10:11
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

    <div class="content-wrapper row" style="padding-top: 70px">
        <div class="col-md-12" id="search-box">
            <%--搜索框--%>
            <div class="form-inline" id="input-box">
                <div class="form-group">
                    <label>区域: </label>
                    <input type="text" class="form-control" id="name">
                </div>
                <button class="btn btn-primary" id="search-btn">搜索</button>
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#newModal">新增</button>
            </div>
            <%--按钮--%>
            <div style="margin-top: 20px">
                <div class="col-md-4" id="button-ned">
                    <div class="col-md-2">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#newModal">新增</button>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-warning" onclick="getCheckBoxEdit()">编辑</button>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-danger" onclick="getCheckBoxDelete()">删除</button>
                    </div>
                </div>
            </div>
            <%--表格--%>
            <div class="col-md-11" id="table-box" style="margin-top: 20px">
                <table class="table table-bordered" border="1" id="table-user">
                    <tr>
                        <td class="success"><label>编号</label></td>
                        <td class="success"></td>
                        <td class="success"><label>区域</label></td>
                        <td class="success"><label>操作</label></td>
                    </tr>
                    <%--<c:forEach items="${user}" var="key" varStatus="status">--%>
                        <tr class="table-bordered">
                            <td>22</td>
                            <td><input type="checkbox" name="checkbox" value="24"></td>
                            <td>sdfghj</td>
                            <td>
                                <button class="btn btn-warning" onclick="getCheckBoxEdit()">编辑</button>
                                <button class="btn btn-danger" onclick="getCheckBoxDelete()">删除</button>
                            </td>
                        </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                        <td>
                            <button class="btn btn-warning" onclick="getCheckBoxEdit()">编辑</button>
                            <button class="btn btn-danger" onclick="getCheckBoxDelete()">删除</button>
                        </td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                        <td>
                            <button class="btn btn-warning" onclick="getCheckBoxEdit()">编辑</button>
                            <button class="btn btn-danger" onclick="getCheckBoxDelete()">删除</button>
                        </td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <tr class="table-bordered">
                        <td>22</td>
                        <td><input type="checkbox" name="checkbox" value="24"></td>
                        <td>sdfghj</td>
                    </tr>
                    <%--</c:forEach>--%>
                </table>
            </div>
            <%--页数文本--%>
            <div id="page-text">
                <div class="col-md-12">
                    <div class="col-md-2" id="page-text-div">
                        <a>总1条&nbsp;&nbsp;&nbsp;每页10条&nbsp;&nbsp;&nbsp;共1页</a>
                    </div>
                    <div class="col-md-6"></div>
                    <div class="col-md-4" id="page-button">
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
    </div>
</body>
</html>
