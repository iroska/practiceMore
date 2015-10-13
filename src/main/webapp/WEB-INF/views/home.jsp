<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:errors path="tweets.description"/>
<h1>${user.firstName }</h1>

<form action="tweets" method="GET">
	<input type="text" name="users_email" value="ion@rosca.com"/><br/>
	<input type="text" name="description" height="100px" width="100" size="140"/>
	<input type="submit" value="Tweet Message"/>
</form>

<table>
<c:forEach items="${user.tweet }" var="tweets">
	<tr>
		<td>${tweets.description }</td>
		<td>${tweets.publishedDate }</td>
	</tr>
</c:forEach>
</table>

</body>
</html>