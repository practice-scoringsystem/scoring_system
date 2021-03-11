<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.UsersBean"%>
<%
// サーブレットから詳細の情報を取得 キーで取り出す
UsersBean ub = (UsersBean) request.getAttribute("ub");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー編集</title>
</head>
<body>

	<%
	String errorMessage = (String) request.getAttribute("error_message");
	%>
	<h2>ユーザー編集</h2>
	<!-- ここにインクルードでtopとログアウトボタンを設置 -->
	<jsp:include page="Header.jsp" />
	<form action="./UserEditConfirm" method="post" name="form">
		<%
		if (errorMessage != null) {
		%>
		<%=errorMessage%>
		<%
		}
		%>
		<div>
			<p>
				ID(編集不可):<input type="text" readonly name="user_id" value="<%=ub.getId()%>" />
			</p>
			<p>
				ユーザー名(編集不可):<input type="text" readonly name="name" value="<%=ub.getName()%>" />
			</p>
			<p>
				PW:<input type="password" name="password"
					value="<%=ub.getPassword()%>" required/>
			</p>
			<p>
				PW確認:<input type="password" name="passwordConfirm"
					value="<%=ub.getPassword()%>" required/>
			</p>
			<%
			if (String.valueOf(ub.getAdminFlag()).equals("1")) {
			%>
			<p>
				管理者:<input type="checkbox" name="adminCheck" value="1" checked="checked">
			</p>
			<input type="hidden" name="adminCheck" value="0">
			<%
			} else {
			%>
			<p>
				管理者:<input type="checkbox" name="adminCheck" value="1">
			</p>
			<input type="hidden" name="adminCheck" value="0">
			<%
			}
			%>
		</div>
		<a href="./UsersList">
			<button type="button">戻る</button>
		</a>
		<!-- 入力したものを確認する　confirmでsubmit -->
		<p>
			<input type="submit" value="編集内容を確認する" onClick="return check();">
		</p>
	</form>

	<script type="text/javascript">
		function check() {
			if (form.password.value == "") {
				alert("パスワードを入力してください");
				return false;
			} else if (!form.password.value.match(/^([a-zA-Z0-9]{8,})$/)) {
				alert("パスワードは半角英数字で8文字以上に設定してください");
				return false;
			} else if (form.passwordConfirm.value == "") {
				alert("確認用のパスワードを入力してください");
				return false;
			} else if (!form.passwordConfirm.value.match(/^([a-zA-Z0-9]{8,})$/)) {
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