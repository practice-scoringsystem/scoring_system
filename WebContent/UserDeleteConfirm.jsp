<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.UsersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>以下の内容を削除してもよろしいですか？</h2>
	<form action="./UserDelete" method="post">
		<%
		// サーブレットから詳細の情報を取得 キーで取り出す
		UsersBean ub = (UsersBean) request.getAttribute("ub");
		%>
		<p>
			ID:<input type="text" readonly name="user_id" value="<%=ub.getId()%>" />
		</p>
		<p>
			ユーザー名:<input type="text" readonly name="name"
				value="<%=ub.getName()%>" />
		</p>
		<p>
			PW:<input type="password" readonly name="password"
				value="<%=ub.getPassword()%>" />
		</p>
		<p>
			PW確認:<input type="password" readonly value="<%=ub.getPassword()%>" />
		</p>
		<input type="hidden" name="admin_flag" value="<%=ub.getAdminFlag()%>" />
		<%
		if (String.valueOf(ub.getAdminFlag()).equals("1")) {
		%>
		<p>管理者権限:あり</p>
		<%
		} else {
		%>
		<p>管理者権限:なし</p>
		<%
		}
		%>

		<a href="./UsersList">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="削除">
		</p>
	</form>

</body>
</html>