<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员管理菜品信息</title>
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
                <jsp:param value="food" name="fun"/>
            </jsp:include>

            <div class="row">
                <div class="col-md-1 col-0"></div>
                <div class="col-md-10 col-12">
                    <div class="card border">
                        <div class="card-header">
                            <form class="form-inline" action="${pageContext.servletContext.contextPath}/admin/food_list.do" method="post">
                                <div class="form-group">
                                    <input class="form-control mr-sm-2" type="text" name="c_un" value="" placeholder="按菜名搜索" />
                                </div>
                                <div class="dropdown">
                                    <select class="form-control" name="type">
                                        <option value="">所有分类</option>
                                        <option value="3">主食</option>
                                        <option value="2">凉菜</option>
                                        <option value="1">家常</option>
                                        <option value="4">饮品</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">搜索</button>
                                </div>
                                <ul class="nav justify-content-end">
                                    <li class="nav-item ">
                                        <a href="${pageContext.servletContext.contextPath}/admin/foodaddform.do" class="btn btn-outline-primary my-2 my-sm-0" tabindex="-1" role="button" aria-disabled="true">添加菜品</a>
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
                                            <th>菜名</th>
                                            <th>特色</th>
                                            <th>主料</th>
                                            <th>价格</th>
                                            <th>分类</th>
                                            <th>图片</th>
                                            <th>点击率</th>
                                            <th>备注</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${foods.list }" var="food">
                                            <tr>
                                                <th>${food.id}</th>
                                                <td>${food.foodname}</td>
                                                <td>${food.feature}</td>
                                                <td>${food.material }</td>
                                                <td>${food.price }</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${food.type==3}">主食</c:when>
                                                        <c:when test="${food.type==2}">凉菜</c:when>
                                                        <c:when test="${food.type==6}">副食</c:when>
                                                        <c:when test="${food.type==1}">家常</c:when>
                                                        <c:when test="${food.type==4}">饮品</c:when>
                                                    </c:choose>
                                                </td>
                                                <td><img src="${pageContext.servletContext.contextPath}/${food.picture }" alt=""></td>
                                                <td>${food.hits }</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${food.comment==-1}">厨师推荐</c:when>
                                                        <c:when test="${food.comment==0}">特价十元</c:when>
                                                        <c:otherwise>特价壹元</c:otherwise>
                                                    </c:choose>
                                                </td><!--备注-->
                                                <td>
                                                    <a href="${pageContext.servletContext.contextPath}/admin/modify_food.do?id=${food.id}" class="btn btn-sm btn-outline-primary">修改</a>
                                                    <a href="${pageContext.servletContext.contextPath}/admin/del_food.do?id=${food.id}" class="btn btn-sm btn-outline-danger">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-end">
                                        <c:forEach begin="1" end="${foods.totalPage}" var="i">
                                            <li class="page-item"><a class="page-link" href="food_list.do?c_un=${foods.c_un}&pageno=${i}">${i}</a></li>
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
