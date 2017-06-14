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
                    <div class="panel-body">
                        <div class="info">
                            <div class="left">
                                <c:choose>
                                    <c:when test="${observedQuestion == null }">
                                        <a href="${contextPath}/Question/${question.getId()}/observe">
                                            <span class="glyphicon glyphicon-eye-open"></span>
                                            <span>Observe</span>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${contextPath}/Question/${question.getId()}/stopObserve">
                                            <span class="glyphicon glyphicon-eye-close"></span>
                                            <span>Stop observe</span>

                                        </a>
                                    </c:otherwise>

                                </c:choose>
                            </div>
                            <div class="right"><c:out value="${question.getUser().getUsername()}" /></div>                            
                        </div>
                        <h3>
                            <c:out value="${question.topic}" />
                        </h3>
                        <p>
                            <c:out value="${question.getBody()}" />
                        </p>
                        <div class="info">
                            <div class="right">
                                <a href="${contextPath}/Question/edit/${question.getId()}">
                                    <span class="glyphicon glyphicon-edit"></span>
                                    <span>Edit</span>
                                </a>
                            </div>
                        </div>
                    </div>
            </div>    
            <ul class="list-group">
            	<c:forEach items="${allAnswers}" var="answer">
            		<li class="list-group-item">
                		<div class="info">
                			<div class="right"><a href="${contextPath}/Answer/edit/${answer.getId()}"><span class="glyphicon glyphicon-edit"></span></a></div>
                            <div class="left strong"><c:out value="${answer.getUser().getUsername()}" /></div>  
                		</div>
                		<div><c:out value="${answer.getBody()}" /></div>
                	</li>
            	</c:forEach>                  
            </ul>                                                                                                                                                    
            <form:form method="POST" action="${contextPath}/Answer/new" modelAttribute="newanswer">                    
                        <div class="form-group">
                        	<input type="hidden" id="question" path="question" name="question" value="${question.getId()}">                            
                            <textarea id="body" path="body" name="body" type="text" class="form-control"></textarea>                            
                        </div>                    

                    <button type="submit" class="btn btn-default btn-block">Add Answer</button>
            </form:form>               
        </div>
    </jsp:body>
</t:genericpage>