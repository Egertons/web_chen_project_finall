<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>首页</title>
		 <!-- Bootstrap CSS -->
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="icon" href="../../favicon.ico">
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="${pageContext.servletContext.contextPath }/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
		<script src="${pageContext.servletContext.contextPath }/popper/popper.min.js"></script>
		<script src="${pageContext.servletContext.contextPath }/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12 col-12">
					<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
						<a class="navbar-brand" href="index.do">
							<span class="text-info" style="letter-spacing: 5px">网络点餐系统</span>
						</a>
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
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-12" style="text-align: center">
					<h4 class="list-group-item active" style="letter-spacing: 5px">热点菜品</h4>
					<ul class="list-group">
						<c:forEach items="${hots}" var="hot">
						<li class="list-group-item">
							<img class="img-rounded" src="${pageContext.servletContext.contextPath }/${hot.picture}" />
							<a href="${pageContext.servletContext.contextPath }/show_detail.do?id=${hot.id}" target="_blank" style="text-decoration: none"> ${hot.foodname}</a>
							&nbsp;&nbsp;
							<span>${hot.price }元</span>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-md-4 col-12" style="text-align: center">
					<h4 class="list-group-item active" style="letter-spacing: 5px">今日特价</h4>
					<ul class="list-group">
						<c:forEach items="${sales}" var="sale">
						<li class="list-group-item">
							<img class="img-rounded" src="${pageContext.servletContext.contextPath }/${sale.picture}" />
							<a href="${pageContext.servletContext.contextPath }/show_detail.do?id=${sale.id}" target="_blank" style="text-decoration: none"> ${sale.foodname}</a>
							&nbsp;&nbsp;
							<span>${sale.price}元</span>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-md-4 col-12" style="text-align: center">
					<h4 class="list-group-item active" style="letter-spacing: 5px">厨师推荐</h4>
					<ul class="list-group">
						<c:forEach items="${recommeds}" var="rec">
						<li class="list-group-item">
							<img class="img-rounded" src="${pageContext.servletContext.contextPath }/${rec.picture}" />
							<a href="${pageContext.servletContext.contextPath }/show_detail.do?id=${rec.id}" target="_blank" style="text-decoration: none"> ${rec.foodname}</a>
							&nbsp;&nbsp;
							<span>${rec.price}元</span>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal" id="loginModal" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-info">用户登录</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="${pageContext.servletContext.contextPath }/login.do">
						<div class="modal-body">
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">用户名</label>
								<div class="col-sm-10">
									<input class="form-control" name="un" type="text" required />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">密码</label>
								<div class="col-sm-10">
									<input class="form-control" name="pw" type="password" required />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<script>
			 $(function(){
				 $('#username').bind("blur",function(){
					$.ajax({
						type : "post",
						url : "check_username.do",
						data: {'username' : $('#username').val()},
						dataType:"json",
						success: function(data){
							$('#checkInfo').html(data.msg);
							if(data.r){
								$('#regButton').removeAttr('disabled');
							}else{
								$('#regButton').attr('disabled','disabled');
							}
						}
					});
				});
			 });
		</script>


		<!-- Modal -->
		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-info">用户注册</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="${pageContext.servletContext.contextPath }/register.do">
						<div class="modal-body">
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">用户名</label>
								<div class="col-sm-10">
									<input class="form-control" id="username" name="un" type="text" required />
									<span class="text-danger" id="checkInfo"></span>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">密码</label>
								<div class="col-sm-10">
									<input class="form-control" name="pw" type="password" required />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">电话</label>
								<div class="col-sm-10">
									<input class="form-control" name="tel" type="number" required />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">地址</label>
								<div class="col-sm-10">
									<input class="form-control" name="addr" type="text" required />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary" id="regButton">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>