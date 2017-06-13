<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="content">  
            <div class="panel panel-default">
                    <div class="panel-heading">
                        Statistics
                    </div>
                    <div class="panel-body statistics">
                        <h3>Questions added <div class="right values"><c:out value="${QuestionsCount}"/></div></h3>
                        <h3>Answers recieved<div class="right values"><c:out value="${AnswersCount}"/></div></h3>
                        <h3>Own answers<div class="right values"><c:out value="${OwnAnswersCount}"/></div></h3>    
                    </div>
            </div>                         
        </div>               
    </jsp:body>
</t:genericpage>