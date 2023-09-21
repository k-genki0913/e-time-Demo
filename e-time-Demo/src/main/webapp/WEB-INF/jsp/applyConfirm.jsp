<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty errorMsg }" >
	<p><c:out value="${errorMsg }" /></p>
</c:if>
<h2>申請内容</h2>
<p><c:out value="${monthly_report_DTO.target_year }" />年<c:out value="${monthly_report_DTO.target_month }" />月</p>
<p>出勤日数：<c:out value="${monthly_report_DTO.workingDay }" />日</p>
<p>合計労働時間：<c:out value="${monthly_report_DTO.total_working_hours }" />時間</p>
<p>合計残業時間：<c:out value="${monthly_report_DTO.total_overtime_hours }" />時間</p>
<p>休日出勤日数：<c:out value="${monthly_report_DTO.work_on_a_day }" />日</p>
<p>休日出勤労働時間：<c:out value="${monthly_report_DTO.total_work_on_a_day_Hour }" />時間</p>
<p>休日出勤残業時間：<c:out value="${monthly_report_DTO.total_work_on_a_day_OverTime }" />時間</p>

<h2>承認者確認・選択画面</h2>
<form action="ReportApplyServlet" method="POST">
<c:if test="${not empty smg_user_id }">
	<p>【サブマネージャー承認者】</p>
	<p>※承認者をクリックしてください</p>
	<table border="1">
		<tr>
			<th>ユーザーID</th>
			<th>氏名</th>
		</tr>
		<c:forEach var="user_id" items="${smg_user_id }">
		<tr>
			<td><c:out value="${user_id.key }" /></td>
			<td><input type="radio" name="smg_user_id" value="${user_id.key }" checked><c:out value="${user_id.value }" /></td>
		</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${not empty mg_user_id }" >
	<p>【マネージャー承認者】</p>
	<p>※承認者をクリックしてください</p>
	<table border="1">
		<tr>
			<th>ユーザーID</th>
			<th>氏名</th>
		</tr>
		<c:forEach var="user_id" items="${mg_user_id }">
		<tr>
			<td><c:out value="${user_id.key }" /></td>
			<td><input type="radio" name="mg_user_id" value="${user_id.key }" checked><c:out value="${user_id.value }" /></td>
		</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${not empty gm_user_id }" >
	<p>【支店長承認者】</p>
	<p>※承認者をクリックしてください</p>
	<table border="1">
		<tr>
			<th>ユーザーID</th>
			<th>氏名</th>
		</tr>
		<c:forEach var="user_id" items="${gm_user_id }">
		<tr>
			<td><c:out value="${user_id.key }" /></td>
			<td><input type="radio" name="gm_user_id" value="${user_id.key }" checked><c:out value="${user_id.value }" /></td>
		</tr>
		</c:forEach>
	</table>
</c:if>
<input type="hidden" name="applyRole" value="${applyRole }">
<input type="hidden" name="year" value="${monthly_report_DTO.target_year }">
<input type="hidden" name="month" value="${monthly_report_DTO.target_month }">
<input type="hidden" name="workingDay" value="${monthly_report_DTO.workingDay }">
<input type="hidden" name="totalWorkTime" value="${monthly_report_DTO.total_working_hours }">
<input type="hidden" name="totalOverTime" value="${monthly_report_DTO.total_overtime_hours }">
<input type="hidden" name="work_on_a_Day" value="${monthly_report_DTO.work_on_a_day }" >
<input type="hidden" name="totalWork_on_a_day_Hour" value="${monthly_report_DTO.total_work_on_a_day_Hour }" >
<input type="hidden" name="totalWork_on_a_day_OverTime" value="${monthly_report_DTO.total_work_on_a_day_OverTime }">
<input type="submit" value="申請">
<a href="HomeServlet">ホーム画面へ戻る</a>

</form>
</body>
</html>