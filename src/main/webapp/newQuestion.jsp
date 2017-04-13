<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/newQuestion.css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<nav>
    <a class="icon" href="${contextPath}">
        Q&A
    </a>

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
    <form:form method="POST" modelAttribute="newquestion" data-parsley-validate="">

        <spring:bind path="topic">
            <div>

                <form:input type="text" path="topic" placeholder="Topic" data-parsley-required="true" autofocus="true"></form:input>
            </div>
        </spring:bind>


        <spring:bind path="body">
            <div>
                <form:textarea id="body" path="body" type="text" placeholder="Question" data-parsley-required="true"></form:textarea>
                <form:errors path="body"></form:errors>
            </div>
        </spring:bind>

        <div class="submit_button">
            <button type="submit">Add</button>
        </div>
    </form:form>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>