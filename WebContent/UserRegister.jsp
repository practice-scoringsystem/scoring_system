<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>

	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>ユーザー登録</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./UserRegisterConfirm" method="post" name="form">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<div>
			ユーザー名(半角英数字)：<input type="text" name="name" size="40" required /><br>
			PW(半角英数字8文字以上)：<input type="password" name="password" size="40" required /><br>
			PW確認(半角英数字8文字以上)：<input type="password" name="confirmPassword" size="40" required /><br>
			管理者：<input type="checkbox" name="adminCheck" value="1">
			<input type="hidden" name="adminCheck" value="0">
		</div>
		<div>
			<a href="./UsersList">
				<button type="button">戻る</button>
			</a>
			<p>
				<input type="submit" value="確認" onClick="return check();" />
			</p>
		</div>
	</form>

	<script type="text/javascript">
		function check() {
			if (form.name.value == "") {
				//条件に一致する場合
				alert("名前を入力してください"); //エラーメッセージを出力
				return false; //送信ボタン本来の動作をキャンセルします
			} else if (!form.name.value.match(/^[A-Za-z0-9]*$/)) {
				alert("名前は半角英数字のみです"); //エラーメッセージを出力
				return false; //送信ボタン本来の動作をキャンセルします
			} else if (form.password.value == "") {
				alert("パスワードを入力してください");
				return false;
			} else if (!form.password.value.match(/^([a-zA-Z0-9]{8,})$/)) {
				alert("パスワードは半角英数字で8文字以上に設定してください");
				return false;
			} else if (form.confirmPassword.value == "") {
				alert("確認用のパスワードを入力してください");
				return false;
			} else if (!form.confirmPassword.value.match(/^([a-zA-Z0-9]{8,})$/)) {
				alert("パスワードは半角英数字で8文字以上に設定してください");
				return false;
			} else {
				//条件に一致しない場合(入力されている場合)
				return true; //送信ボタン本来の動作を実行します
			}
		}
	</script>

</body>
</html>