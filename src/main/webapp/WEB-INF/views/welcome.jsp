<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<html>
<body>

	<br>
	<br>
	<div class="jumbotron text-center">
		<strong>Welcome
		<sec:authentication property="principal.username" />
		!!!!!!!!</strong> <a href="<c:url value="/logout" />">Logout</a>
	</div>
</body>
</html>