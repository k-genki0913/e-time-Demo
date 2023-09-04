<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>契約一覧</title>
</head>
<body>
<a href="HomeServlet">ホーム画面へ戻る</a>
<h1>契約一覧</h1>
<c:choose>
	<c:when test="${not empty constractListErrorMsg }">
		<p><c:out value="${constractListErrorMsg }" /></p>
	</c:when>
	<c:otherwise>
		<table border="1">
			<tr>
				<th>契約ID</th>
				<th>勤務先</th>
				<th>契約開始日</th>
				<th>契約終了日</th>
				<th>ユーザーID</th>
				<th>氏名</th>
				<th>勤務開始時間</th>
				<th>勤務終了時間</th>
				<th>定時 業務時間(分)</th>
				<th>休憩時間(分)</th>
			</tr>
			<c:forEach var="constract" items="${constractList }" >
				<tr>
					<td><c:out value="${constract.constract_id }" /></td>
					<td><c:out value="${constract.workplace }" /></td>
					<td><c:out value="${constract.constract_start }" /></td>
					<td><c:out value="${constract.constract_end }" /></td>
					<td><c:out value="${constract.user_id }" /></td>
					<td><c:out value="${constract.name }" /></td>
					<td><c:out value="${constract.start_time }" /></td>
					<td><c:out value="${constract.end_time }" /></td>
					<td><c:out value="${constract.work_time }" /></td>
					<td><c:out value="${constract.break_time }" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>