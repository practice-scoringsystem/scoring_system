<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.UsersBean"%>
<%
// サーブレットから詳細の情報を取得 キーで取り出す
UsersBean ub = (UsersBean) request.getAttribute("ub");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー編集</title>
</head>
<body>

	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>ユーザー編集</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./UserEditConfirm" method="post">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<div>
			<p>
				ID:<input type="text" name="user_id" value="<%=ub.getId()%>">
			</p>
			<p>
				ユーザー名:<input type="text" name="name" value="<%=ub.getName()%>">
			</p>
			<p>
				PW:<input type="password" name="password"
					value="<%=ub.getPassword()%>">
			</p>
			<p>
				PW確認:<input type="password" name="passwordConfirm"
					value="<%=ub.getPassword()%>">
			</p>
			<%
			if (String.valueOf(ub.getAdminFlag()).equals("1")) {
			%>
			<p>
				管理者:<input type="checkbox" name="adminCheck" value="1" checked="checked">
			</p>
			<input type="hidden" name="adminCheck" value="0">
			<%
			} else {
			%>
			<p>
				管理者:<input type="checkbox" name="adminCheck" value="1">
			</p>
			<input type="hidden" name="adminCheck" value="0">
			<%
			}
			%>
		</div>
		<a href="./UsersList">
			<button type="button">戻る</button>
		</a>
		<!-- 入力したものを確認する　confirmでsubmit -->
		<p>
			<input type="submit" value="編集内容を確認する">
		</p>
	</form>

</body>
</html>