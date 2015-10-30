<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<script src="scripts.js"></script>
<script>
	countChar(val);
</script>


<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
	media="screen,projection" />
<link rel="stylesheet"
	href="<c:url value=" resources/css/styles.css" />"
	media="screen, projection" />
<link rel="icon" type="image/png"
	href="<c:url value=" resources/img/logo.png" />" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Log Page</title>
</head>
<body>

	<%@include file="partial/navHome.jsp"%>

	<div class="container">
		<div class="row">

			<p align="left">
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
				<!-- <input type="text" name="descript" height="100px" width="100" size="140" /> -->

				
				<div class="input-field col s12">
					<textarea id="descript"  class="materialize-textarea tweet-body"
						name="descript"></textarea>
					<label for="descript">Tweet here</label>					
				</div>
				<div class="row light condensed left-align">
					<a href="home">
						<button class="btn waves-effect waves-ligh cyan lighten-3t"
							id="tweetMessage" type="submit" name="submit">
							Tweet Message <i class="material-icons done">done</i>
						</button>
						
					<!-- 	<input class="btn waves-effect waves-ligh cyan lighten-3t" id="text-remaining" class="input-medium uneditable-input"
					placeholder="140 Chars Remaining" readonly /> -->
						
					</a>
				</div>

			</form>
			<h5 align="center">
				<font color="#0080FF">TWEETS <br>- ${sizeUserTweets } -
				</font><br />
			</h5>
			<%-- <c:set var="counts" value="0" scope="session" /> --%>
			<table border="1" align="center">

				<thead>
					<tr>
						<th width="20%" data-field="id">User</th>
						<th width="62%" data-field="name">Description</th>
						<th width="65%" data-field="price">Published Date</th>
						<th width="5%"></th>
						<th width="5%"></th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${empty existingUser}">
						<c:forEach items="${userTweetsSublist }" var="tweets">
							<tr>

								<td>${loadedUser.firstName }&nbsp;${loadedUser.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
								<td><form action="editmytweet" method="GET">
										<input type="hidden" name="userToEdit"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToEdit" value="${tweets.id }" /> <input
											type="hidden" name="textToEdit"
											value="${tweets.description }" />
										<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="edit-btn" type="submit" name="page" value="EDIT Tweet">
											<i class="material-icons">mode_edit</i>
										</button>
									</form></td>
								<td>
									<form action="deletemytweet" method="GET">
										<input type="hidden" name="userToDelete"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToDelete" value="${tweets.id }" /> <input
											type="hidden" name="textTodelete"
											value="${tweets.description }" />
										<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="delete-btn" type="submit" name="page"
											value="Delete Tweet">
											<i class="material-icons delete">delete</i>
										</button>
									</form>
								</td>
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
											value="${tweets.description }" />
										<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="edit-btn" type="submit" name="page" value="EDIT Tweet">
											<i class="material-icons">mode_edit</i>
										</button>
									</form></td>
								<td>
									<form action="deletemytweet" method="GET">
										<input type="hidden" name="userToDelete"
											value="${loadedUser.email }" /> <input type="hidden"
											name="idTweetToDelete" value="${tweets.id }" /> <input
											type="hidden" name="textTodelete"
											value="${tweets.description }" />

										<button class="btn waves-effect waves-ligh cyan lighten-3t"
											id="delete-btn" type="submit" name="page"
											value="Delete Tweet">
											<i class="material-icons">mode_delete</i>
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>
			</table>
			<br />
			<table>
				<tr>
					<td>
						<form action="paginateTweets" method="GET">
							<input type="hidden" name="firstrow" value="${firstRow}" /> <input
								type="hidden" name="rowcount" value="${rowCount}" /> <input
								type="hidden" name="user_email" value="${loadedUser.email }" />
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
								id="previous-btn" type="submit" name="page" value="Previous">
								<i class="material-icons arrow-L">arrow_back</i>
							</button>
							<button class="btn waves-effect waves-ligh cyan lighten-3t"
								id="next-btn" type="submit" name="page" value="Next">
								<i class="material-icons arrow-R">arrow_forward</i>
							</button>

						</form>
					</td>
					<td align="right"></td>
				</tr>
			</table>
			<br />
			<p align="center">
				<b><c:forEach begin="1" end="${numberOfRealPages }"
						varStatus="loop">
						<c:if test="${selectedRealPage == loop.index }">
							<th><font color="#0080FF" size="6">${loop.index}
									&nbsp; </font></th>
						</c:if>
						<c:if test="${selectedRealPage != loop.index }">
							<th><font size="5">${loop.index}&nbsp; &nbsp; &nbsp;</font></th>
						</c:if>
					</c:forEach></b>
			</p>
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
