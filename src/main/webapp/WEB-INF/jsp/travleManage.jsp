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
    <title>旅游管理</title>
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
                    <label>城市: </label>
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
                <table class="table table-bordered" border="1" id="travleTable">
                    <tr align="center">
                        <td class="success" width="7%"><label>id</label></td>
                        <td class="success" width="20%"><label>同行</label></td>
                        <td class="success" width="30%"><label>旅程</label></td>
                        <td class="success" width="18%"><label>时间</label></td>
                        <td class="success" width="25%"><label>操作</label></td>
                    </tr>
                    <c:forEach items="${travleList}" var="travle" varStatus="status">
                        <tr class="table-bordered" align="center">
                            <td>${travle.id}</td>
                            <td>${travle.company}</td>
                            <td>${travle.way}</td>
                            <td>${travle.beginTime}</td>
                            <td>
                                <button class="btn btn-warning" onclick="edit(${travle.id})">编辑</button>
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
                        <li><a href="/wetio/manage/travle?toPage=${currentPage-1}">&lt;</a></li>
                    </c:if>
                    <%--页码按钮--%>
                    <c:if test="${pageNum<=7}">
                        <c:forEach begin="1" end="${pageNum}" var="page">
                            <c:if test="${currentPage==page}">
                                <li class="active"><a>${page}</a></li>
                            </c:if>
                            <c:if test="${currentPage!=page}">
                                <li><a href="/wetio/manage/travle?toPage=${page}">${page}</a></li>
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
                                    <li><a href="/wetio/manage/travle?toPage=${page}">${page}</a></li>
                                </c:if>
                            </c:forEach>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/travle?toPage=${pageNum}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${currentPage>4 && currentPage<(pageNum-3)}">
                            <li><a href="/wetio/manage/travle?toPage=1">1</a></li>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/music?toPage=${currentPage-1}">${currentPage-1}</a></li>
                            <li class="active"><a>${currentPage}</a></li>
                            <li><a href="/wetio/manage/travle?toPage=${currentPage+1}">${currentPage+1}</a></li>
                            <li class="disabled"><a>...</a></li>
                            <li><a href="/wetio/manage/travle?toPage=${pageNum}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${currentPage>=(pageNum-3)}">
                            <li><a href="/wetio/manage/travle?toPage=1">1</a></li>
                            <li class="disabled"><a>...</a></li>
                            <c:forEach begin="${pageNum-4}" end="${pageNum}" var="page">
                                <c:if test="${currentPage==page}">
                                    <li class="active"><a>${page}</a></li>
                                </c:if>
                                <c:if test="${currentPage!=page}">
                                    <li><a href="/wetio/manage/travle?toPage=${page}">${page}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <%--页码按钮--%>
                    <c:if test="${currentPage==pageNum}">
                        <li class="disabled"><a>&gt;</a></li>
                    </c:if>
                    <c:if test="${currentPage!=pageNum}">
                        <li><a href="/wetio/manage/travle?toPage=${currentPage+1}">&gt;</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="col-md-1 col-sm-1 col-xs-1"></div>
        </div>
    </div>

    <%--新建用户模态框--%>
    <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabelAdd">新增旅程</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>同行:</label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="companyAdd">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>目的地:  </label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="destinationAdd">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>时间:  </label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="beginTimeAdd">
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
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabelEdit">旅程</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>同行:</label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="companyEdit">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>旅途:  </label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="destinationEdit">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-5 col-xs-5" align="right">
                            <label>时间:  </label>
                        </div>
                        <div class="col-md-7 col-sm-7 col-xs-7" align="left">
                            &nbsp;<input type="text" id="beginTimeEdit">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="editlbutton">更新</button>
                </div>
            </div>
        </div>
    </div>

    <%--成功模态框--%>
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabelSuccess">成功</h4>
                </div>
                <div class="modal-body">操作成功!</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="success()">好的</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <%--失败模态框--%>
    <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabelError">失败</h4>
                </div>
                <div class="modal-body">操作失败!</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">好的</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <script type="text/javascript">
        $("#newbutton").click(function(){
            if($("#destinationAdd").val() == ""){
                alert("请填写目的地！");
                return;
            }

            var str = {};
            str["company"] = $("#companyAdd").val();
            str["destination"] = $("#destinationAdd").val();
            str["beginTime"] = $("#beginTimeAdd").val();

            $.ajax({                    //获得各个区域的值
                type:"post",
                async: false, //同步执行
                url:"/wetio/manage/travle/add",
                data:str,
                success:function(result){
                    if (result.toString() == "success"){
                        $('#successModal').modal('show');
                    }else if (result.toString() == "error"){
                        $('#errorModal').modal('show');
                    }
                }
            });
        })
    </script>

    <script type="text/javascript">
        function success(){
            $('#successModal').modal('hide');
            $('#newModal').modal('hide');
            $('#editModal').modal('hide');
            window.location.reload();
        }
    </script>

    <script type="text/javascript">
        function edit(id){
            $.ajax({                    //获得各个区域的值
                type:"post",
                async: false, //同步执行
                url:"/wetio/manage/travle/add",
                data:[{"id":id}],
                success:function(result){
                    str = eval(result);

                }
            });
        }
    </script>

</body>
</html>
