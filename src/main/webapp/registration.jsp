<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->--%>
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>

    <%--<title>Create an account</title>--%>

    <%--<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>

    <%--<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
<%--</head>--%>

<%--<body>--%>

<%--<div class="container">--%>

    <%--<form:form method="POST" modelAttribute="userForm" class="form-signin">--%>
        <%--<h2 class="form-signin-heading">Create your account</h2>--%>
        <%--<spring:bind path="username">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="text" path="username" class="form-control" placeholder="Username"--%>
                            <%--autofocus="true"></form:input>--%>
                <%--<form:errors path="username"></form:errors>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<spring:bind path="password">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>--%>
                <%--<form:errors path="password"></form:errors>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<spring:bind path="passwordConfirm">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="password" path="passwordConfirm" class="form-control"--%>
                            <%--placeholder="Confirm your password"></form:input>--%>
                <%--<form:errors path="passwordConfirm"></form:errors>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>--%>
    <%--</form:form>--%>

<%--</div>--%>
<%--<!-- /container -->--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/register.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/parsley.css">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="register-wrapper">
    <div class="register-header">
        <a href="#">Q&A Platform</a>
    </div>
    <div class="register">
        <form:form method="POST" modelAttribute="userForm" data-parsley-validate="">

            <spring:bind path="username">
            <div>

                <form:input type="text" path="username" placeholder="Username" data-parsley-required="true" autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
            </spring:bind>


            <spring:bind path="password">
            <div>
                <form:input id="password" type="password" path="password" placeholder="Password" data-parsley-required="true"></form:input>
                <form:errors path="password"></form:errors>

            </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
            <div>

                <form:input type="password" path="passwordConfirm" placeholder="Confirm password" data-parsley-required="true" data-parsley-equalto="#password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>

            </spring:bind>
            <div class="submit_button">
                <button type="submit">Sign up</button>
            </div>
        </form:form>
    </div>
    <div class="footer">
        Do have an account? <a href="${contextPath}/login">Sign in</a>
    </div>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/parsley.min.js"></script>
</body>
</html>