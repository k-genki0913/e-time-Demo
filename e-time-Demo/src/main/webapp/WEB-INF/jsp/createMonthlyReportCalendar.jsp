<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カレンダー作成画面</title>
</head>
<body>
<h1>カレンダー作成画面</h1><br>
<c:if test="${not empty resultMsg }">
	<p><c:out value="${resultMsg }" /></p><br>
</c:if>
<c:if test="${not empty calendarErrorMsg }">
	<p><c:out value="${calendarErrorMsg }" /></p>
</c:if>
<form action="CreateCalendarServlet" method="POST">
<p>カレンダー作成</p>
<select name="year" required>
<option value="2023">2023</option>
<option value="2024">2024</option>
<option value="2025">2025</option>
<option value="2026">2026</option>
<option value="2027">2027</option>
</select>年
<select name="month" required>
<option value="01">1</option>
<option value="02">2</option>
<option value="03">3</option>
<option value="04">4</option>
<option value="05">5</option>
<option value="06">6</option>
<option value="07">7</option>
<option value="08">8</option>
<option value="09">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>月<br>
<input type="submit" value="カレンダー作成">
</form>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>