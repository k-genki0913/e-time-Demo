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
◆ユーザーID：
<c:choose>
	<c:when test="${not empty input_user_id }">
		<input type="text" name="user_id" value="${input_user_id }" required><br>
	</c:when>
	<c:otherwise>
		<input type="text" name="user_id"><br>
	</c:otherwise>
</c:choose>
※数字10桁<br>
◆パスワード：<input type="password" name="password" required><br>
※ローマ字大文字小文字、数字、記号(-、_)　8桁以上<br>
◆氏名：
<c:choose>
	<c:when test="${not empty name }" >
		<input type="text" name="name" value="${name }" required><br>
	</c:when>
	<c:otherwise>
		<input type="text" name="name" required><br>
	</c:otherwise>
</c:choose>
◆メールアドレス：
<c:choose>
	<c:when test="${not empty mail_address }" >
		<input type="email" name="mail_address" value ="${mail_address }" required><br>
	</c:when>
	<c:otherwise>
		<input type="email" name="mail_address" required><br>
	</c:otherwise>
</c:choose>
◆管理者権限：<br>
<c:choose>
	<c:when test="${admin == 1 }">
		<input type="radio" name="admin" value=1 checked required>有効<br>
	</c:when>
	<c:otherwise>
		<input type="radio" name="admin" value=1 required>有効<br>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${admin == 0 }">
		<input type="radio" name="admin" value=0 checked required>無効<br>
	</c:when>
	<c:otherwise>
		<input type="radio" name="admin" value=0 required>無効<br>
	</c:otherwise>
</c:choose>
◆役割(役職)：
<select name="role" required>
<c:forEach var="role" items="${roleList}">
	<c:choose>
		<c:when test="${role.key == role_id }">
			<option value="${role.key}" selected>${role.value}</option>
		</c:when>
		<c:otherwise>
			<option value="${role.key}">${role.value}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</select><br>
◆部署：<select name="department" required>
<c:forEach var="department" items="${departmentList}">
	<c:choose>
		<c:when test="${department.key == department_id }">
			<option value="${department.key}" selected>${department.value}</option>
		</c:when>
		<c:otherwise>
			<option value="${department.key}">${department.value}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</select><br>
<input type="submit" value="登録">
</form>
<a href="HomeServlet">ホーム画面へ戻る</a>
</body>
</html>