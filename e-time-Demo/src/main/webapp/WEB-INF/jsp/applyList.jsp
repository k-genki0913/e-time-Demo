<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>承認待ち一覧</title>
</head>
<body>
<h2>承認待ち月報一覧</h2>
<c:if test="${not empty errorMsg }" >
	<p><c:out value="${errorMsg }" /></p>
</c:if>
<table border="1">
	<tr>
		<th>月報ID</th>
		<th>年</th>
		<th>月</th>
		<th>申請者ユーザーID</th>
		<th>申請者名</th>
	</tr>
	<c:forEach var="monthly_report" items="${applyList }">
		<tr>
			<td><c:out value="${monthly_report.monthlyReport_id }" /></td>	
			<td><c:out value="${monthly_report.target_year }" /></td>
			<td><c:out value="${monthly_report.target_month }" /></td>
			<td><c:out value="${monthly_report.user_id }" /></td>
			<td><c:out value="${monthly_report.name }" /></td>
			<td><form action="MonthlyReportApproveServlet" method="GET">
				<input type="hidden" name="monthly_report_id" value="${monthly_report.monthlyReport_id}" >
				<input type="submit" value="承認画面へ">
				</form>
		</tr>
	</c:forEach>
</table>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>