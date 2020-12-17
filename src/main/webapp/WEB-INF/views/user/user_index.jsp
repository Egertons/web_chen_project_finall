<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>购物车</title>
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
            <jsp:include page="../nav.jsp">
                <jsp:param value="carting" name="fun"/>
            </jsp:include>
        </div>
        <div class="container-fluid">
            <div class="card border">
                <div class="card-header border">
                    <form class="form-inline" action="${pageContext.servletContext.contextPath }/user/user_index.do" method="post">
                        <input type="search" class="form-control mr-sm-2" placeholder="按菜名搜索" name="s_fn" value="${param['s_fn'] }">
                        <select class="form-control mr-sm-2" name="s_type">
                            <option value="">所有分类</option>
                            <c:forEach items="${types }" var="type">
                                <option value="${type.id }"
                                    ${type.id == param.s_type?'selected':'' }>
                                    ${type.typename }
                                </option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.servletContext.contextPath }/user/add_cart.do">
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
                            <button type="submit" class="btn btn-success btn-block">将 菜 品 添 加 到 点 餐 车</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer border">
                    <div class="row">
                        <div class="col-md-12 col-12">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end">
                                    <c:forEach begin="1" end="${foods.totalPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="user_index.do?s_type=${s_type}&s_fn=${s_name}&pageno=${i}">${i}</a></li>
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