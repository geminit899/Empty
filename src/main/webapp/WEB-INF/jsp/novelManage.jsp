<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: geminit
  Date: 2018/2/4
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小说管理</title>
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
                <label>书名: </label>
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
                    <td class="success" width="10%"><label>书名</label></td>
                    <td class="success" width="10%"><label>作者</label></td>
                    <td class="success" width="45%"><label>最新章节</label></td>
                    <td class="success" width="10%"><label>状态</label></td>
                    <td class="success" width="25%"><label>操作</label></td>
                </tr>
                <c:forEach items="${novelList}" var="novel" varStatus="status">
                    <tr class="table-bordered" align="center">
                        <td>${novel.name}</td>
                        <td>${novel.author}</td>
                        <td>${novel.latestChapter}</td>
                        <c:if test="${novel.isFinished==0}"><td>未完结</td></c:if>
                        <c:if test="${novel.isFinished==1}"><td>已完结</td></c:if>
                        <td>
                            <button class="btn btn-warning">编辑</button>
                            <button class="btn btn-danger">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
    </div>
    <%--页数文本--%>
    <div class="row">
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
        <div class="col-md-10 col-sm-10 col-xs-10" align="center">
            <ul class="pagination">
                <li><a href="#">&lt;&lt;</a></li>
                <li><a href="#">&lt;</a></li>
                <c:if test="${pageNum<7}">
                    <li class="active"><a href="#">1</a></li>
                    <c:forEach begin="2" end="${pageNum}" var="page">
                        <li><a href="#">${page}</a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${pageNum>7}">
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a class="disabled" href="#">...</a></li>
                    <li><a href="#">${pageNum}</a></li>
                </c:if>
                <li><a href="#">&gt;</a></li>
                <li><a href="#">&gt;&gt;</a></li>
            </ul>
        </div>
        <div class="col-md-1 col-sm-1 col-xs-1"></div>
    </div>
    <%--新建用户模态框--%>
    <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增小说</h4>
                </div>
                <div class="modal-body">
                    <div class="form-inline">
                        <div class="form-group">
                            <label>书名:  </label>
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
                    <h4 class="modal-title" id="myModalLabelEdit">区域小说</h4>
                </div>
                <div class="modal-body">
                    <div class="form-inline">
                        <div class="form-group">
                            <label>书名:  </label>
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
