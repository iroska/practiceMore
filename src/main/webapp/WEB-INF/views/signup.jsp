<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

<%-- <h1 align="center">Join Twitter Today.</h1><br/>

<a href="">HAVE AN ACCOUNT? Log in</a> <br/>


${users.firstName}<br/>
${users.lastName}<br/>
${users.email}<br/>
${users.password}<br/> --%>

<tiles:insertAttribute name="body" />

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />


	<center>
		<tiles:insertAttribute name="footer" />
	</center>

</body>
</html>