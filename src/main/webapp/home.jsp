<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <c:forEach items="${AllQuestions}" var="question">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="info">
                            <div class="author"><c:out value="${question.getUser().getUsername()}" /></div>
                            <div class="time">1 hour ago</div>
                        </div>
                        <h3>
                            <c:out value="${question.topic}" />
                        </h3>
                        <p>
                            <c:out value="${question.getBody()}" />
                        </p>
                        <div class="more"><a href="#">Read more</a></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:genericpage>