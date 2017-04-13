<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <%--<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>

    <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
        <%--<form id="logoutForm" method="POST" action="${contextPath}/logout">--%>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <%--</form>--%>

        <%--<h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>--%>
		<%--<br>Questions<br>--%>
		<%--<table class="table">--%>
		<%--<c:forEach items="${questions}" var="question">--%>
    		<%--<tr>--%>
    			<%--<td>${question.getTopic()}<br></td>--%>
    			<%--<td>${question.getBody()}<br></td>--%>
    			<%--<td>${question.getUser().getUsername()}<br></td>--%>
    		<%--</tr>--%>
		<%--</c:forEach>--%>
		<%--</table>--%>
    <%--</c:if>--%>

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
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/home.css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<nav>
    <a class="icon" href="${contextPath}">
        Q&A
    </a>
    <div class="add-button">
        <a href="${contextPath}/newQuestion"> Add question</a>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="logout-button">
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </div>
    </c:if>
</nav>
<main>
    <div class="card">
        <c:if test="${pageContext.request.userPrincipal.name != null}">

            <table class="table">
                <c:forEach items="${questions}" var="question">
                    <tr>
                        <td><c:out value="${question.topic}"/><br></td>
                        <td><c:out value="${question.getBody()}"/><br></td>
                        <td><c:out value="${question.getUser().getUsername()}"/><br></td>
                    </tr>
                </c:forEach>
            </table>

        </c:if>
    </div>


</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>