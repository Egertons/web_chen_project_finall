<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员管理用户信息</title>
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
            <div class="row">
                <div class="col-md-12 col-12">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
                        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/index.do">
                            <span class="text-info">网络点餐系统</span>
                        </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/user_list.do">用户管理</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/type_list.do">菜品分类管理</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/food_list.do">菜品管理</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/cart_show.do">查看用户点餐情况</a>
                            </li>
                        </ul>
                        <ul class="nav justify-content-end">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"> admin </a>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li>
                                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/index.do">退出登录</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="row">
                <div class="col-md-1 col-0"></div>
                <div class="col-md-10 col-12">
                    <div class="card border">
                        <div class="card-header">
                            <form class="form-inline" action="${pageContext.servletContext.contextPath}/admin/user_list.do" method="post">
                                <div class="form-group">
                                    <input class="form-control mr-sm-2" type="text" name="s_un" value="" placeholder="按用户名搜索" />
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">搜索</button>
                                </div>
                                <ul class="nav justify-content-end">
                                    <li class="nav-item ">
                                        <a href="${pageContext.servletContext.contextPath}/admin/useraddform.do" class="btn btn-outline-primary my-2 my-sm-0" tabindex="-1" role="button" aria-disabled="true">添加用户</a>
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
                                            <th>用户名</th>
                                            <th>角色</th>
                                            <th>电话</th>
                                            <th>地址</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${users.list }" var="user">
                                            <tr>
                                                <th>${user.id}</th>
                                                <td>${user.username}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${user.ident==1}">管理员</c:when>
                                                        <c:otherwise>普通用户</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${user.telephone }</td>
                                                <td>${user.address }</td>
                                                <td>
                                                    <a href="${pageContext.servletContext.contextPath}/admin/modify_user.do?id=${user.id}" class="btn btn-sm btn-outline-primary">修改</a>
                                                    <a href="${pageContext.servletContext.contextPath}/admin/del_user.do?id=${user.id}" class="btn btn-sm btn-outline-danger">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-end">
                                        <c:forEach begin="1" end="${users.totalPage}" var="i">
                                            <li class="page-item"><a class="page-link" href="user_list.do?s_un=${users.s_un}&pageno=${i}">${i}</a></li>
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