<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>DemoCyncs ログイン画面</h1>
<c:if test="${not empty inputErrorMsg }">
	<p><c:out value="${inputErrorMsg }" /></p><br>
</c:if>
<form action = "LoginServlet" method = "post">
ログインID：<input type="text" name="user_id"><br>
パスワード：<input type="password" name="password"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>