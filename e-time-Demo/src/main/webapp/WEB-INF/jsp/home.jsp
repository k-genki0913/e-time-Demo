<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Users_DTO, dao.Users_DAO" %>
<%
	String user_id = (String) session.getAttribute("user_id");
	Users_DTO users_DTO = new Users_DAO().getUser(user_id);
	Integer admin = users_DTO.getAdmin();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>ホーム画面</p>
<% if(admin == 1) { %>
	<a href="AdminServlet">管理者画面</a>
<% } %>
<a href="CreateCalendarServlet">カレンダー作成</a>
<a href="MonthlyReportServlet">勤怠入力画面</a>
<a href="CalendarServlet">カレンダー</a>
<a href="ApplyListServlet">勤怠承認画面</a>
<a href="LogoutServlet">ログアウト</a>
</body>
</html>