<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>登録確認</h2>
	<form action="./New" method="post">

		<p>
			問題:<textarea readonly name="question" rows="4" cols="40"><%=request.getAttribute("question")%></textarea>
		</p>
		<%
		String[] arr = (String[])request.getAttribute("answer");
		for (int i = 0; i < arr.length; i++) {
		%>
		<p>
			答え:<input type="text" name="answer" readonly value="<%=arr[i]%>">
		</p>
		<% } %>
		<a href="Register.jsp">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="登録する">
		</p>
	</form>
</body>
</html>