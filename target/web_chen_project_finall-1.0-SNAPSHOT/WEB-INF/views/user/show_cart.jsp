<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>订单详情</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="${pageContext.servletContext.contextPath }/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
    <script src="${pageContext.servletContext.contextPath }/popper/popper.min.js"></script>
    <script src="${pageContext.servletContext.contextPath }/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        img{height:50px}
        td{max-width:350px}
    </style>
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
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/user/user_index.do">正在点餐</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/user/show_cart.do">我的点餐</a>
                    </li>
                </ul>
                <ul class="nav justify-content-end">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"> ${user.username} </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/user/user_modify.do?id=${user.id}">修改个人资料</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/index.do">退出登录</a>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="card border">
        <div class="card-body">
            <form method="post" action="${pageContext.servletContext.contextPath }/user/del_cart.do">
                <div class="table-responsive-xl">
                    <table class="table table-striped table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">菜名</th>
                            <th scope="col">特色</th>
                            <th scope="col">主料</th>
                            <th scope="col">价格</th>
                            <th scope="col">分类</th>
                            <th scope="col">图片</th>
                            <th scope="col">点餐率</th>
                            <th scope="col">备注</th>
                            <th scope="col">选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${foods.list }" var="food" varStatus="vs">
                            <tr>
                                <th scope="row">${vs.index+1 }</th>
                                <td>${food.foodname }</td>
                                <td>${food.feature }</td>
                                <td>${food.material }</td>
                                <td>${food.price }</td>
                                <td>${food.typename }</td>
                                <td>
                                    <img class="img-rounded" src="${pageContext.servletContext.contextPath }/${food.picture }" />
                                </td>
                                <td>${food.hits }</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${food.comment == '0' }">厨师推荐</c:when>
                                        <c:when test="${food.comment == '-1' }">&nbsp;</c:when>
                                        <c:otherwise>特价${food.comment }元</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><input type="checkbox" name="ids" value="${food.id }"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-danger btn-block">将 菜 品 从 点 餐 车 中 删 除</button>
                </div>
            </form>
        </div>
        <div class="card-footer border">
            <div class="row">
                <div class="col-md-12 col-12">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <c:forEach begin="1" end="${foods.totalPage}" var="i">
                                <li class="page-item"><a class="page-link" href="show_cart.do?pageno=${i}">${i}</a></li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>