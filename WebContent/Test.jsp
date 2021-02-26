<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List" %>
<%@ page import="beans.QuestionsBean"%>
<%
String error = (String) request.getAttribute("error_message");
String success = (String) request.getAttribute("success_message");
%>
<%-- リクエストスコープからBeanクラスの配列を取得 --%>
<%
List<QuestionsBean> Questionslist = (List<QuestionsBean>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト画面</title>
</head>
<body>
	<%
	if (error != null) {
	%>
	<%=error%>
	<%
	}
	%>
	<%
	if (success != null) {
	%>
	<%=success%>
	<%
	}
	%>
	<jsp:include page="Header.jsp" />
	<h2>テスト画面</h2>
	<div>
		<%-- Beanの要素数分（問題の数分）テーブルを作成 --%>
		<%
		for (int i = 0; i < Questionslist.size(); i++){
		%>

		<%-- 問題ID --%>
		<%
		if (Questionslist.get(i) != null) {
		%>
		<p><%=Questionslist.get(i).getId()%></p>
		<p>
			問題：
			<textarea name="question" readonly rows="4" cols="40"><%=Questionslist.get(i).getQuestion()%></textarea>
		</p>
		<input type="hidden" name="questions_id"
			value="<%=Questionslist.get(i).getId()%>">
		<%
		}
		%>
		<p>
			答え：<input type="text" name="answer">
		</p>
		<%
		}
		%>
	</div>
</body>
</html>