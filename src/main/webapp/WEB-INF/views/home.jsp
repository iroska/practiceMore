<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
	media="screen,projection" />
<link rel="stylesheet"
	href="<c:url value=" resources
	/css/styles.css" />"
	media="screen, projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HomeLogPage</title>
</head>
<body>

	<%@include file="partial/navHome.jsp"%>

	<div class="container">
		<div class="row">

			<p align="right">
				Hello ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp; <a
					href="logout">Sign Out</a>
			</p>
			<br /> <br /> <br /> <br />

			<h3 align="center">My Tweets</h3>

			<p>
				<font color="green">${descriptionLengthError }</font>
			</p>


			<form action="tweets" method="POST">
				<input type="hidden" name="user_email" value="${loadedUser.email }" />
				<input type="hidden" name="publishedDate"value="new Date()"/>
				<!--<input type="text" name="descript" height="100px" width="100" size="140" />-->
				<div class="input-field col s12">
					
					<textarea id="descript" class="materialize-textarea"
						name="descript"></textarea>
					<label for="descript">Tweet here</label>
				</div>
				<div class="row light condensed left-align">
				<a href="home">
					<button class="btn waves-effect waves-ligh cyan lighten-3t"
						id="tweetMessage" type="submit" name="submit">
						Tweet Message <i class="material-icons done">done</i>
					</button>
				</a>
				</div>
			</form>

			<%-- <a href="<c:url value='/home'/>" >Get tweet</a> --%>


			<table border="1" align="center">

				<c:choose>
					<c:when test="${empty existingUser}">
						<c:forEach items="${loadedUser.tweet }" var="tweets">
							<tr>
								<td>${tweets.user.firstName }${tweets.user.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
								
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<c:forEach items="${existingUser.tweet }" var="tweets">
							<tr>
								<td>${tweets.user.firstName }${tweets.user.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>

				<%-- <c:forEach items="${existingUser.tweet }" var="tweets">
					<tr>
						<td>${tweets.user.firstName }${tweets.user.lastName }</td>
						<td>${tweets.description }</td>
						<td>${tweets.publishedDate }</td>
					</tr>
				</c:forEach> --%>


			</table>

		<%-- 	<br /> <br /> <br /> <br /> <br />

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
 --%>

			<br />
		</div>
	</div>
	<%@include file="partial/footer.jsp"%>


	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<!-- Compiled and minified JavaScript -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/scripts/scripts.js" />"></script>



</body>
</html>
