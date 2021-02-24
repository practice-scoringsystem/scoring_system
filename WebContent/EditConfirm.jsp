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
	<input type="hidden" name="questions_id" value="<%=String.valueOf(request.getAttribute("questions_id"))%>" />
		<p>
		<!-- 値が取れてるか確認中 -->
		<%=String.valueOf(request.getAttribute("questions_id"))%>
			問題:
			<textarea readonly rows="4" cols="40"><%=request.getAttribute("question")%></textarea>
			<input type="hidden" name="question" value="<%=request.getAttribute("question")%>" />
		</p>
		<%
		List<CorrectAnswersBean> CAlist = (List<CorrectAnswersBean>) request.getAttribute("CAlist");
		for (int i = 0; i < CAlist.size(); i++) {
		%>
		<%
		String[] arr = (String[]) request.getAttribute("answer");
		for (int j = 0; j < arr.length; j++) {
		%>
		<p>
			答え:<input type="text" name="answer" readonly value="<%=arr[j]%>">
			<input type="hidden" name="answers_id" value="<%=CAlist.get(i).getId()%>" />
		</p>
		<%
		}
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