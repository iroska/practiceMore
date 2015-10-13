<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<div class="container">
${loginError}

    <h2 class="center-align light hide-on-small-only">Wellcome To Team 01 Twitter Application</h2>
    <h4 class="center-align light hide-on-med-and-up">Wellcome To Team 01 Twitter Application</h4>
    <div class="divider"></div>    
    
    <div class="row thin">
        <div class="col s12 m6 l4 offset-l4 offset-m3 lime lighten-5">
            <div class="center promo promo-example">
                <h5 class="light condensed">Log in</h5>
            </div>
            <form action="login" method="post">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="userEmail" name="userEmail" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" name="password" type="password" class="validate">
                        <label for="password">Password</label>                       
                    </div>
                </div>
                <div class="row light condensed center-align">
                    <button class="btn waves-effect waves-ligh cyan lighten-3t" type="submit" name="submit" id="loginButton">
                        Log in
                        <i class="material-icons done">done</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="divider" > </div>
</div>

</body>
</html>