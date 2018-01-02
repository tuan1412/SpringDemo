<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">

<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.input-group {
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<br>
	<br>
	<div class="container">
		<div class="row ">
			<div class="col-sm-offset-4 col-sm-4 well">
				<legend>Admin login</legend>
				<c:if test="${not empty param.error}">
					<div class="alert alert-danger alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
						<p>Invaild username or password</p>
					</div>
				</c:if>
				<form method="post">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="username" type="text" class="form-control"
							name="username" placeholder="username">
					</div>
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-lock"></i>
						</span> <input id="password" type="password" class="form-control"
							name="password" placeholder="password">
					</div>
					<hr>
					<button type="submit" class="btn btn-primary btn-block">Login</button>
				</form>

			</div>
		</div>
	</div>

</body>

</html>