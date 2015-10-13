<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Tweets Page</title>
</head>
<body>

	<h3>Tweets List</h3>
	<c:if test="${!empty tweetList}">
		<table class="tg" border="1">
			<tr>
				<th width="80">Tweet</th>
				<th width="120">Published Date</th>
				<th width="120">Email</th>
			</tr>
			<c:forEach items="${tweetList}" var="tweet">
				<tr>
					<td>${tweet.description}</td>
					<td>${tweet.publishedDate}</td>
					<td>${tweet.user}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>