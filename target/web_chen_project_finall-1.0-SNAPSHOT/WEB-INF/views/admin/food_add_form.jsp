<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员添加菜品信息</title>
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
                            <form role="form" method="post" action="${pageContext.servletContext.contextPath }/admin/food_add.do" enctype="multipart/form-data">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="foodname">菜品名称</label>
                                        <input type="text" class="form-control" name="fn" id="foodname" required="required " />
                                    </div>
                                    <div class="form-group">
                                        <label for="feature">菜品特色</label>
                                        <textarea class="form-control" rows="3" name="fea" id="feature" required="required"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="material">主要原料</label>
                                        <textarea class="form-control" rows="3" name="mat" id="material" required="required"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="type">所属分类</label>
                                        <select class="form-control" name="type" id="type">
                                            <c:forEach items="${types}" var="type">
                                                <option value="${type.id }">${type.typename }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="price">菜品价格</label>
                                        <input type="number" class="form-control" name="price" id="price" required="required"   />
                                        <p class="text-info">单位：元</p>
                                    </div>
                                    <div class="form-group">
                                        <label for="img">菜品图片</label>
                                        <input type="file" id="img" name="img"  accept="image/*"/>
                                        <p class="text-info">请选择上传的菜品图片，大小应小于5M，扩展名为jpg,png或gif。</p>
                                    </div>
                                    <div class="form-group">
                                        <label for="comment">菜品备注</label>
                                        <input type="text" class="form-control" name="com" id="comment" value="-1" required="required" />
                                        <p class="text-info">-1代表正常菜，0代表厨师推荐，正整数代表特价菜价格。</p>
                                    </div>
                                </div>
                                <div class="card-footer border-warning text-center">
                                    <button type="submit" class="btn btn-warning">确认添加</button>
                                    &nbsp;&nbsp;
                                    <a role="button" class="btn btn-default" href="javascript:history.back();">放弃返回</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>