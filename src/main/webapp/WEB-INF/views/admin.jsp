<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<link rel="icon"
		  type="image/png"
		  href="<c:url value=" resources/img/logo.png" />" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin page</title>
</head>
<body>

	<%@include file="partial/navAdmin.jsp"%>

	<div class="container">
		<div class="row">
		<br/>
			<p align="left">
				Hello ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp; <a
					href="logout">Log Out</a>
			</p><br/>

			<h3>TWITTER Users</h3>
			<c:if test="${!empty userList}">
				<table class="tg" border="1">
					<tr>
						<th width="12%">First Name</th>
						<th width="12%">Last Name</th>
						<th width="16%">Email</th>
						<th width="30"></th>
						<th width="30"></th>
					</tr>
					<c:forEach items="${userList}" var="person">
						<tr>
							<td>${person.firstName}</td>
							<td>${person.lastName}</td>
							<td>${person.email}</td>
							<td><form action="userstweet_admin" method="get">
							<input type="hidden" name="userEmail"  value="${person.email}"/>
							
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="View tweets-btn" type="submit" value="View tweets">View Tweets</button>
								
							<!-- <input type="submit" value="View tweets "/>		 -->			
							</form></td>	
							<%-- <td><form action="#" method="get">
							<input type="hidden" name="userEmail"  value="${person.email}"/>	
							<input type="submit" value="Edit "/>					
							</form></td>	 --%>
							<td>
							<c:if test="${loadedRole != person.role}">
							<form action="delite_user" method="get">
							<input type="hidden" name="userEmail"  value="${person.email}"/>	
							
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="DeliteUserFromDatabase-btn" type="submit" value="Delite User From Database">Delite User From Database</button>
							
						<!-- 	<input type="submit" value="Delite User From Database"/>	 -->				
							</form>
							</c:if>
							</td>							
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<br/>
	<br/>

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