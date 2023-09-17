<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Calendar calendar = (Calendar)request.getAttribute("calendar");
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠一覧</title>
</head>
<body>
<a href="HomeServlet">ホーム画面へ戻る</a>
<h1>勤怠カレンダー</h1>
<h2><%=year %>年<%=month %>月</h2>
<form action="CalendarServlet" method="POST">
<input type="hidden" name="change" value="last">
<input type="hidden" name="year" value="<%=year %>">
<input type="hidden" name="month" value="<%=month %>">
<input type="submit" value="前月">
</form>
<form action="CalendarServlet" method="POST">
<input type="hidden" name="change" value="next">
<input type="hidden" name="year" value="<%=year %>">
<input type="hidden" name="month" value="<%=month %>">
<input type="submit" value="翌月">
</form>
<c:choose>
	<c:when test="${not empty calendarErrorMsg }">
		<p><c:out value="${calendarErrorMsg }" /></p>
	</c:when>
	<c:otherwise>
		<table border="1">
			<tr>
				<th>日付</th>
				<th>枝番</th>
				<th>勤務パターン</th>
				<th>勤怠備考</th>
				<th>就業区分</th>
				<th>業務開始時間</th>
				<th>業務終了時間</th>
				<th>休憩時間</th>
				<th>残業時間</th>
				<th>休日出勤</th>
				<th>編集</th>
			</tr>
			<c:forEach var="calendar" items="${calendarList }" >
				<tr><c:choose>
						<c:when test="${not empty calendar.clock_in_date }">
							<td><c:out value="${calendar.clock_in_date }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.clock_in_no }">
							<td><c:out value="${calendar.clock_in_no }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.change_work_pattern }">
							<td><c:out value="${calendar.change_work_pattern }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.work_remarks }">
							<td><c:out value="${calendar.work_remarks }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.change_work_category }">
							<td><c:out value="${calendar.change_work_category }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.start_time }">
							<td><c:out value="${calendar.start_time }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.end_time }">
							<td><c:out value="${calendar.end_time }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.break_time }">
							<c:choose>
								<c:when test="${not empty calendar.start_time }">
									<td><c:out value="${calendar.break_time }" /></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.over_time }">
							<c:choose>
								<c:when test="${not empty calendar.start_time }">
									<td><c:out value="${calendar.over_time }" /></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.str_work_on_a_day }">
							<td><c:out value="${calendar.str_work_on_a_day }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<td><form action="MonthlyReportUpdateServlet" method="GET">
						<input type="hidden" name="clock_in_date_branch" value="${calendar.clock_in_date_branch }" >
						<input type="submit" value="編集">
						</form>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose><br>
<p>出勤日数：<c:out value="${workingDay }" /></p>
<p>合計労働時間：<c:out value="${totalWorkTime }" /></p>
<p>合計残業時間：<c:out value="${totalOverTime }" /></p>
<p>休日出勤回数：<c:out value="${work_on_a_Day }" /></p>
<p>休日出勤労働時間：<c:out value="${totalWork_on_a_day_Hour }" /></p>
<p>休日出勤残業時間：<c:out value="${totalWork_on_a_day_OverTime }" /></p>
<form action="ReportApplyServlet" method="GET">
<input type="hidden" name="year" value="<%=year %>">
<input type="hidden" name="month" value="<%=month %>">
<input type="hidden" name="workingDay" value="${workingDay }">
<input type="hidden" name="totalWorkTime" value="${totalWorkTime }">
<input type="hidden" name="totalOverTime" value="${totalOverTime }">
<input type="hidden" name="work_on_a_Day" value="${work_on_a_Day }">
<input type="hidden" name="totalWork_on_a_day_Hour" value="${totalWork_on_a_day_Hour }">
<input type="hidden" name="totalWork_on_a_day_OverTime" value="${totalWork_on_a_day_OverTime }">
<input type="submit" value="勤怠申請">
<p>※一度申請すると個人では修正出来ません。修正が必要な場合は管理部へお問い合わせください</p>
</form>
</body>
</html>