<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.QuestionsBean"%>
<%
// サーブレットから詳細の情報を取得
QuestionsBean questionsbean = (QuestionsBean) request.getAttribute("questionsbean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題・答え編集</title>
</head>
<body>

	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>問題・答え編集</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./EditConfirm" method="post">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<p>
			問題：
			<textarea name="student_number" rows="4" cols="40"><%=questionsbean.getQuestion()%></textarea>
		</p>
		<!-- フォームが増える トリガーを設置してjsを動かす -->
		<p>
			<!-- nameでservletへ送る -->
			答え1：<input type="text" name="student_number"
				value="<%=questionsbean.getAnswersId()%>" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>
			<br> 答え2：<input type="text" name="answer" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>
		</p>
		<a href="./List.jsp">
			<button type="button">戻る</button>
		</a>
		<!-- 入力したものを確認する　confirmでsubmit -->
		<p>
			<input type="submit" value="編集内容を確認する">
		</p>
		<button type="button" name="addForm" value="addForm">
			<font size="2">フォームを追加</font>
		</button>
	</form>

</body>
</html>