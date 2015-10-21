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

			<h3 align="center">Change your ${loadedUser.firstName }</h3>

			<h3>Your actual Profile Data are:</h3>
			<br />
			<p>First Name: ${loadedUser.firstName }</p>
			<br>
			<p>Last Name: ${loadedUser.lastName }</p>
			<br>
			<p>Email: ${loadedUser.email }</p>
			<br>
			<p>Password: ${loadedUser.password }</p>
			<br> <br /> <br />


			<h5 align="center">
				<font color="green">${successfulRegistration }</font>
			</h5>

			<div class="row">
				<div class="col s12 m6 l4 offset-l4 offset-m3 amber lighten-5">
					<div class="center promo promo-example">
						<h5 class="light condensed">New to Endava Twitter? Sign up</h5>
					</div>

					<form action="changeProfile" method="post">
					<div class="row">
							<div class="input-field col s6">
								<input id="role"  name="role" value="USER_ROLE" type="hidden"
									> <label for="role">First
									Name</label>
								<form:errors path="user.firstName" />
							</div>							
						</div>						
						<div class="row">
							<div class="input-field col s6">
								<input id="first_name" name="firstName" type="text"
									class="validate"> <label for="first_name">First
									Name</label>
								<form:errors path="user.firstName" />
							</div>
							<div class="input-field col s6">
								<input id="last_name" name="lastName" type="text"
									class="validate"> <label for="last_name">Last
									Name</label>
								<form:errors path="user.lastName" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<input id="email" name="email" type="email" class="validate">
								<label for="email" data-error="wrong" data-success="">Email</label>
								<form:errors path="user.email" />
							</div>
						</div>
						<!-- <div class="row">
                <div class="input-field col s12">
                    <input id="avatar" name="avatar" type="text" class="validate">
                    <label for="password">avatar</label>
                </div>
            </div> -->
						<div class="row">
							<div class="input-field col s12">
								<input id="password" name="password" type="password"
									class="validate" /> <label for="password">Password</label>
								<form:errors path="user.password" />
							</div>
						</div>
						<div class="row light condensed center-align">
							<p align="center">
								<font color="green">${userAlreadyExists }</font>
							</p>
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
								type="submit" name="submit">
								Update My Profile <i class="material-icons right">send</i>
							</button>
						</div>
					</form>
				</div>
			</div>


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
