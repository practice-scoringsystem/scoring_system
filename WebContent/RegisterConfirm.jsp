<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>登録確認</h2>
	<p>
		問題:<%=request.getAttribute("content")%>
		<input type="hidden" name="content" />
	</p>
	<p>
		答え1:<%=request.getAttribute("answer1")%>
		<input type="hidden" name="answer1" />
	</p>
	<p>
		答え2:<%=request.getAttribute("answer2")%>
		<input type="hidden" name="answer2" />
	</p>
	<button type="button" name="編集" value="編集">
		<font size="2">戻る</font>
	</button>
	<button type="button" name="登録" value="登録">
		<font size="2">登録</font>
	</button>

</body>
</html>