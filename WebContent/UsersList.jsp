<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.UsersBean"%>
<!-- エラーメッセージを受け取る -->
<%
String error = (String) request.getAttribute("error_message");
%>
<%-- リクエストスコープからBeanクラスの配列を取得 --%>
<%
List<UsersBean> ulist = (List<UsersBean>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
</head>
<body>
	<%
	if (error != null) {
	%>
	<%=error%>
	<%
	}
	%>
	<jsp:include page="Header.jsp" />
	<div style="text-align: center">
		<a href=#>
			<button type="button">新規登録</button>
		</a>
		<h2 style="text-align: center">一覧</h2>
		<hr style="height: 3; background-color: #0000FF" />
		<br>

		<table style="margin: 0 auto">
			<tr>
				<th style="background-color: #6666FF; width: 100">ID</th>
				<th style="background-color: #6666FF; width: 100">権限</th>
				<th style="background-color: #6666FF; width: 100">ユーザー名</th>
				<th style="background-color: #6666FF; width: 250">編集</th>
				<th style="background-color: #6666FF; width: 250">削除</th>
			</tr>
			<%-- Beanの要素数分（問題の数分）テーブルを作成 --%>
			<%
			for (int i = 0; i < ulist.size(); i++) {
			%>

			<tr>
				<%
				if (ulist.get(i) != null) {
				%>
				<td><%=ulist.get(i).getId()%></td>
				<input type="hidden" name="questions_id"
					value="<%=ulist.get(i).getId()%>">

				<%
				if (String.valueOf(ulist.get(i).getAdminFlag()).equals("1")) {
				%>
				<td>管理者</td>
				<%
				} else {
				%>
				<td>一般</td>
				<%
				}
				%>
				<td><%=ulist.get(i).getName()%></td>
				<td><a href="UserEdit?user_id=<%=ulist.get(i).getId()%>">
						<button type="button">編集</button>
				</a></td>
				<td><a href="UserDeleteConfirm?user_id=<%=ulist.get(i).getId()%>">
						<button type="button">削除</button>
				</a></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>

		</table>
	</div>
</body>
</html>