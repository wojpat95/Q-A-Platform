<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Q&A Platform</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${contextPath}/">Home</a></li>
            <li><a href="${contextPath}/Question/new">Add question</a></li>
            <li><a href="${contextPath}/Statistics">Statistics</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${pageContext.request.userPrincipal.name != null}">

                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <li>
                    <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>