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
        <jsp:param value="mycart" name="fun"/>
    </jsp:include>
</div>
<div class="container-fluid">
    <div class="card border">
        <div class="card-body border">
            <form
                    action="${pageContext.servletContext.contextPath }/user/del_cart.do"
                    method="post">
                <div class="table-responsive-xl">
                    <table class="table table-striped table-hover table-sm">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>菜名</th>
                            <th>特色</th>
                            <th>主料</th>
                            <th>价格</th>
                            <th>分类</th>
                            <th>图片</th>
                            <th>点餐率</th>
                            <th>备注</th>
                            <th>选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${carts }" var="food" varStatus="vs">
                            <tr>
                                <th>${vs.count }</th>
                                <td><c:out value="${food.foodname }" escapeXml="true" /></td>
                                <td><c:out value="${food.feature }" escapeXml="true" /></td>
                                <td><c:out value="${food.material }" escapeXml="true" /></td>
                                <td>${food.price }</td>
                                <td><c:out value="${food.typename }" escapeXml="true" /></td>
                                <td><img class="img-rounded"
                                         src="${pageContext.servletContext.contextPath }/${food.picture }" />
                                </td>
                                <td>${food.hits }</td>
                                <td><c:choose>
                                    <c:when test="${food.comment == '0' }">厨师推荐</c:when>
                                    <c:when test="${food.comment == '-1' }">&nbsp;</c:when>
                                    <c:otherwise>特价${food.comment }元</c:otherwise>
                                </c:choose></td>
                                <td><input type="checkbox" name="ids"
                                           value="${food.dcid }"></td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-danger btn-block">将
                        菜 品 从 点 餐 车 删 除</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>