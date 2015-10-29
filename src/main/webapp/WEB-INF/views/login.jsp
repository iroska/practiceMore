<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
          media="screen,projection"/>
    <link rel="stylesheet" href="<c:url value=" resources
    /css/styles.css" />" media="screen, projection" />
    <!--Let browser know website is optimized for mobile-->
    <link rel="icon"
          type="image/png"
          href="<c:url value=" resources/img/logo.png" />" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HomeLogPage</title>
</head>
<body>

<%@include file="partial/nav.jsp" %>

<div class="container">
    <h2 class="center-align light hide-on-small-only">Wellcome To
        Team 01 Twitter Application</h2>
    <h4 class="center-align light hide-on-med-and-up">Wellcome To
        Team 01 Twitter Application</h4>

    <div class="divider"></div>
    <div class="row">
        <div class="col s12 m6 l4 offset-l4 offset-m3 amber lighten-5">
            <div class="center promo promo-example">
                <h5 class="light condensed">New to Endava Twitter? Sign up</h5>
            </div>

            <form action="signup" method="post">
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName" type="text"
                               class="validate"> <label for="first_name">First
                        Name</label>
                       <font color="red"><form:errors path="user.firstName"/></font>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text"
                               class="validate"> <label for="last_name">Last
                        Name</label>
                       <font color="red"><form:errors path="user.lastName"/></font>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" name="email" type="email" class="validate">
                        <label for="email" data-error="wrong" data-success="">Email</label>
                        <font color="red"><form:errors path="user.email"/></font>
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
                        <input id="password" name="password" type="password" class="" />
                        <label for="password">Password</label>
                        <font color="red"><form:errors path="user.password"  /></font>
                    </div>
                </div>
                <div class="row light condensed center-align">
                    <p align="center"><font color="green">${userAlreadyExists }</font></p>
                    <button class="btn waves-effect waves-ligh cyan lighten-3t"
                            type="submit" name="submit">
                        Sign up for Twitter <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="divider col s12 m6 l4 offset-l4 offset-m3"></div>
    </div>
    <div class="row">
        <div class="col s12 m6 l4 offset-l4 offset-m3 lime lighten-5">
            <p align="center"><font color="red">${loginError }</font></p>

            <div class="center promo promo-example">
                <h5 class="light condensed">Log in</h5>
            </div>

            <form action="login" method="POST">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="userId" name="userEmail" type="text"/>
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="login-password" name="password" type="password"/>
                        <label for="login-password">Password</label>
                    </div>
                </div>
                <div class="row light condensed center-align">
                    <button class="btn waves-effect waves-ligh cyan lighten-3t" id="loginButton" type="submit"
                            name="submit">
                        Log in <i class="material-icons done">done</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="divider"></div>
</div>
<%@include file="partial/footer.jsp" %>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<!-- Compiled and minified JavaScript -->
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/scripts.js" />"></script>
</body>
</html>