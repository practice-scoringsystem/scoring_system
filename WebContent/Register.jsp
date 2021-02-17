<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題・答え 新規登録</title>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>問題・答え登録</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./RegisterConfirm" method="post">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<p>
			問題：
			<textarea name="content" rows="4" cols="40"></textarea>
		</p>
		<!-- フォームが増える トリガーを設置してjsを動かす -->
		<p>
			<!-- nameでservletへ送る -->
			答え1：<input type="text" name="answer1" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>
			<br> 答え2：<input type="text" name="answer2" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>
		</p>
		<a href="./Top.jsp">
			<button type="button">戻る</button>
		</a>
		<!-- 入力したものを確認する　confirmでsubmit -->
		<p>
			<input type="submit" value="確認">
		</p>
		<button type="button" name="addForm" value="addForm">
			<font size="2">フォームを追加</font>
		</button>
	</form>
</body>
</html>