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
    <title>音乐管理</title>
    <script src="/js/jquery-3.3.1.min.js"></script>
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
                    <label>歌名: </label>
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
                <table class="table table-bordered" border="1" id="musicTable">
                    <tr align="center">
                        <td class="success" width="20%"><label>歌名</label></td>
                        <td class="success" width="25%"><label>歌手</label></td>
                        <td class="success" width="30%"><label>专辑</label></td>
                        <td class="success" width="25%"><label>操作</label></td>
                    </tr>
                    <c:forEach items="${musicList}" var="music" varStatus="status">
                        <tr class="table-bordered" align="center">
                            <td>${music.name}</td>
                            <td>${music.singer}</td>
                            <td>${music.album}</td>
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
                <ul id="page" class="pagination">
                    <%--前一页按钮--%>
                    <c:if test="${currentPage==1}">
                        <li><a class="disabled">&lt;</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li><a href="/wetio/manage/music?toPage=${currentPage-1}">&lt;</a></li>
                    </c:if>
                    <%--页码按钮--%>
                    <c:if test="${pageNum<=7}">
                        <c:forEach begin="1" end="${pageNum}" var="page">
                            <c:if test="${currentPage==page}">
                                <li class="active"><a>${page}</a></li>
                            </c:if>
                            <c:if test="${currentPage!=page}">
                                <li><a href="/wetio/manage/music?toPage=${page}">${page}</a></li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageNum>7}">
                        <c:if test="${currentPage<=4}">
                            <c:forEach begin="1" end="5" var="page">
                                <c:if test="${currentPage==page}">
                                    <li class="active"><a>${page}</a></li>
                                </c:if>
                                <c:if test="${currentPage!=page}">
                                    <li><a href="/wetio/manage/music?toPage=${page}">${page}</a></li>
                                </c:if>
                            </c:forEach>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/music?toPage=${pageNum}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${currentPage>4 && currentPage<(pageNum-3)}">
                            <li><a href="/wetio/manage/music?toPage=1">1</a></li>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/music?toPage=${currentPage-1}">${currentPage-1}</a></li>
                            <li class="active"><a>${currentPage}</a></li>
                            <li><a href="/wetio/manage/music?toPage=${currentPage+1}">${currentPage+1}</a></li>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/music?toPage=${pageNum}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${currentPage>=(pageNum-3)}">
                            <li><a href="/wetio/manage/music?toPage=1">1</a></li>
                            <li class="disabled"><a>...</a></li>
                            <c:forEach begin="${pageNum-4}" end="${pageNum}" var="page">
                                <c:if test="${currentPage==page}">
                                    <li class="active"><a>${page}</a></li>
                                </c:if>
                                <c:if test="${currentPage!=page}">
                                    <li><a href="/wetio/manage/music?toPage=${page}">${page}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <%--页码按钮--%>
                    <c:if test="${currentPage==pageNum}">
                        <li class="disabled"><a>&gt;</a></li>
                    </c:if>
                    <c:if test="${currentPage!=pageNum}">
                        <li><a href="/wetio/manage/music?toPage=${currentPage+1}">&gt;</a></li>
                    </c:if>
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
                        <h4 class="modal-title" id="myModalLabel">新增歌曲</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                            <div class="form-group">
                                <label>歌名:  </label>
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
                        <h4 class="modal-title" id="myModalLabelEdit">歌曲</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                            <div class="form-group">
                                <label>歌名:  </label>
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
