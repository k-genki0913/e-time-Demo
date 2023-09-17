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
<h2><c:out value="${year }"/>年<c:out value="${month }" />月の申請はされています</h2>
<h2>修正が必要な場合は管理部へ連絡してください</h2>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>