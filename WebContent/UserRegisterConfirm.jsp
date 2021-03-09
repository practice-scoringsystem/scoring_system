<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録確認画面</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>登録確認</h2>
	<form action="./UserRegister" method="post">

		<p>
			ユーザー名:<input type="text" readonly name="name" size="44"
				value="<%=request.getAttribute("name")%>">
		</p>
		<p>
			PW:<input type="password" name="password" readonly
				value="<%=request.getAttribute("pw")%>">
		</p>
		<p>
			PW確認:<input type="password" readonly
				value="<%=request.getAttribute("cpw")%>">
		</p>
		<input type="hidden" name="adminCheck"
			value="<%=String.valueOf(request.getAttribute("adc"))%>">
		<%
		if (String.valueOf(request.getAttribute("adc")).equals("1")) {
		%>
		<p>管理者権限:あり</p>
		<%
		} else {
		%>
		<p>管理者権限:なし</p>
		<%
		}
		%>
		<a href="UserRegister.jsp">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="登録する">
		</p>
	</form>
</body>
</html>