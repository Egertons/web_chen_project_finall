<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员管理菜品类型信息</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="${pageContext.request.contextPath }/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
        <script src="${pageContext.request.contextPath }/popper/popper.min.js"></script>
        <script src="${pageContext.request.contextPath }/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../nav.jsp">
                <jsp:param value="type" name="fun"/>
            </jsp:include>

            <div class="row">
                <div class="col-md-1 col-0"></div>
                <div class="col-md-10 col-12">
                    <div class="card border">
                        <div class="card-header">
                            <form class="form-inline" action="${pageContext.servletContext.contextPath}/admin/type_list.do" method="post">
                                <div class="form-group">
                                    <input class="form-control mr-sm-2" type="text" name="t_un" value="" placeholder="按菜品类型搜索" />
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">搜索</button>
                                </div>
                                <ul class="nav justify-content-end">
                                    <li class="nav-item ">
                                        <a href="${pageContext.servletContext.contextPath}/admin/foodtypeaddform.do" class="btn btn-outline-primary my-2 my-sm-0" tabindex="-1" role="button" aria-disabled="true">添加菜品类型</a>
                                    </li>
                                </ul>
                            </form>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive-xl">
                                <table class="table table-striped table-hover table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>菜品分类</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${types.list }" var="type">
                                        <tr>
                                            <th>${type.id}</th>
                                            <td>${type.typename}</td>
                                            <td>
                                                <a href="${pageContext.servletContext.contextPath}/admin/modify_type.do?id=${type.id}" class="btn btn-sm btn-outline-primary">修改</a>
                                                <a href="${pageContext.servletContext.contextPath}/admin/del_type.do?id=${type.id}" class="btn btn-sm btn-outline-danger">删除</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-end">
                                        <c:forEach begin="1" end="${types.totalPage}" var="i">
                                            <li class="page-item"><a class="page-link" href="user_list.do?s_un=${types.t_un}&pageno=${i}">${i}</a></li>
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>