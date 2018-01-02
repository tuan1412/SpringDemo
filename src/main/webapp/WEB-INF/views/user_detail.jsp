<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<c:url value="/static/css/confirmstyle.css"/>">
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

img {
	margin: 10px 0px;
}
</style>
</head>
<body>
	<div class="container">
		<br> <br>
		<div class="row">
			<div class="col-sm-offset-4 col-sm-4 well">
				<h3>Edit a member</h3>
				<hr>
				<form:form method="POST" modelAttribute="user"
					class="form-horizontal" role="form" enctype="multipart/form-data">
					<form:hidden path="id"/>
					<!-- Username -->
					<label>Username:</label>
					<br>
					<form:errors path="username" style="color:red" />
					<form:input path="username" class="form-control"
						placeholder="Enter your username" readonly="true" />
					<!-- Name -->
					<label>Name:</label>
					<br>
					<form:errors path="name" style="color:red" />
					<form:input path="name" class="form-control"
						placeholder="Enter your name" />
					
					<!-- Gender -->
					<div>
						<label>Gender:</label><br>
						<form:errors path="gender" style="color:red" />
					</div>
					<label class="radio-inline"><form:radiobutton path="gender"
							value="Male" label="Male" /></label>
					<label class="radio-inline"><form:radiobutton path="gender"
							value="Female" label="Female" /></label>
					<form:hidden path="password"/>
					<form:hidden path="confirmPassword" value="${user.password}"/>
					<form:hidden path="avatar"/>
					<!-- Birthday -->
					<div>
						<label>Birthday:</label><br>
						<form:errors path="birthday" style="color:red" />
					</div>
					<form:input type="date" path="birthday" class="form-control" />

					<!-- Role -->
					<label>Role:</label>
					<br>
					<select name="role" class="form-control">
						<option value="admin" ${user.admin eq true ? 'selected' : ''}>Admin</option>
						<option value="member" ${user.admin eq false ? 'selected' : ''}>Member</option>
					</select>
					<form:hidden path="roles"/>

					<!-- Avatar -->
					<img alt="avatar" src="<c:url value="/upload/${user.avatar}"/>"
						width="75" height="75">
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