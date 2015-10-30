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
<title>Admin tweets</title>
</head>
<body>

	<%@include file="partial/navAdmin.jsp"%>

	<div class="container">
		<div class="row">
			<br />
			<p align="left">
				Hello ${loadedUser.firstName } ${loadedUser.lastName }! &nbsp; <a
					href="logout">Log Out</a>
			</p>
			<br /> <br /> <br /> <br />

			<h3 align="center">${sessionUser.firstName }'sTWEETS</h3>
			<h5 align="center">
				<font color="#0080FF">- ${numberOfUsersTweetsUser } - </font><br />
			</h5>

			<table border="1" align="center">
				<tr>
					<th width="20%">User</th>
					<th width="40%">Description</th>
					<th width="20%">Published Date</th>
					<th width="20%"></th>
				</tr>
				<c:forEach items="${userTweetsSublistUser }" var="tweets">

					<tr>
						<td>${sessionUser.firstName }${sessionUser.lastName }</td>
						<td>${tweets.description }</td>
						<td>${tweets.publishedDate }</td>
						<td><form action="delite_user_tweet" method="GET">
								<input type="hidden" name="userToDelete"
									value="${sessionUser.email }" /> <input type="hidden"
									name="idTweetToDelete" value="${tweets.id }" /> <input
									type="hidden" name="textTodelete"
									value="${tweets.description }" />

								<button class="btn waves-effect waves-ligh cyan lighten-3t"
									id="Delete Tweet-btn" type="submit" value="Delete Tweet">Delete
									Tweet</button>

								<!-- 	 <input type="submit"value="Delete Tweet" /> -->
							</form></td>
					</tr>
				</c:forEach>
			</table>


			<table>
				<tr>
					<td><form action="paginateTweetsUser" method="GET">
							<input type="hidden" name="firstrowUser" value="${firstRowUser}" />
							<input type="hidden" name="rowcountUser" value="${rowCountUser}" />
							<%-- <input type="hidden" name="sessionUser"  value="${loadedUser.email}"/>--%>
							<input type="hidden" name="user_email"
								value="${specialUser.email }" />


							<button class="btn waves-effect waves-ligh cyan lighten-3t"
								id="previous-btn" type="submit" name="pageUser" value="Previous">
								<i class="material-icons arrow-L">arrow_back</i>
							</button>
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
								id="next-btn" type="submit" name="pageUser" value="Next">
								<i class="material-icons arrow-R">arrow_forward</i>
							</button>


							<!-- 	 <input type="submit" name="pageUser" value="Previous" />
							  <input type="submit" name="pageUser" value="Next" /> -->
						</form></td>
					<td align="right"></td>
					<td align="right">Tweets from ${firstRowUser} to
						${rowCountUser}</td>
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
