<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ユーザー一覧</h1>
<table border="1">
	<tr>
		<th>ユーザーID</th>
		<th>氏名</th>
		<th>メールアドレス</th>
		<th>管理者権限</th>
		<th>有効無効</th>
		<th>編集</th>
	</tr>
	<c:forEach var="user" items="${userList }" >
		<tr>
			<td><c:out value="${user.user_id }" /></td>
			<td><c:out value="${user.name }" /></td>
			<td><c:out value="${user.mail_address }" /></td>
			<c:choose>
				<c:when test="${user.admin == 1 }">
					<td>有</td>
				</c:when>
				<c:otherwise>
					<td>無</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${user.is_valid == 1 }">
					<td>有効</td>
				</c:when>
				<c:otherwise>
					<td>無効</td>
				</c:otherwise>
			</c:choose>
			<td><form action="UserUpdateServlet" method="get">
				<input type="hidden" name="user_id" value="${user.user_id }" >
				<input type="submit" value="編集">
				</form></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>