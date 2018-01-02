<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
input {
	margin-bottom: 10px;
}

select {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<br> <br>
		<div class="row">
			<div class="col-sm-offset-4 col-sm-4 well">
				<h3>Add a member</h3>
				<hr>
				<form:form method="POST" modelAttribute="user"
					class="form-horizontal" role="form" enctype="multipart/form-data">

					<!-- Username -->
					<form:errors path="username" style="color:red" />
					<form:input path="username" class="form-control"
						placeholder="Enter your username" />
					<!-- Name -->
					<form:errors path="name" style="color:red" />
					<form:input path="name" class="form-control"
						placeholder="Enter your name" />

					<!-- Password -->
					<form:errors path="password" style="color:red" />
					<form:password path="password" class="form-control"
						placeholder="Enter your password" />

					<!-- Confirm password -->
					<form:errors path="confirmPassword" style="color:red" />
					<form:password path="confirmPassword" class="form-control"
						placeholder="Confirm your password" />

					<!-- Gender -->
					<div>
						<form:errors path="gender" style="color:red" />
					</div>
					<label class="radio-inline"><form:radiobutton path="gender"
							value="Male" label="Male" /></label>
					<label class="radio-inline"><form:radiobutton path="gender"
							value="Female" label="Female" /></label>

					<!-- Birthday -->
					<div>
						<form:errors path="birthday" style="color:red" />
					</div>
					<form:input type="date" path="birthday" class="form-control" />

					<!-- Role -->
					<select class="form-control" name="role">
						<option>MEMBER</option>
						<option>ADMIN</option>
					</select>
					
					<!-- Avatar -->
					<input type="file" name="file">

					<hr>
					<button type="submit" class="btn btn-primary">Submit</button>
					<a href="<c:url value="/admin/user"/>" class="btn btn-info">Back
						to list</a>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>