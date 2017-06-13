<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="content">   
            
                <form:form class="form" method="POST" action="${contextPath}/search">
                <div class="input-group">
	                <input type="text" name="search_expr" class="form-control" placeholder="Search for...">
	                <span class="input-group-btn">
	                <button class="btn btn-default" type="submit">Search</button>	 
	                </span>
                    </div>                     
                </form:form>
        <br>
            <div class="info">
                <c:choose>
                    <c:when test="${observed == false }">
                        <a href="${contextPath}/observe">
                        <%--<span class="glyphicon glyphicon-eye-open">--%>
                            <span>Obserwowane</span>
                        <%--</span>--%>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${contextPath}/stopObserve">
                            <span>Wszystkie</span>
                        </a>
                    </c:otherwise>

                </c:choose></div>
        </div>
        <br>
            <c:forEach items="${AllQuestions}" var="question">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="info">
                            <div class="right"><c:out value="${question.getUser().getUsername()}" /></div>                            
                        </div>
                        <h3>
                            <c:out value="${question.topic}" />
                        </h3>
                        <p>
                            <c:out value="${question.getBody()}" />
                        </p>
                        <div class="more"><a href="Question/${question.id}">Read more</a></div>
                    </div>
                </div>                
            </c:forEach>
        </div>
    </jsp:body>
</t:genericpage>