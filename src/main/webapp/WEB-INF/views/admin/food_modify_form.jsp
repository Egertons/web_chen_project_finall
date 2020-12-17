<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>管理员修改菜品信息</title>
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
                    请输入菜品信息
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.servletContext.contextPath }/admin/food_update.do" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${food.id }" />
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">菜品名</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="username" name="f_un" type="text" required value="${food.foodname }" />
                                <span class="text-danger" id="checkInfo"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">菜品特色</label>
                            <div class="col-sm-10">
                                <input class="form-control" name="fea" type="text" required value="${food.feature }"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">主要原料</label>
                            <div class="col-sm-10">
                                <input class="form-control" name="mat" type="text" required value="${food.material }"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">所属分类</label>
                            <select class="form-control" name="type" id="type">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.id }">${type.typename }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">菜品价格</label>
                            <input type="number" class="form-control" name="price" id="price" required="required" value="${food.price }"  />
                            <p class="text-info">单位：元</p>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">菜品图片</label>
                        </div>
                        <div class="form-group row">
                            <img src="${pageContext.servletContext.contextPath }/${food.picture }" alt="">
                            <input type="file" id="img" name="img"  accept="image/*"  />
                            <p class="text-info">请选择上传的菜品图片，大小应小于5M，扩展名为jpg,png或gif。(文件名不易过长且必须修改图片)</p>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">菜品备注</label>
                            <input type="text" class="form-control" name="com" id="comment" required="required" value="${food.comment }"  />
                            <p class="text-info">-1代表正常菜，0代表厨师推荐，正整数代表特价菜价格。</p>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary" id="regButton" >确定</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
