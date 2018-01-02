<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>ListUser</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="container">
		<header class="clearfix">
			<img src="<c:url value="/upload/logo.gif"/>" alt="rikkeisoft">
			<ul class="nav">
				<li><a href="<c:url value="/logout"/>"
					class="btn btn-default btn-block">Logout</a></li>
				<li><a href="<c:url value="/admin/user"/>"
					class="btn btn-default btn-block">Home</a></li>
			</ul>
		</header>

		<div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
					</div>
					<div class="modal-body">
						<p>Do u want to delete this user?</p>
						<p class="debug-url"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<a class="btn btn-danger btn-ok">Delete</a>
					</div>
				</div>
			</div>
		</div>
		<div>
			<a href="<c:url value="/admin/user/add"/>" class="btn btn-default">Add
				a user</a>
		</div>
		<br>
		<c:if test="${not empty success}">
		<div class="jumbotron text-center">${success}</div></c:if>
		<table class="col-sm-12 table-bordered">
			<thead>
				<tr>
					<th>Avatar</th>
					<th>Username</th>
					<th>Name</th>
					<th>Gender</th>
					<th>Birthday</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<th><img alt="avatar"
							src="<c:url value="/upload/${user.avatar}"/>" width="50"
							height="50">
						<th>${user.username}</th>
						<th>${user.name}</th>
						<th>${user.gender}</th>
						<th>${user.birthday}</th>
						<th><c:choose>
								<c:when test="${user.admin}">
									<span class="label label-danger">Admin</span>			
								</c:when>
								<c:otherwise>
									<span class="label label-success">Member</span>
								</c:otherwise>
							</c:choose></th>
						<th><a href="<c:url value="/admin/user/${user.id}"/>"
							class="btn btn-warning"> <i class="glyphicon glyphicon-edit"></i></a>
							<a href="#" data-original-title="Remove this user"
							data-toggle="modal" data-target="#confirm-delete"
							data-href="<c:url value="/admin/user/${user.id}/delete"/>"
							class="btn btn-danger"> <i class="glyphicon glyphicon-remove"></i>
						</a>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<footer class="col-sm-12" style="padding-left: 0px">
			<ul class="pagination">
				<c:choose>
					<c:when test="${empty param.page}">
						<c:set var="page" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="page" value="${param.page}" />
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" end="${pageNums}" var="i">
					<c:choose>
						<c:when test="${page eq i}">
							<li class="page-item active"><span class="page-link">
									${i} <span class="sr-only">(current)</span>
							</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="<c:url value="/admin/user?page=${i}"/>">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</ul>
		</footer>
	</div>

	<script>
		$('#confirm-delete').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});
	</script>
</body>
</html>