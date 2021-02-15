<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.QuestionsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- エラーメッセージを受け取る -->
<%
String error = (String) request.getAttribute("error_message");
%>
<%-- リクエストスコープからBeanクラスの配列を取得 --%>

<%
List<QuestionsBean> Questionslist = (List<QuestionsBean>) request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<%
	if (error != null) {
	%>
	<%=error%>
	<%
	}
	%>

	<div style="text-align: center">
		<a href="#">
			<button type="button">新規登録</button>
		</a>
		<h2 style="text-align: center">一覧</h2>
		<hr style="height: 3; background-color: #0000FF" />
		<br>



		<table style="margin: 0 auto">
			<tr>
				<th style="background-color: #6666FF; width: 100">ID</th>
				<th style="background-color: #6666FF; width: 100">問題</th>
				<th style="background-color: #6666FF; width: 250">編集</th>
				<th style="background-color: #6666FF; width: 250">削除</th>
			</tr>
			<%-- Beanの要素数分（問題の数分）テーブルを作成 --%>
			<%
			for (QuestionsBean bean : Questionslist) {
			%>
			<tr>
				<%-- 問題ID --%>
				<%
				if (bean != null) {
				%>
				<td><%=bean.getId()%></td>
				<%-- 問題 --%>
				<td><%=bean.getQuestion()%></td>
				<td><a href="#"><button type="button">編集</button></a></td>
				<td><a href="#"><button type="button">削除</button></a></td>
				<%
				}
				%>
				<%
				}
				%>
			</tr>
		</table>
		<br>
	</div>

</body>
</html>