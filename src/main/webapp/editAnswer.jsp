<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />             
                <div class="panel panel-default content">
                    <div class="panel-heading">
                        Edit answer
                    </div>

                    <div class="panel-body">
                        <form:form method="POST" modelAttribute="editAnswer" action="${contextPath}/Answer/edit">
							<input type="hidden" id="id" path="id" name="id" value="${editAnswer.getId()}">

                            <spring:bind path="body">
                                <div class="form-group">
                                    <label for="body">Body:</label>
                                    <form:textarea id="body" path="body" type="text" class="form-control" value="${editAnswer.getBody() }"></form:textarea>
                                    <form:errors path="body" class="errors alert alert-danger"></form:errors>
                                </div>
                            </spring:bind>

                            <button type="submit" class="btn btn-default btn-block">Add</button>
                        </form:form>
                    </div>                            
        </div>
    </jsp:body>
</t:genericpage>