<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
</head>
<body>
<h1>新規ユーザー登録画面</h1>
<c:if test="${ not empty userRegistErrorMsg }">
	<c:out value="${userRegistErrorMsg }" />
</c:if><br>
<c:if test="${ not empty daoRegistErrorMsg }">
	<c:out value="${daoRegistErrorMsg }" />
</c:if><br>
<form action="UserRegistServlet" method="POST">
◆ユーザーID：<input type="text" name="user_id"><br>
※数字10桁<br>
◆パスワード：<input type="password" name="password" required><br>
※ローマ字大文字小文字、数字、記号(-、_)　8桁以上<br>
◆氏名：<input type="text" name="name" required><br>
◆メールアドレス：<input type="email" name="mail_address" required><br>
◆管理者権限：<br>
<input type="radio" name="admin" value=1 required>有効<br>
<input type="radio" name="admin" value=0 required>無効<br>
◆役割(役職)：<select name="role" required>
<c:forEach var="role" items="${roleList}">
	<option value="${role.key}">${role.value}</option>
</c:forEach>
</select><br>
◆部署：<select name="department" required>
<c:forEach var="department" items="${departmentList}">
	<option value="${department.key}">${department.value}</option>
</c:forEach>
</select><br>
<input type="submit" value="登録">
</form>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>