<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/parsley.css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="login-wrapper">
    <div class="login-header">
        <a href="#">Q&A Platform</a>
    </div>
    <div class="login">
        <form method="POST" action="${contextPath}/login"  data-parsley-validate="">
            <div>
                <span>${message}</span>
            </div>
            <div>
                <input name="username" type="text" placeholder="Username" data-parsley-required>

            </div>
            <div>
                <input name="password" type="password" placeholder="Password" data-parsley-required>
            </div>
            <div>
                <span>${error}</span>
            </div>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <div class="submit_button">
                <button type="submit">Login</button>
            </div>
        </form>
    </div>
    <div class="footer">
        Don't have an account? <a href="${contextPath}/registration">Sign up now!</a>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/parsley.min.js"></script>
</body>
</html>
