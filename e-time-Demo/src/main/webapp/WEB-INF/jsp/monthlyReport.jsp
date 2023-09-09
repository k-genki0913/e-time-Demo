<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar, model.Cnvrt_DayOfWeek" %>
<%
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	Cnvrt_DayOfWeek cnvrt_DayOfWeek = new Cnvrt_DayOfWeek();
	String day_of_week = cnvrt_DayOfWeek.putBack(calendar.get(Calendar.DAY_OF_WEEK));
	String user_id = (String)session.getAttribute("user_id");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠入力画面</title>
</head>
<body>
<h2>勤怠入力画面</h2>
<h2><%= month %>月<%= day %>日　<%= day_of_week %></h2><br>
<c:if test="${not empty start_time_errorMsg }" >
	<p><c:out value="${start_time_errorMsg }" /></p>
</c:if>
<c:if test="${not empty end_time_errorMsg }" >
	<p><c:out value="${end_time_errorMsg }" /></p>
</c:if>
<c:if test="${not empty break_time_errorMsg }" >
	<p><c:out value="${break_time_errorMsg }" /></p><br>
</c:if>
<form action="MonthlyReportServlet" method="POST">
<input type="hidden" name="year" value="<%= year %>">
<input type="hidden" name="month" value="<%= month %>">
<input type="hidden" name="day" value="<%= day %>">
<input type="hidden" name="day_of_week" value="<%= day_of_week %>">
<input type="hidden" name="user_id" value="<%= user_id %>">
枝番：<select name="branch" required>
<option value="01">①</option>
<option value="02">②</option>
<option value="03">③</option>
</select><br>
勤務パターン：<select name="work_pattern" style="width:200px" required>
<option value="normal">通常勤務</option>
<option value="stated">大阪支店</option>
</select><br>
就業区分：<select name="work_category" stype="width:100px" required>
<option value="office">出社</option>
<option value="home">在宅</option>
<option value="other">その他</option>
</select><br>
開始時間：<input type="text" name="start_time" required> (例：0900)<br>
終了時間：<input type="text" name="end_time" required> (例：1830)<br>
休憩時間：<input type="text" name="break_time" required>（分) (例 60)<br>
勤怠備考：<input type="text" name="work_remarks"><br>
出勤区分：<select name="work_on_a_day" required>
<option value="0">出勤</option>
<option value="1">休日出勤</option>
</select>
<br>
<button type="submit" value="applicate">申請</button><br>
</form>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>