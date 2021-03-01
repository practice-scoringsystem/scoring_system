<%@ page import="java.util.ArrayList" import="java.util.List"%>
<%@ page import="beans.HistoriesBean"%>
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
List<HistoriesBean> ulist = (List<HistoriesBean>) request.getAttribute("ulist");
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
	<h2>履歴</h2>
	<table style="margin: 0 auto">
		<tr>
			<th style="background-color: #6666FF; width: 100">氏名</th>
			<th style="background-color: #6666FF; width: 100">得点</th>
			<th style="background-color: #6666FF; width: 250">採点時間</th>
		</tr>

		<tr>
			<%
			for (int j = 0; j < Historieslist.size(); j++) {
			%>
			<%
			for (int i = 0; i < ulist.size(); i++) {
			%>
			<%
			if (ulist.get(i) != null) {
			%>
			<td><%=ulist.get(i).getName()%></td>
			<%
			}
			%>

			<%-- ユーザーID --%>
			<%
			if (Historieslist.get(j) != null) {
			%>
			<%-- ポイント --%>
			<td><%=Historieslist.get(j).getPoint()%></td>
			<!-- 採点時間 -->
			<td><%=Historieslist.get(j).getCreatedAt()%></td>
			<%
			}
			%>
			<%
			}
			%>
			<%
			}
			%>
		</tr>
	</table>

</body>
</html>