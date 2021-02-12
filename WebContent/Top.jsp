<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Topページ</title>
</head>

<body>

	<header>
		<h3>ようこそテスト問題登録/自動採点システムへ</h3>
		<nav class="nav">
			<form action="./Logout" method="post">
			<input type="hidden" name="action" value="logout">
				<input type="submit" value="logout">
			</form>
		</nav>
	</header>
	<h4>下記のメニューボタンから選択してください</h4>
	<button type="button" name="問題と答えを確認・登録する　＞" value="問題と答えを確認・登録する　＞">
		<font size="2">問題と答えを確認・登録する ＞</font>
	</button>
	<br>
	<button type="button" name="テストをする　＞" value="テストをする　＞">
		<font size="2">テストをする ＞</font>
	</button>
	<br>
	<button type="button" name="過去の採点結果をみる　＞" value="過去の採点結果をみる　＞">
		<font size="2">過去の採点結果をみる ＞</font>
	</button>

</body>
</html>