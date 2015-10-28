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

<div class="row">
	<div class="col s12 m6">
		<div class="card light-blue lighten-5">
			<div class="card-content blue-text text-darken-2">
				<span class="card-title">Card Title</span>
				<p>I am a very simple card. I am good at containing small bits of information.
					I am convenient because I require little markup to use effectively.</p>
			</div>
			<div class="card-action">
				<a href="#">This is a link</a>
				<a href="#">This is a link</a>
			</div>
		</div>
	</div>
</div>

					
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