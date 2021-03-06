<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.QuestionsCorrectAnswersBean"
	import="beans.QuestionsBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認</title>
</head>
<body>

	<jsp:include page="Header.jsp" />
	<h2>以下の内容を削除してもよろしいですか？</h2>
	<form action="./Delete" method="post">
		<input type="hidden" name="questions_id"
			value="<%=String.valueOf(request.getAttribute("questions_id"))%>" />
		<%
			// サーブレットから詳細の情報を取得 キーで取り出す
			QuestionsBean questionsbean = (QuestionsBean) request.getAttribute("questionsBean");
		%>
		<p>

			<tr>
				問題:
				<textarea readonly rows="4" cols="40"><%=questionsbean.getQuestion()%></textarea>
				<input type="hidden" name="question" value="<%=request.getAttribute("question")%>" />
		</p>

		<%
		List<QuestionsCorrectAnswersBean> arr = (List<QuestionsCorrectAnswersBean>) request.getAttribute("CAlist");
		for (int i = 0; i < arr.size(); i++) {
		%>
		<!-- answers_idsはいらない -->
		<%
		int[] answers_ids = (int[]) request.getAttribute("answers_ids");
		%>

		<p>
			答え:<input type="text" name="answer" readonly value="<%=arr.get(i).getAnswer()%>">
				<input type="hidden" name="answers_id" value="<%=arr.get(i).getId()%>" />
		</p>
		<%
		}
		%>

		<a href="./List">
			<button type="button">戻る</button>
		</a>
		<p>
			<input type="submit" value="削除する">
		</p>
	</form>

</body>
</html>