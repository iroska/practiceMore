<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HomeLogPage</title>
</head>
<body>

<h1 align="center">Wellcome To Team 01 Twitter Application</h1>

<form action="" method="post" >


<fieldset><legend>HAVE AN ACCOUNT? Log in</legend>

<input type="text" name="" value="Username"/><br/>
<input type="password" name="password" />
<input type="submit" name="submit" value="Log in"/>

</fieldset>
</form><br/>

<form action="showUsers/add" method="post">

<fieldset><legend>New to Endava Twitter? Sign up</legend>


<input type="text" name="firstName" value="First name"/><br/>
<input type="text" name="lastName" value="Last name"/><br/>
<input type="text" name="email" value="Email"/><br/>
<input type="text" name="avatar" value ="avatar" /><br/>
<input type="password" name="password" value="Password"/><br/>
<input type="submit" name="submit" value="Sign up for Twitter"/>

</fieldset>
</form>

</body>
</html>