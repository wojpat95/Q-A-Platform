<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="container">
        	<div class="row">
        		<div class="col-lg-2">
        			<h3>Categories</h3>
        			<br/>
        			<ul>
        			<c:forEach items="${allCategories}" var="category">
        				<li><a class="btn btn-default" href="${contextPath}/show/${category.getId()}"><c:out value="${category.getName()}" /></a></li>
        			</c:forEach>
        				<li><a class="btn btn-default" href="${contextPath}/">All</a></li>
        			</ul>
        		</div>   
           		<div class="col-lg-10">
           			<div class="content">
		                <form:form class="form" method="POST" action="${contextPath}/search">
		                <div class="input-group">
			                <input type="text" name="search_expr" class="form-control" placeholder="Search for...">
			                <span class="input-group-btn">
			                <button class="btn btn-default" type="submit">Search</button>	 
			                </span>
		                    </div>                     
		                </form:form>
		                <br/>
	                  	<div class="info">
			                <c:choose>
			                    <c:when test="${observed == false }">
			                        <a href="${contextPath}/observe">
			                        <%--<span class="glyphicon glyphicon-eye-open">--%>
			                            <span>Observed question</span>
			                        <%--</span>--%>
			                        </a>
			                    </c:when>
			                    <c:otherwise>
			                        <a href="${contextPath}/stopObserve">
			                            <span>All question</span>
			                        </a>
			                    </c:otherwise>
			
			                </c:choose>
			                 <form:form class="form" method="POST">
				                 <c:choose>
				                 	<c:when test="${sort == true}">
				                 		<div class="input-group">
					                 		<select>
					                 			<option name="topic" value="topic" selected>Topic </option>
					                 			<option name="userName" value="userName">User Name </option>
					                 		</select>
					                 		<span class="input-group-btn">
					                 		<button class="btn btn-default" type="submit">Sort</button>	 
					                 		</span>
				                    	</div>
				                    </c:when>
				                 </c:choose>                    
			                </form:form>
                		</div>
	        			<br> 
	        			<h3><c:out value="${questionListType}" /></h3>        
			            <c:forEach items="${AllQuestions}" var="question">
			                <div class="panel panel-default">
			                    <div class="panel-body">
			                        <h3>
			                            <c:out value="${question.topic}" /><c:out value="${question.getCategory()}" />
			                            <div class="right author"><c:out value="${question.getUser().getUsername()}" /></div>
			                        </h3>
			                        <p>
			                            <c:out value="${question.getBody()}" />
			                        </p>
			                        <div class="more"><a href="Question/${question.id}">Read more</a></div>
			                    </div>
			                </div>                
			            </c:forEach>
		            </div>
           		</div>
        	</div>

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
			
			<form:form class="form" method="GET" action="${contextPath}/draw">
                	<button class="btn btn-default" type="submit">Draw Question></button> 
			</form:form>
        </div>
    </jsp:body>
</t:genericpage>