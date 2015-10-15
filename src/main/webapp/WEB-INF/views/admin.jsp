<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


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

</body>
</html>