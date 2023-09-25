<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>承認画面</title>
</head>
<body>
<h2>月報承認画面</h2>
<p>ユーザーID：<c:out value="${monthly_report_DTO.user_id }" /></p>
<p>氏名：<c:out value="${monthly_report_DTO.name }" /></p>
<p>承認依頼：<c:out value="${monthly_report_DTO.target_year }" />年<c:out value="${monthly_report_DTO.target_month }" />月</p>
<p>勤務日数：<c:out value="${monthly_report_DTO.workingDay }" /></p>
<p>合計労働時間：<c:out value="${monthly_report_DTO.total_working_hours }" /></p>
<p>残業時間：<c:out value="${monthly_report_DTO.totalovertime_hours }" /></p>
<p>休日出勤日数：<c:out value="${monthly_report_DTO.work_on_a_day }" /></p>
<p>休日出勤労働時間：<c:out value="${monthly_report_DTO.total_work_on_a_day_Hour }" /></p>
<p>休日出勤残業時間：<c:out value="${monthly_report_DTO.total_work_on_a_day_OverTime }" /></p>
<form action="MonthlyReportApproveServlet" method="POST">
<input type="hidden" name="monthly_report_id" value="${monthly_report_DTO.monthlyReport_id }" />
<button type="submit" name="judgement" value="approve">承認</button>
<button type="submit" name="judgement" value="denial">否認</button>
</form>
</body>
</html>