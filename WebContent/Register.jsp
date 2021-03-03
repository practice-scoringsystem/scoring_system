<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題・答え 新規登録</title>
</head>
<body>
	<!-- <script type="text/javascript">
		var i = 1;
		function addForm() {
			//インプットフィールドを作成している
			var input_data = document.createElement('input');
			input_data.type = 'text';
			input_data.id = 'inputform_' + i;
			var parent = document.getElementById('form_area');
			var rdo_name = document.getElementsByName('answer');
			parent.appendChild(input_data);
			i++;
		}
	</script> -->
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
			<textarea name="question" rows="4" cols="40"></textarea>
		</p>
		<!-- フォームが増える トリガーを設置してjsを動かす -->
		<div>
			<!-- nameでservletへ送る -->
			答え1：<input type="text" name="answer" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>

			<br> 答え2：<input type="text" name="answer" size="40">
			<button type="button" name="delete" value="delete">
				<font size="2">削除</font>
			</button>

			<!-- js用 -->
			<!-- <div id="form_area">
				答え:<input type="text" name="answer" >
			</div> -->
		</div>
		<a href="./List">
			<button type="button">戻る</button>
		</a>
		<!-- 入力したものを確認する　confirmでsubmit -->
		<p>
			<input type="submit" value="確認">
		</p>

		<!-- js用 -->
		<!-- <input type="button" value="フォームを追加" onclick="addForm()"> -->

	</form>
</body>
</html>