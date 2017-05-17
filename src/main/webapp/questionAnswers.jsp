<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:body>
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="info">
                            <div class="left"><a href="#"><span class="glyphicon glyphicon-edit"></span></a></div>
                            <div class="right">AUTOR</div>                            
                        </div>
                        <h3>
                            TEMAT
                        </h3>
                        <p>
                            PYTANIE
                        </p>                        
                    </div>
            </div>    
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="info">
                            <div class="right"><a href="#"><span class="glyphicon glyphicon-edit"></span></a></div>
                            <div class="left strong">AUTOR</div>                            
                        </div>
                    <div>ODPOWIEDZ1</div>
                </li>
                <li class="list-group-item">
                    <div class="info">
                            <div class="right"><a href="#"><span class="glyphicon glyphicon-edit"></span></a></div>
                            <div class="left strong">AUTOR</div>                            
                        </div>
                    <div>ODPOWIEDZ2</div>
                </li>   
                <li class="list-group-item">
                    <div class="info">
                            <div class="right"><a href="#"><span class="glyphicon glyphicon-edit"></span></a></div>
                            <div class="left strong">AUTOR</div>                            
                        </div>
                    <div>ODPOWIEDZ3</div>
                </li>                   
            </ul>                                                                                                                                                    
            <form method="POST" modelAttribute="newanswer">                    
                        <div class="form-group">                            
                            <textarea id="body" path="body" type="text" class="form-control"></textarea>                            
                        </div>                    

                    <button type="submit" class="btn btn-default btn-block">Add Answer</button>
            </form>               
        </div>
    </jsp:body>
</t:genericpage>