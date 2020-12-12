<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
    实际上就是把导航栏的显示分离出来
    在每种情况的开头进行一步判断，实时监控此时“Session”内用户的“ident”值。
    如为null，则是未登录状态（游客模式）
    为1，则是管理员模式
    为0，则是普通用户登录后状态
 --%>


<c:if test="${ sessionScope.user==null }">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 col-12">
                <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
                    <a class="navbar-brand" href="index.do">
                        <span class="text-info" style="letter-spacing: 5px">网络点餐系统</span></a>
                    <ul class="nav justify-content-end">
                        <li class="nav-item">
                            <button type="button" class="btn btn-primary" style="margin-right: 20px">
                                <a class="nav-link" data-toggle="modal" data-target="#loginModal">登录</a>
                            </button>
                        </li>
                        <li class="nav-item">
                            <button type="button" class="btn btn-success">
                                <a class="nav-link" data-toggle="modal" data-target="#registerModal">注册</a>
                            </button>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</c:if>


<c:if test="${ not empty sessionScope.user && sessionScope.user.ident == 1 }">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 col-12">
                <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
                    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/index.do">
                        <span class="text-info" style="letter-spacing: 5px">网络点餐系统</span>
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
                            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                                ${user.username}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li>
                                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/logout.do">退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</c:if>


<c:if test="${ not empty sessionScope.user && sessionScope.user.ident == 0 }">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 col-12">
                <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
                        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/index.do">
                            <span class="text-info" style="letter-spacing: 5px">网络点餐系统</span>
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
                            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                                ${user.username }
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">修改个人资料</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/logout.do">退出登录</a>
                            </div>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</c:if>