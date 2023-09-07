<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar, model.Cnvrt_DayOfWeek" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>契約書入力</title>
</head>
<body>
<h1>契約入力</h1>
<c:if test="${not empty registErrorMsg }" >
	<p><c:out value="${registErrorMsg }" /></p>
</c:if>
<form action="InputConstractServlet" method="POST">
契約ID(自動で採番されます)<br>
<c:if test="${not empty workplace_errorMsg }" >
	<p><c:out value="${workplace_errorMsg }" /></p>
</c:if>
勤務先：<input type="text" name="workplace" required><br>
<c:if test="${not empty constract_start_errorMsg }" >
	<p><c:out value="${constract_start_errorMsg }" /></p>
</c:if>
契約開始日：<input type="text" name="constract_start">(入力例：2023-01-01 この方法で入力してください)<br>
<c:if test="${not empty constract_end_errorMsg }" >
	<p><c:out value="${constract_end_errorMsg }" /></p>
</c:if>
契約終了日：<input type="text" name="constract_end">(入力例：2023-03-31 この方法で入力してください)<br>
<c:if test="${not empty start_time_errorMsg }" >
	<p><c:out value="${start_time_errorMsg }" /></p>
</c:if>
勤務開始時間：<input type="text" name="start_time">(入力方法：4桁の数字で入力してください 例：0930)<br>
<c:if test="${not empty end_time_errorMsg }" >
	<p><c:out value="${end_time_errorMsg }" /></p>
</c:if>
勤務終了時間：<input type="text" name="end_time">(入力方法：4桁の数字で入力してください 例：1830)<br>
<c:if test="${not empty break_time_errorMsg }" >
	<p><c:out value="${break_time_errorMsg }" /></p>
</c:if>
休憩時間：<input type="text" name="break_time">(入力方法：1〜99の数字で入力してください)<br>
<c:if test="${not empty user_id_errorMsg }" >
	<p><c:out value="${user_id_errorMsg }" /></p>
</c:if>
勤務者：<input type="text" name="user_id">(ユーザーIDを入力してください)<br>
<input type="submit" value="登録">
</form>
</body>
</html>