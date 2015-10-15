<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Users Page</title>
</head>
<body>


	<h3>Users List</h3>
	<c:if test="${!empty userList}">
		<table class="tg" border="1">
			<tr>
				<th width="80">First Name</th>
				<th width="120">Last Name</th>
				<th width="120">Email</th>
			</tr>
			<c:forEach items="${userList}" var="person">
				<tr>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td><a href="${person.email}"></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>