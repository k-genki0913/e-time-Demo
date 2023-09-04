<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠一覧</title>
</head>
<body>
<a href="HomeServlet">ホーム画面へ戻る</a>
<h1>勤怠カレンダー</h1>
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
						<c:when test="${not empty calendar.work_pattern }">
							<td><c:out value="${calendar.work_pattern }" /></td>
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
						<c:when test="${not empty calendar.work_category }">
							<td><c:out value="${calendar.work_category }" /></td>
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
							<td><c:out value="${calendar.break_time }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.over_time }">
							<td><c:out value="${calendar.over_time }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty calendar.work_on_a_day }">
							<td><c:out value="${calendar.work_on_a_day }" /></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>