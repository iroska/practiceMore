<p><font color="green">${descriptionLengthError }</font></p>
					<form action="tweets" method="GET">
					
						<input type="hidden" name="user_email" value="${loadedUser.email }" />
						<input type="hidden" name="publishedDate" value="new Date()" />
						<input type="text" name="descript" height="100px" width="100"
							size="140" />
						<a href=""><input type="submit" value="Tweet Message" /></a> 
					</form>

					<table border="1" align="center">
						<c:forEach items="${loadedUser.tweet }" var="tweets">
							<tr>
								<td>${tweets.user.firstName } ${tweets.user.lastName }</td>
								<td>${tweets.description }</td>
								<td>${tweets.publishedDate }</td>
							</tr>
						</c:forEach>
					</table>
