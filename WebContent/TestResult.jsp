<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト採点結果</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h2>テスト結果</h2>
	<div>
		<p>
			<%=request.getAttribute("name")%>さん
		</p>
		<p>
			<%=request.getAttribute("qCount")%>問中<%=request.getAttribute("count")%>問正解です。
		</p>
		<p>
			<%=request.getAttribute("result")%>点でした。
		</p>
		<p>
			<%
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
			out.print("<h4>" + ft.format(dNow) + "</h4>");
			%>
		</p>
	</div>
</body>
</html>