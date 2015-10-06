<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>

<form:errors path="user.*"/>

<form action="loginSubmissionSuccess" method="post">

<fieldset><legend>New to Endava Twitter? Sign up</legend>


<input type="text" name="firstName" value="First name"/><br/>
<input type="text" name="lastName" value="Last name"/><br/>
<input type="text" name="userEmail" value="Email"/><br/>
<input type="password" name="userPassword" value="Password"/><br/>
<input type="submit" name="submit" value="Sign up for Twitter"/>

</fieldset>
</form>


</body>
</html>