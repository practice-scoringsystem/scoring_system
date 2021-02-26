<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.CorrectAnswersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>削除確認</h2>
	<form action="./Update" method="post">
		<input type="hidden" name="questions_id"
			value="<%=String.valueOf(request.getAttribute("questions_id"))%>" />
		<p>
			<%=String.valueOf(request.getAttribute("questions_id"))%>
			問題:
			<textarea readonly rows="4" cols="40"><%=request.getAttribute("question")%></textarea>
			<input type="hidden" name="question"
				value="<%=request.getAttribute("question")%>" />
		</p>

		<%
		String[] arr = (String[]) request.getAttribute("answer");
		for (int j = 0; j < arr.length; j++) {
		%>
		<%
			int [] answers_ids = (int[])request.getAttribute("answers_ids");
		%>

		<p>
			答え:<input type="text" name="answer" readonly value="<%=arr[j]%>">
			<input type="hidden" name="answers_id" value="<%=answers_ids[j]%>" />
		</p>
		<%
		}
		%>

		<a href="./Edit">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="登録する">
		</p>
	</form>
</body>
</html>