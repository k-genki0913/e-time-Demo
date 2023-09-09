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
<h1>ユーザー更新画面</h1>
<c:if test="${ not empty userUpdateErrorMsg }">
	<c:out value="${userUpdateErrorMsg }" />
</c:if><br>
<form action="UserUpdateServlet" method="POST">
◆ユーザーID：<c:out value="${users_DTO.user_id }" /><br>
<input type="hidden" name="user_id" value="${users_DTO.user_id }" >
※数字10桁<br>
◆パスワード：<input type="password" name="password"><br>
<input type="hidden" name="present_password" value="${user_password_DTO.password }" />
 ※ローマ字大文字小文字、数字、記号(-、_)　8桁以上<br>
 ◆氏名：<input type="text" name="name" value="${users_DTO.name }" required><br>
 ◆メールアドレス：<input type="email" name="mail_address" value="${users_DTO.mail_address }" /><br>
 ◆管理者権限：<br>
 <c:choose>
 	<c:when test="${users_DTO.admin == 1 }">
 		<input type="radio" name="admin" value=1 checked required>有効<br>
 	</c:when>
 	<c:otherwise>
 		<input type="radio" name="admin" value=1 required>有効<br>
 	</c:otherwise>
 </c:choose>
<c:choose>
 	<c:when test="${users_DTO.admin == 0 }">
 		<input type="radio" name="admin" value=0 checked required>無効<br>
 	</c:when>
 	<c:otherwise>
 		<input type="radio" name="admin" value=0 required>無効<br>
 	</c:otherwise>
 </c:choose>
 ◆有効/無効：<br>
  <c:choose>
 	<c:when test="${users_DTO.is_valid == 1 }">
 		<input type="radio" name="is_valid" value=1 checked required>有効<br>
 	</c:when>
 	<c:otherwise>
 		<input type="radio" name="is_valid" value=1 required>有効<br>
 	</c:otherwise>
 </c:choose>
<c:choose>
 	<c:when test="${users_DTO.is_valid == 0 }">
 		<input type="radio" name="is_valid" value=0 checked required>無効<br>
 	</c:when>
 	<c:otherwise>
 		<input type="radio" name="is_valid" value=0 required>無効<br>
 	</c:otherwise>
 </c:choose>
 ◆役割(役職)：<select name="role" required>
 <c:forEach var="role" items="${roleList}">
 	<c:choose>
 		<c:when test="${user_role_DTO.role_id == role.key }">
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
		<c:when test="${user_department_DTO.department_id == department.key }">
			<option value="${department.key}" selected>${department.value}</option>
		</c:when>
		<c:otherwise>
			<option value="${department.key}">${department.value}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</select><br>
<input type="submit" value="登録"><br>
<a href="HomeServlet">ホーム画面へ戻る</a>
</form>
</body>
</html>