<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.QuestionsBean" import="beans.CorrectAnswersBean"%>
<%
// サーブレットから詳細の情報を取得 キーで取り出す
QuestionsBean questionsbean = (QuestionsBean) request.getAttribute("questionsBean");
%>
<%
List<CorrectAnswersBean> CAlist = (List<CorrectAnswersBean>) request.getAttribute("CAlist");
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
		<input type="hidden" name="questions_id" value="<%=String.valueOf(request.getAttribute("questions_id"))%>" />
		<p>
			問題：
			<textarea name="question" rows="4" cols="40"><%=questionsbean.getQuestion()%></textarea>
		</p>
		<!-- フォームが増える トリガーを設置してjsを動かす -->
		<!-- nameでservletへ送る -->

		<%
		for (int i = 0; i < CAlist.size(); i++) {
		%>
		<%-- 問題ID --%>
		<%
		if (CAlist.get(i) != null) {
		%>

		<p>
			答え:<input type="text" name="answer"
				value="<%=CAlist.get(i).getAnswer()%>">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>
		</p>
		<%
		}
		%>
		<%
		}
		%>
		<a href="./List">
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