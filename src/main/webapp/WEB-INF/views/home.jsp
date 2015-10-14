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

	<form:errors path="tweets.description" />
	<div class="container">
		<div style="float: right; margin-top: -30px">
			<c:choose>
				<c:when test="${empty loadedUser.firstName }">
					<a href="users?register"><spring:message
							code="homePage.RegisterLink" /></a>
					<a href="login">Login</a>
				</c:when>
				<c:otherwise>
			Hello ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp;
			<a href="logout">Sign Out</a>
				</c:otherwise>
			</c:choose>
		</div>
		
		
		<br />
					<br />
					<br />
					<br />
					<br />

					<form action="tweets" method="GET">
						<input type="hidden" name="${loadedUser.email }" value="${loadedUser.email }" />
						<input type="text" name="description" height="100px" width="100"
							size="140" />
						 <input type="submit" value="Tweet Message" />
					</form>

					<table>
						<c:forEach items="${loadedUser.tweet }" var="tweets">
							<tr>
								<td>${tweets.user.firstName } ${tweets.user.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
							</tr>
						</c:forEach>
					</table>

	</div>

</body>
</html>