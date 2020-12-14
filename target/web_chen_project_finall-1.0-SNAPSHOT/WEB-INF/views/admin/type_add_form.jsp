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
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"> ${user.username} </a>
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