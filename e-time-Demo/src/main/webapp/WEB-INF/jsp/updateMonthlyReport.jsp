<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Clock_in_DTO, java.sql.Date" %>
<%
	String user_id = (String) session.getAttribute("user_id");
	Clock_in_DTO clock_in_DTO = (Clock_in_DTO)request.getAttribute("clock_in_DTO");
	String str_start_time = clock_in_DTO.getStart_time().toString().substring(0, 2) + clock_in_DTO.getStart_time().toString().substring(3, 5);
	String str_end_time = clock_in_DTO.getEnd_time().toString().substring(0, 2) + clock_in_DTO.getEnd_time().toString().substring(3, 5);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>勤怠入力画面</h2>
<h2><c:out value="${month }" />月<c:out value="${day }" />日　<c:out value="${day_of_week }" /></h2><br>
<c:if test="${not empty start_time_errorMsg }" >
	<p><c:out value="${start_time_errorMsg }" /></p>
</c:if>
<c:if test="${not empty end_time_errorMsg }" >
	<p><c:out value="${end_time_errorMsg }" /></p>
</c:if>
<c:if test="${not empty break_time_errorMsg }" >
	<p><c:out value="${break_time_errorMsg }" /></p><br>
</c:if>
<form action="MonthlyReportUpdateServlet" method="POST">
<input type="hidden" name="year" value="${year }">
<input type="hidden" name="month" value="${month }">
<input type="hidden" name="day" value="${day }">
<input type="hidden" name="day_of_week" value="${day_of_week }">
<input type="hidden" name="user_id" value="<%= user_id %>">
<input type="hidden" name="clock_in_date_branch" value="${clock_in_DTO.clock_in_date_branch }">
枝番：<select name="branch" required>
<c:choose>
	<c:when test="${clock_in_DTO.clock_in_no == '01' }">
		<option value="01" selected>①</option>
		<option value="02">②</option>
		<option value="03">③</option>
	</c:when>
	<c:when test="${clock_in_DTO.clock_in_no == '02' }">
		<option value="01">①</option>
		<option value="02" selected>②</option>
		<option value="03">③</option>		
	</c:when>
	<c:when test="${clock_in_DTO.clock_in_no == '03' }">
		<option value="01">①</option>
		<option value="02">②</option>
		<option value="03" selected>③</option>		
	</c:when>
	<c:otherwise>
		<option value="01">①</option>
		<option value="02">②</option>
		<option value="03">③</option>
	</c:otherwise>
</c:choose>
</select><br>
勤務パターン：<select name="work_pattern" style="width:200px" required>
<c:choose>
	<c:when test="${clock_in_DTO.work_pattern == 'normal' }">
		<option value="normal" selected>通常勤務</option>
		<option value="stated">大阪支店</option>
	</c:when>
	<c:when test="${clock_in_DTO.work_pattern == 'stated' }">
		<option value="normal">通常勤務</option>
		<option value="stated" selected>大阪支店</option>
	</c:when>
	<c:otherwise>
		<option value="normal">通常勤務</option>
		<option value="stated">大阪支店</option>
	</c:otherwise>
</c:choose>
</select><br>
就業区分：<select name="work_category" style="width:100px" required>
<c:choose>
	<c:when test="${clock_in_DTO.work_category == 'office' }">
		<option value="office" selected>出社</option>
		<option value="home">在宅</option>
		<option value="other">その他</option>
	</c:when>
	<c:when test="${clock_in_DTO.work_category == 'home' }">
		<option value="office">出社</option>
		<option value="home" selected>在宅</option>
		<option value="other">その他</option>
	</c:when>
	<c:when test="${clock_in_DTO.work_category == 'other' }">
		<option value="office">出社</option>
		<option value="home">在宅</option>
		<option value="other" selected>その他</option>
	</c:when>
	<c:otherwise>
		<option value="office">出社</option>
		<option value="home">在宅</option>
		<option value="other">その他</option>
	</c:otherwise>
</c:choose>
</select><br>
開始時間：
<c:choose>
	<c:when test="${not empty clock_in_DTO.start_time }">
		<input type="text" name="start_time" value="<%=str_start_time %>" required> (例：0900)<br>
	</c:when>
	<c:otherwise>
		<input type="text" name="start_time" required> (例：0900)<br>
	</c:otherwise>
</c:choose>
終了時間：
<c:choose>
	<c:when test="${not empty clock_in_DTO.end_time }">
		<input type="text" name="end_time" value="<%= str_end_time %>" required> (例：1830)<br>
	</c:when>
	<c:otherwise>
		<input type="text" name="end_time" required> (例：1830)<br>
	</c:otherwise>
</c:choose>
休憩時間：
<c:choose>
	<c:when test="${not empty clock_in_DTO.break_time }">
		<input type="text" name="break_time" value="${clock_in_DTO.break_time }" required> (分)(例：60)<br>
	</c:when>
	<c:otherwise>
		<input type="text" name="break_time" required> (分)(例：60)<br>
	</c:otherwise>
</c:choose>
勤怠備考：
<c:choose>
	<c:when test="${not empty clock_in_DTO.work_remarks }">
		<input type="text" name="work_reamrks" value="${clock_in_DTO.work_remarks }" required><br>
	</c:when>
	<c:otherwise>
		<input type="text" name="work_remarks" required><br>
	</c:otherwise>
</c:choose>
出勤区分：<select name="work_on_a_day" required>
<c:choose>
	<c:when test="${clock_in_DTO.work_on_a_day == 0 }">
		<option value="0" selected>出勤</option>
		<option value="1">休日出勤</option>
	</c:when>
	<c:when test="${clock_in_DTO.work_on_a_day == 1 }">
		<option value="0">出勤</option>
		<option value="1" selected>休日出勤</option>
	</c:when>
	<c:otherwise>
		<option value="0">出勤</option>
		<option value="1">休日出勤</option>
	</c:otherwise>
</c:choose>
</select><br>
<button type="submit" value="applicate">申請</button><br>
</form>
<a href="CalendarServlet">カレンダーへ戻る</a>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>