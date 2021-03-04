<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
nav.nav {
	text-align: right;
}
</style>
</head>
<body>
	<nav class="nav">
		<form action="./Logout" method="post">
			<input type="hidden" name="action" value="logout"/>
			<input type="submit" value="logout" />
		</form>
		<a href="./Top.jsp">
			<button type="button">Top</button>
		</a>
	</nav>

</body>
</html>