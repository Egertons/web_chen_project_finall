<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员查看用户点餐情况</title>
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
                <jsp:param value="cart" name="fun"/>
            </jsp:include>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 col-0"></div>
                <div class="col-md-6 col-12">
                    <c:forEach items="${carts }" var="cart" varStatus="vs">
                        <div class="card border-primary">
                            <div class="card-body">
                                <ul class="list-group">
                                    <li class="list-group-item active">${cart.username}</li>
                                    <c:set value="0" var="sum"/>
                                    <c:forEach items="${cart['cart'] }" var="d" varStatus="vs">
                                        <li class="list-group-item">
                                            ${d.foodname}
                                            <span class="badge badge-pill badge-success p-2 float-right">￥${d.price}</span>
                                            <c:set value="${sum+d.price}" var="sum"/>
                                        </li>
                                    </c:forEach>
                                    <li class="list-group-item active">
                                        合计
                                        <span class="badge badge-pill badge-success p-2 float-right">￥${sum}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="col-md-3 col-0"></div>
            </div>
        </div>
    </body>
</html>
