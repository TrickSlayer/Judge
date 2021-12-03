<%-- 
    Document   : AddActorRoll
    Created on : Oct 10, 2021, 1:08:20 PM
    Author     : Acer
--%>

<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="dal.ActorDAO"%>
<%@page import="model.Actor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Judge</title>
        <link rel="icon" href="images/Judge-icon.png">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/common.css" rel="stylesheet">
        <link href="css/addActorRole.css" rel="stylesheet">
        <%
            User user = (User) session.getAttribute("account");
            if (user == null || user.getPower() < 5) {
                response.sendRedirect("home");
            }
        %>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="body">
                <h2>Add Actor Roll</h2>
                <h4 class="erro">${requestScope.error}</h4>
                <form action="add-actor-role" method="post">
                    <div>
                        <input style="height: 33px; width: 300px" placeholder="Enter Actor Name" name="Search">
                        <button type="submit" class="btn btn-primary" name="action" value="Search">Search</button>
                    </div>
                    <c:set value="${requestScope.actor}" var="actor">
                    </c:set>
                    <br>
                    <div style="display: ${(actor!=null)?"block":"none"}">
                        <input type="radio" value="${actor.getActorID()}" name="Actor" checked class="Info">
                        <input style="height: 33px; width: 300px" value="${actor.getName()}" >
                        <button type="submit" class="btn btn-primary" name="action" value="addOldActor">Add Actor</button>
                    </div>
                    <div class="orther">
                        <h4>Other:</h4>
                        <div class="input">
                            <div class="form-group">
                                <label>Name</label> 
                                <input type="text" 
                                       class="form-control" name="Name" placeholder="Enter name">
                            </div>
                            <div class="form-group">
                                <label>DOB</label> 
                                <input type="date" 
                                       class="form-control" name="DOB" placeholder="Enter DOB">
                            </div>
                            <div class="form-group">
                                <label>Status</label> 
                                <input type="text" 
                                       class="form-control" name="Status" placeholder="Enter Status">
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" name="action" value="addActor">Add New Actor</button>
                    <c:if test="${requestScope.listActorRole.size() != 0}">
                        <table class="table">
                            <tr>
                                <th>Actor Name</th>
                                <th>DOB</th>
                                <th>Status</th>
                                <th>Role</th>
                                <th>Confirm</th>
                            </tr>
                            <c:forEach items="${requestScope.listActorRole}" var="ar">
                                <c:set var="a" value="${ar.getActor()}"/>
                                <tr>
                                    <td>${a.getName()}</td>
                                    <td>${a.getDOB()}</td>
                                    <td>${a.getStatus()}</td>
                                    <td><input name="${a.getActorID()}" type="text" value="${ar.getRole()}"
                                               class="input" placeholder="Enter Role" ></td>
                                    <td><input type="checkbox" name="ActorID" value="${a.getActorID()}" checked></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

                    <!--Movie ID-->
                    <input class ="Info" name="id" type="text" value="${requestScope.id}">
                    <button type="submit" class="btn btn-primary" name="action" value="Confirm">Confirm</button>
                </form>

                <form action="add-role" method="post">
                    <div class="Info">
                        <input class ="" name="id" type="text" value="${requestScope.id}">${requestScope.id}
                        <c:forEach items="${requestScope.listActorRole}" var="ar">
                            <input class="" type="checkbox" name="ActorID" value="${ar.getActor().getActorID()}" checked>${ar.getActor().getActorID()}
                            <input class="" type="checkbox" name="Role" value="${ar.getRole()}" checked>${ar.getRole()}
                        </c:forEach>
                    </div>
                    <button type="submit" class="btn btn-primary addrole" style="display: ${requestScope.display}">Add Role</button>
                </form>

            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>