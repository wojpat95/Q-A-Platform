<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <table class="table">
            <c:forEach items="${AllQuestions}" var="question">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h1><c:out value="${question.topic}" /></h1>                                                
                        <p><c:out value="${question.getBody()}" /></p>
                        <div class="more label"><a href="#">Read more</a></div> 
                        <div>Author:
                            <c:out value="${question.getUser().getUsername()}" />
                        </div>
                    </div>
                </div>
            </c:forEach>
        </table>
        </div>
        </main>
    </jsp:body>
</t:genericpage>