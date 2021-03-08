<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Topページ</title>
<style>
nav.nav {
	text-align: right;
}
</style>
</head>

<body>
	<header>
		<h3>ようこそテスト問題登録/自動採点システムへ</h3>
		<nav class="nav">
			<form action="./Logout" method="post">
				<input type="hidden" name="action" value="logout" /> <input
					type="submit" value="logout" />
			</form>
		</nav>
	</header>
	<h4>下記のメニューボタンから選択してください</h4>
	<a href="./List">
		<button type="button">問題と答えを確認・登録する ＞</button>
	</a>
	<br>
	<form action="./TestList" method="post">
		<input type="submit" value="テストをする ＞" />
	</form>
	<br>
	<form action="./Histories" method="post">
		<input type="submit" value="過去の採点結果を見る ＞" />
	</form>

</body>
</html>