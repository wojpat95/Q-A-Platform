<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <form:form method="POST" modelAttribute="newquestion">

            <spring:bind path="topic">
                <div class="form-group">
                    <label for="topic">Topic:</label>
                    <form:input type="text" path="topic" class="form-control"></form:input>
                    <form:errors path="topic" class="errors alert alert-danger"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="body">
                <div class="form-group">
                    <label for="body">Body:</label>
                    <form:textarea id="body" path="body" type="text" class="form-control"></form:textarea>
                    <form:errors path="body" class="errors alert alert-danger"></form:errors>
                </div>
            </spring:bind>

            <button type="submit" class="btn btn-default btn-block">Add</button>
        </form:form>
    </jsp:body>
</t:genericpage>