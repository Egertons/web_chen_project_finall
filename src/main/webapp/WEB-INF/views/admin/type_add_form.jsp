<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>管理员添加菜品分类信息</title>
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
                    <div class="card border ">
                        <div class="card-header bg-success" style="text-align: center;letter-spacing: 5px;font-size: 30px;line-height: 30px">
                            添加菜品分类
                        </div>
                        <div class="card-body">
                            <form method="post" action="${pageContext.servletContext.contextPath }/admin/type_add_form.do">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">菜品分类名</label>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <input class="form-control" id="foodname" name="tn" type="text" required />
                                        <span class="text-danger" id="checkInfo"></span>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-12" style="text-align: center">
                                        <button type="submit" class="btn btn-primary" id="regButton" >确定添加</button>
                                        <button type="submit" class="btn btn-primary" id="delButton" >放弃返回</button>
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