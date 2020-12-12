<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>菜品详情介绍:${food.foodname }</title>
		 <!-- Bootstrap CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/4.3.1/css/bootstrap.min.css">
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="${pageContext.request.contextPath }/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
		<script src="${pageContext.request.contextPath }/popper/popper.min.js"></script>
		<script src="${pageContext.request.contextPath }/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<style>
			table.table tr th {
				vertical-align: middle;
				text-align: center;
				width: 30%
			}
		</style>
	</head>
	<body>
		<p></p>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3 col-0"></div>
				<div class="col-md-6 col-12">
					<table class="table table-hover table-bordered">
						<tbody>
							<tr class="table-primary">
								<th scope="col">菜 名</th>
								<td>
									<c:out value="${food.foodname }" escapeXml="true" />
								</td>
							</tr>
							<tr class="table-secondary">
								<th scope="row">特 色</th>
								<td><c:out value="${food.feature }" escapeXml="true" /></td>
							</tr>
							<tr class="table-success">
								<th scope="row">食 材</th>
								<td><c:out value="${food.material }" escapeXml="true" /></td>
							</tr>
							<tr class="table-danger">
								<th scope="row">类 型</th>
								<td><c:out value="${foodtype.typename }" escapeXml="true" /></td>
							</tr>
							<tr class="table-warning">
								<th scope="row">价 格</th>
								<td>${food.price }元</td>
							</tr>
							<tr class="table-info">
								<th scope="row">图 片</th>
								<td><img class="img-rounded" alt="Bootstrap Image Preview" src="${food.picture }" /></td>
							</tr>
							<tr class="table-danger">
								<th scope="row">点 餐 率</th>
								<td>${food.hits }次</td>
							</tr>
							<tr class="table-success">
								<th scope="row">备 注</th>
								<td>
									<c:choose>
										<c:when test="${food.comment == '0' }">厨师推荐</c:when>
										<c:when test="${food.comment == '-1' }">&nbsp;</c:when>
										<c:otherwise>特价${food.comment }元</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</tbody>
					</table>
					<form method="post" action="${pageContext.request.contextPath }/user/add_cart.do">
						<input type="hidden" name="ids" value="${food.id }">
						<div class="form-group">
							<button type="submit" class="btn btn-danger btn-block">
								将 菜 品 添 加 到 点 餐 车
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>