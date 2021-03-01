<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<form action="./Login" method="post">
		<%
			if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
						}
		%>
		<div class="login">
			<div class="id">
				ID:<input type="text" name="id" size="40" maxlength="30"><br>
				<br>
			</div>
			<div class="pass">
				pw:<input type="password" name="password" size="41" maxlength="20"><br>
				<br>
			</div>
			<div class="submit">
				<input type="submit" value="login">
			</div>
		</div>
	</form>
</body>
</html>