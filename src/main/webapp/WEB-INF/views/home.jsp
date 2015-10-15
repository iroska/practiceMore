<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users home page</title>
</head>
<body>


	<p align="right">
		Hello ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp; <a
			href="logout">Sign Out</a>
	</p>
	<br />
	<br />
	<br />
	<br />

	<h3 align="center">My Tweets</h3>

	<p>
		<font color="green">${descriptionLengthError }</font>
	</p>
	<form action="tweets" method="GET">

		<input type="hidden" name="user_email" value="${loadedUser.email }" />
		<input type="hidden" name="publishedDate" value="new Date()" /> <input
			type="text" name="descript" height="100px" width="100" size="140" />
		<input type="submit" value="Tweet Message" />
	</form>
	
	<%-- <a href="<c:url value='/home'/>" >Get tweet</a> --%>
	

	<table border="1" align="center">
		<c:forEach items="${newloadedUser.tweet }" var="tweets">
			<tr>
				<td>${tweets.user.firstName }${tweets.user.lastName }</td>
				<td>${tweets.description }</td>
				<td>${tweets.publishedDate }</td>				         	
			</tr>
		</c:forEach>
	</table>

	<br />
	<br />
	<br />
	<br />
	<br />
	
	<h3 align="center">Existent Users</h3>

	<c:if test="${!empty userList}">
		<table class="tg" border="1" align="center">
			<tr>
				<th width="80">First Name</th>
				<th width="120">Last Name</th>
				<th width="120">Email</th>
			</tr>
			<c:forEach items="${userList}" var="person">
				<tr>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td>${person.email}</td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	<br/>	
	
</body>
</html>