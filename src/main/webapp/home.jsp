<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
                <c:forEach items="${AllQuestions}" var="question">
                    <div>Topic: <c:out value="${question.topic}"/></div>
                    <div>Body: <c:out value="${question.getBody()}"/></div>
                    <div>Author: <c:out value="${question.getUser().getUsername()}"/></div>
                </c:forEach>
            </table>

        </c:if>
    </div>


</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>