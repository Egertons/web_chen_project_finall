<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>结果页</title>
		 <!-- Bootstrap CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/4.3.1/css/bootstrap.min.css">
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="${pageContext.request.contextPath }/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
		<script src="${pageContext.request.contextPath }/popper/popper.min.js"></script>
		<script src="${pageContext.request.contextPath }/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script>
			$(function() {
				$('#resultModal').modal('show');
				$('#okBtn').click(function(){
					location.replace('${href }');
				});
			});
		</script>
	</head>
	<body>
		<!-- Modal -->
		<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header bg-primary">
						<h5 class="modal-title text-warning">提示信息</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">${msg}</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="okBtn">确定</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>