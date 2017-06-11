<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="content">  
        <div>Questions count: <c:out value="${QuestionsCount}" /></div>
        <div>Answers under own questions count: <c:out value="${AnswersCount}" /></div>
        <div>Own answers count: <c:out value="${OwnAnswersCount}" /></div>                             
        </div>               
    </jsp:body>
</t:genericpage>