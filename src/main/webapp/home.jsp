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
                	
                <form:form class="from" method="POST" action="${contextPath}/sort">
                	<div class="input-group">
                		<select>
                			<option value="topic" selected>Topic</option>
                		</select>
                		<button class="btn btn-default" type="submit">Sort</button>
                	</div>
                </form:form>
        
        <br>            
            <c:forEach items="${AllQuestions}" var="question">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3>
                            <c:out value="${question.topic}" />
                            <div class="right author"><c:out value="${question.getUser().getUsername()}" /></div>
                        </h3>
                        <p>
                            <c:out value="${question.getBody()}" />
                        </p>
                        <div class="more"><a href="Question/${question.id}">Read more</a></div>
                    </div>
                </div>                
            </c:forEach>
                <form:form class="form" method="GET" action="${contextPath}/draw">
                	<button class="btn btn-default" type="submit">Draw Question></button> 
                </form:form>
        </div>
    </jsp:body>
</t:genericpage>