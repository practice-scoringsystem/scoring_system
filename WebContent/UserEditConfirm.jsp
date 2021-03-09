<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー編集確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>ユーザー編集確認</h2>
	<form action="./UserUpdate" method="post">
		<p>
			ID:<input type="text" readonly name="user_id"
				value="<%=request.getAttribute("user_id")%>" />
		</p>
		<p>
			ユーザー名:<input type="text" readonly name="name"
				value="<%=request.getAttribute("name")%>" />
		</p>
		<p>
			pw:<input type="password" readonly name="password"
				value="<%=request.getAttribute("password")%>" />
		</p>
		<p>
			pw確認:<input type="password" readonly
				value="<%=request.getAttribute("password")%>" />
		</p>
		<input type="hidden" value="<%=request.getAttribute("adc")%>" />
		</p>
		<%
		if (String.valueOf(request.getAttribute("adc")).equals("1")) {
		%>
		<p>管理者:あり</p>
		<%
		} else {
		%>
		<p>管理者:なし</p>
		<%
		}
		%>

		<a href="Edit?user_id=<%=request.getAttribute("user_id")%>">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="更新">
		</p>
	</form>
</body>
</html>