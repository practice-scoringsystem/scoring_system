<%-- <%@ page import="java.util.Date, java.text.DateFormat" %> --%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.HistoriesBean"
import="beans.UsersBean"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- エラーメッセージを受け取る -->
<%
String error = (String) request.getAttribute("error_message");
%>

<%-- リクエストスコープからBeanクラスの配列を取得 --%>
<%
List<HistoriesBean> Historieslist = (List<HistoriesBean>) request.getAttribute("list");
%>
<%
List<UsersBean> ulist = (List<UsersBean>) request.getAttribute("ulist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>採点履歴</title>
</head>
<body>
	<%
	if (error != null) {
	%>
	<%=error%>
	<%
	}
	%>
	<jsp:include page="Header.jsp" />
	<h2 style="text-align: center">履歴</h2>
	<table style="margin: 0 auto">
		<tr>
			<th style="background-color: #6666FF; width: 100">氏名</th>
			<th style="background-color: #6666FF; width: 100">得点</th>
			<th style="background-color: #6666FF; width: 250">採点時間</th>
		</tr>
		<%
		for (int i = 0; i < Historieslist.size(); i++) {
		%>
		<%
		if (Historieslist.get(i) != null) {
		%>
		<%
		for (int j = 0; j < ulist.size(); j++) {
		%>
		<%
		if (ulist.get(j) != null) {
		%>
		<%
		if (ulist.get(j).getId() == Historieslist.get(i).getUserId()) {
		%>
		<td><%=ulist.get(j).getName()%></td>
		<%-- ポイント --%>
		<td><%=Historieslist.get(i).getPoint()%></td>
		<!-- 採点時間 -->
		<td><%=Historieslist.get(i).getCreatedAt()%></td>
		<tr>

			<%
			}
			%>
			<%
			}
			%>
			<%
			}
			%>
			<%
			}
			%>
			<%
			}
			%>

	</table>

</body>
</html>