<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<body>


					
					<table border="1" align="center">
						<c:forEach items="${tweetList }" var="tweets">
							<tr>
								
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
								
							</tr>
						</c:forEach>

					</table>
</body>
</html>
</head>