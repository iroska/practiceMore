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
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
          media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HomeLogPage</title>
</head>
<body>
<div class="container">
<h1 align="center">Wellcome To Team 01 Twitter Application</h1>

<form action="" method="post">


    <fieldset>
        <legend>HAVE AN ACCOUNT? Log in</legend>

        <input type="text" name="" value="Username"/><br/>
        <input type="password" name="password"/>
        <input type="submit" name="submit" value="Log in"/>

    </fieldset>
</form>
<br/>
<hr/>
    <div class="divider"></div>
<div class="row">
    <div class="col s12 m6 l4 offset-l4 offset-m3 ">
        <div class="center promo promo-example">
            <i class="material-icons">group</i>
            <p class="promo-caption">New to Endava Twitter? Sign up</p>
        </div>

    <form action="showUsers/add" method="post">
        <div class="row">
            <div class="input-field col s6">
                <input id="first_name" name="firstName" type="text" class="validate">
                <label for="first_name">First Name</label>
            </div>
            <div class="input-field col s6">
                <input id="last_name" name="lastName" type="text" class="validate">
                <label for="last_name">Last Name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="email" name="email" type="email" class="validate">
                <label for="email" data-error="wrong" data-success="">Email</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="avatar" name="avatar" type="text" class="validate">
                <label for="password">avatar</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="password" name="password" type="password" class="validate">
                <label for="password">Password</label>
            </div>
        </div>

        <div class="row">
            <button class="btn waves-effect waves-light" type="submit" name="submit">
                Sign up for Twitter
                <i class="material-icons right">send</i>
            </button>
        </div>
    </form>
    </div>
</div>

<!--<form action="showUsers/add" method="post">

    <fieldset>
        <legend>New to Endava Twitter? Sign up</legend>


        <input type="text" name="firstName" value="First name"/><br/>
        <input type="text" name="lastName" value="Last name"/><br/>
        <input type="text" name="email" value="Email"/><br/>
        <input type="text" name="avatar" value="avatar"/><br/>
        <input type="password" name="password" value="Password"/><br/>
        <input type="submit" name="submit" value="Sign up for Twitter"/>

    </fieldset>
</form>-->
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
    </div>
</body>
</html>