<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweets</title>
</head>
<body>
<h1>${tweets.publishedDate }</h1>

<table>
<c:forEach items="${tweets }" var="data">
	<tr>
		<td>${data.description }</td>
		<td>${data.publishedDate }</td>
	</tr>
</c:forEach>
</table>

</body>
</html>