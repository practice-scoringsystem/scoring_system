<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String login_name = (String)session.getAttribute("login_name");
	int login_id = (int)session.getAttribute("login_id");
   session.setAttribute("login_name", login_name);
   session.setAttribute("login_id", login_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト採点結果</title>
</head>
<body>

</body>
</html>