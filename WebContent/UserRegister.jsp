<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>ユーザー登録</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./UserRegisterConfirm" method="post">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<div>
			ユーザー名：<input type="text" name="name" size="40"><br>
			PW ：<input type="password" name="password" size="40"><br>
			PW確認 ：<input type="password" name="confirmPassword" size="40"><br>
			管理者 ：<input type="checkbox" name="adminCheck" id="saveCheckbox" value="1">
			<input type="hidden" name="adminCheck" value="0">
		</div>
		<div>
			<a href="./UsersList">
				<button type="button">戻る</button>
			</a>
			<p>
				<input type="submit" value="確認">
			</p>
		</div>
	</form>
</body>
</html>