<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.CorrectAnswersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>編集確認</h2>
	<form action="./Update" method="post">
		<input type="hidden" name="questions_id"
			value="<%=String.valueOf(request.getAttribute("questions_id"))%>" />
		<p>
			<%=String.valueOf(request.getAttribute("questions_id"))%><br>
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

		<a href="Edit?questions_id=<%=String.valueOf(request.getAttribute("questions_id"))%>">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="更新">
		</p>
	</form>
</body>
</html>