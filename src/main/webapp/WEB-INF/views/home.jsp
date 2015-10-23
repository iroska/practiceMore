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
					href="logout">Log Out</a>
			</p>
			<br /> <br /> <br /> <br />

			<h3 align="center">My Tweets</h3>

			<p>
				<font color="red">${descriptionLengthError }</font>
			</p>


			<form action="tweets" method="POST">
				<input type="hidden" name="user_email" value="${loadedUser.email }" />
				<input type="hidden" name="publishedDate" value="new Date()" />
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
			<h5 align="center">
				<font color="#0080FF">TWEETS <br>- ${sizeUserTweets } -
				</font><br />
			</h5>

			<table border="1" align="center">
				<tr>
					<th width="120">User</th>
					<th width="120">Description</th>
					<th width="120">Published Date</th>
				</tr>
				<c:choose>
					<c:when test="${empty existingUser}">
						<c:forEach items="${userTweetsSublist }" var="tweets">
							<tr>
								<td>${loadedUser.firstName }${loadedUser.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
								<td><form action="editmytweet" method="GET">
										<input type="hidden" name="userToEdit"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToEdit" value="${tweets.id }" /> <input
											type="hidden" name="textToEdit"
											value="${tweets.description }" /> <input type="submit"
											value="EDIT Tweet" />
									</form></td>
								<td><form action="deletemytweet" method="GET">
										<input type="hidden" name="userToDelete"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToDelete" value="${tweets.id }" /> <input
											type="hidden" name="textTodelete"
											value="${tweets.description }" /> <input type="submit"
											value="Delete Tweet" />
									</form></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach items="${existingUser }" var="tweets">
							<tr>
								<td>${loadedUser.firstName }${loadedUser.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>

								<td><form action="editmytweet" method="GET">
										<input type="hidden" name="userToEdit"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToEdit" value="${tweets.id }" /> <input
											type="hidden" name="textToEdit"
											value="${tweets.description }" /> <input type="submit"
											value="EDIT Tweet" />
									</form></td>
								<td><form action="deletemytweet" method="GET">
										<input type="hidden" name="userToDelete"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToDelete" value="${tweets.id }" /> <input
											type="hidden" name="textTodelete"
											value="${tweets.description }" /> <input type="submit"
											value="Delete Tweet" />
									</form></td>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>
			</table>
			<br />
			<table>
				<tr>
					<td><form action="paginateTweets" method="GET">
							<input type="hidden" name="firstrow" value="${firstRow}" /> <input
								type="hidden" name="rowcount" value="${rowCount}" /> <input
								type="hidden" name="user_email" value="${loadedUser.email }" />
							<input type="submit" name="page" value="Previous" /> <input
								type="submit" name="page" value="Next" />
						</form></td>
					<td align="right"></td>
					<td align="right">Tweets from ${firstRow} to ${rowCount}</td>
				</tr>
			</table>
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
