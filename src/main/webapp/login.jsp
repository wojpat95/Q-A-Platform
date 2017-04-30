<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:genericpage>
    <jsp:body>
        <div class="panel panel-default login">
            <div class="panel-heading">
                Q&A Platform
            </div>

            <div class="panel-body">
                <form method="POST" action="${contextPath}/login">

                    <c:if test="${not empty message}">
                        <div class="errors alert alert-success">
                            ${message}
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label for="username">User name:</label>
                        <input name="username" type="text" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input name="password" type="password" class="form-control">
                    </div>

                    <c:if test="${not empty error}">
                        <div class="errors alert alert-danger">
                            ${error}
                        </div> 
                    </c:if>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                    
                    <button type="submit" class="btn btn-default btn-block">Log in</button>
                </form>
            </div>

             <div class="panel-footer">
                Don't have an account? <a href="${contextPath}/registration">Sign up now!</a>
            </div> 
        </div>
    </jsp:body>
</t:genericpage>
