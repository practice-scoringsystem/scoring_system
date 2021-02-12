<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body>
	<%
	String successMessage = (String) request.getAttribute("success_message");
	%>
	<%
	if (successMessage != null) {
	%>
	<%=successMessage%>
	<%
	}
	%>
	<a href="./Login.jsp">ログイン画面へ</a>
</body>
</html>