<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:genericpage>
    <jsp:body>
        <div class="panel panel-default register">
            <div class="panel-heading">
                Register
            </div>

            <div class="panel-body">
                <form:form method="POST" modelAttribute="userForm">

                    <spring:bind path="username">
                    <div class="form-group">
                        <label for="username">User name:</label>
                        <form:input type="text" path="username" class="form-control"></form:input>                        
                        <form:errors path="username" class="errors alert alert-danger"></form:errors>                        
                    </div>
                    </spring:bind>

                    <spring:bind path="password">
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <form:input type="password" path="password" class="form-control"></form:input>                        
                        <form:errors path="password" class="errors alert alert-danger"></form:errors>                        
                    </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                    <div class="form-group">
                        <label for="passwordConfirm">Password:</label>
                        <form:input type="password" path="passwordConfirm" class="form-control"></form:input>                        
                        <form:errors path="passwordConfirm" class="errors alert alert-danger"></form:errors>                        
                    </div>
                    </spring:bind>

                    <button type="submit" class="btn btn-default btn-block">Sing up</button>
        
                </form:form> 
            </div> 

            <div class="panel-footer">
                Do you have an account? <a href="${contextPath}/login">Sign in</a>
            </div>  
        </div>  
    </jsp:body>
</t:genericpage>