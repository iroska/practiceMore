<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css"
          media="screen,projection"/>
    <link rel="stylesheet" href="<c:url value="resources/css/styles.css" />" media="screen, projection" />
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HomeLogPage</title>
</head>
<body>
<nav class="deep-orange accent-4">
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo">Logo</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="sass.html">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">Javascript</a></li>
            <li><a href="mobile.html">Mobile</a></li>
        </ul>
        <ul class="side-nav" id="mobile-demo">
            <li><a href="sass.html">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">Javascript</a></li>
            <li><a href="mobile.html">Mobile</a></li>
        </ul>
    </div>
</nav>

 <form:errors path="tweets.description"/>
<div class="container">
    <h2 class="center-align light hide-on-small-only">Wellcome To Team 01 Twitter Application</h2>
    <h4 class="center-align light hide-on-med-and-up">Wellcome To Team 01 Twitter Application</h4>
    <div class="divider"></div>
    <div class="row thin">
        <div class="col s12 m6 l4 offset-l4 offset-m3 amber lighten-5">
            <div class="center promo promo-example">
                <h5 class="light condensed">New to Endava Twitter? Sign up</h5>
            </div>

            <form action="home" method="post">
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName" type="text" class="validate">
                        <label for="first_name">First Name</label>
                        <form:errors path="user.firstName"/>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text" class="validate">
                        <label for="last_name">Last Name</label>
                        <form:errors path="user.lastName"/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" name="email" type="email" class="validate">
                        <label for="email" data-error="wrong" data-success="">Email</label>
                        <form:errors path="user.email"/>
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
                        <input id="password" name="password" type="password" class="validate">
                        <label for="password">Password</label>
                        <form:errors path="user.password"/>
                    </div>
                </div>
                <div class="row light condensed center-align">
                    <button class="btn waves-effect waves-ligh cyan lighten-3t" type="submit" name="submit">
                        Sign up for Twitter
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <br/>
    <form action="delete/">
    Delete <input type="text" name="email" value="email"/>
    <input type="submit" value="delete">
    </form>
    
    <form action="users">
    Users <input type="submit" value="ListUsers">
    </form>
    
    <div class="row">
        <div class="divider col s12 m6 l4 offset-l4 offset-m3"></div>
    </div>
    <div class="row thin">
        <div class="col s12 m6 l4 offset-l4 offset-m3 lime lighten-5">
            <div class="center promo promo-example">
                <h5 class="light condensed">Log in</h5>
            </div>
            <form action="" method="post">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="username" name="username" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" name="password" type="password" class="validate">
                        <label for="password">Password</label>
                        <form:errors path="user.password"/>
                    </div>
                </div>
                <div class="row light condensed center-align">
                    <button class="btn waves-effect waves-ligh cyan lighten-3t" type="submit" name="submit">
                        Log in
                        <i class="material-icons done">done</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="divider" > </div>
</div>
<footer class="page-footer deep-orange accent-4">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2014 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/css/styles.css" />"></script>
</body>
</html>