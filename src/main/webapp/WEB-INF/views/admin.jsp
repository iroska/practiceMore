<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<!--Import Google Icon Font-->
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!-- Compiled and minified CSS -->
	<link rel="stylesheet"
		  href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
		  media="screen,projection"/>
	<link rel="stylesheet" href="<c:url value=" resources
	/css/styles.css" />" media="screen, projection" />
	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HomeLogPage</title>
</head>
<body>

<%@include file="partial/nav.jsp" %>

<h1 align="center">Admin Page</h1>


Hello ${loadedUser.role } ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp;
			<a href="logout">Sign Out</a>		
					<br />
					<br />
					<br />
					<br />
<br />
		<form action="delete/">
			Delete <input type="text" name="email" value="email" /> <input
				type="submit" value="delete">
		</form>
		<br />
		<br />
		<br />

		<form action="tweets" method="POST">
			<input type="text" name="userEmail" /><br />			
			<input type="submit" value="Get User" />
		</form>
		<br />
		<br />

		<form action="users" method="GET">
			Users <input type="submit" value="ListUsers">
		</form>
		<br />
		<br />

		<form action="tweetsviwe" method="GET">
			Tweets <input type="submit" value="ListUsers">
		</form>
		<br />
		<br />

<%@include file="partial/footer.jsp" %>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<!-- Compiled and minified JavaScript -->
<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/scripts.js" />"></script>

</body>
</html>